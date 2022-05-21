package com.exelerate.homeassignment;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.exelerate.homeassignment.entity.EventUpdate;
import com.exelerate.homeassignment.entity.EventUpdateRequest;
import com.exelerate.homeassignment.entity.TrafficSystemClient;
import com.exelerate.homeassignment.repository.EventsRepository;

@SpringBootTest
public class RepositoryTest {
	
	@Autowired
	private EventsRepository repository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
    @Value("${queue.event.process:testQueue}")
    private String eventUpdateQueue;
    
    @Value("${database.max.connections}")
    private int requestsCount;
	
	private static final Logger log = LoggerFactory.getLogger(RepositoryTest.class);

	@Test
	void eventSaveTest() throws InterruptedException {
		  TrafficSystemClient client = new TrafficSystemClient();
		  client.setName("TestClient");
	      for (int i=0; i < requestsCount * 2; i++) {
	    	  EventUpdateRequest request = new EventUpdateRequest();
	    	  request.setAttemptNumber(0);
	    	  request.setRequestId("" + i);	    	  
	    	  EventUpdate event = new EventUpdate();
		      event.setData("My traffic data " + i);
		      event.setClient(client);
		      request.setEvent(event);
		      rabbitTemplate.convertAndSend(eventUpdateQueue, request);
	      }
	      Thread.sleep(60 * 1000); //1 minute
	      // fetch all event (will bypass the threading mechanism), 
	      //if viewing events is added as a requirement, support for threads pooling should be added (similar to event insert),
	      //in addition to caching 
	      log.info("Events found with findAll():");
	      log.info("-------------------------------");
	      for (EventUpdate event : repository.findAll()) {
	        log.info(event.toString());
	      }
		
	}

}
