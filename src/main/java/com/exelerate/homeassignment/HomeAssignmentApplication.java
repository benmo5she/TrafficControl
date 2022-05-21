package com.exelerate.homeassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 
 * @author Ben Moshe
 * 		   Home assignment created to demonstrate receiving messages from message queue using Spring AMQP
 * 		   Afterwards storing the data in in-memory DB (H2) which will be reset after each session.
 * 		   The amount of active connections is limited by 2 factors:
 * 				- Java Thread pooling
 * 				- Hibernate batch insert
 * 		   Both can be configured through the application.properties
 * 		   In addition, there is a unit test (src/test/java/RepositoryTest.java) that demonstrates the process.
 * 		   Note that it is required for AMQP to be configured via the application.properties (or local installation of message queue)
 *
 */
@SpringBootApplication
public class HomeAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeAssignmentApplication.class, args);
	}

}
