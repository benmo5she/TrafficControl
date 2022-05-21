package com.exelerate.homeassignment.mq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exelerate.homeassignment.entity.EventUpdateRequest;
import com.exelerate.homeassignment.repository.EventsRepository;

@Service
public class MQReceiver {
	
	@Value("${database.max.connections:100000}")
	private int maxConnections;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ExecutorService executorService;
	
	@Autowired
	private EventsRepository repository;
	
	@PostConstruct
	public void init() {
		executorService = Executors.newFixedThreadPool(maxConnections);
	}
	
    @RabbitListener(queues = "${queue.event.process:testQueue}")
    public void receiveMessage(final EventUpdateRequest eventUpdateRequest) {
        logger.info("Received event update request: " + eventUpdateRequest.getRequestId());
        try {
        	executorService.execute(() -> {
        		repository.save(eventUpdateRequest.getEvent());
        		logger.info("Successfully saved event! thread:" + Thread.currentThread().getName());
        	}
        	);	
        }
        catch(Exception ex) {
        	logger.error("Encountered an error during event DB update", ex);
        	//possible to requeue, or send mail to support team with the payload to inquire
        }
    }
    
}
