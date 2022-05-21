package com.exelerate.homeassignment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.exelerate.homeassignment.entity.EventUpdate;

public interface EventsRepository extends CrudRepository<EventUpdate, Long> {

	EventUpdate findById(long id);
	List<EventUpdate> findByClient_Name(String name);

}
