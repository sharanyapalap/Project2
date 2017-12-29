package com.niit.Backend.Dao;

import java.util.List;

import com.niit.Backend.Model.Event;

//import com.niit.collaboration.model.Event;

public interface EventDao
{
	public boolean addEvent(Event event);
	
	public boolean deleteEvent(int id);
	
	public Event getEvent(int id);
	
	public List<Event> listEvent();
	
}
