package com.exelerate.homeassignment.entity;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EventUpdateRequest  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String requestId;	
	
	private int attemptNumber;	
	
	private EventUpdate event;
	
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public int getAttemptNumber() {
		return attemptNumber;
	}

	public void setAttemptNumber(int attemptNumber) {
		this.attemptNumber = attemptNumber;
	}

	public EventUpdate getEvent() {
		return event;
	}

	public void setEvent(EventUpdate event) {
		this.event = event;
	}
	
	@Override
	public String toString() {
	    try {
	        return new ObjectMapper().writeValueAsString(this);
	    } catch (JsonProcessingException e) {
	       //possibly report?
	    }
	    return null;
	}
}
