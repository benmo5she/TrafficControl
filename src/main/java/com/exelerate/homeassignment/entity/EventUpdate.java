package com.exelerate.homeassignment.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "events")
public class EventUpdate {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	  
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")	  
	private TrafficSystemClient client;
	  
	@Column(name="data", length = 100)
	private String data;
	  
	@Column(name="update_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date updateDate;
	
	public EventUpdate() {
		updateDate = new Date();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrafficSystemClient getClient() {
		return client;
	}

	public void setClient(TrafficSystemClient client) {
		this.client = client;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
