package com.niit.Backend.TestCases;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Backend.Dao.EventDao;
import com.niit.Backend.Model.Event;
import com.niit.Backend.util.Date_Time;


public class EventTestCases
{
	Logger log = LoggerFactory.getLogger(EventTestCases.class);
	
	@Autowired
	EventDao eventDAO;
	
	@Autowired
	Event event;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public EventTestCases()
	{	
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.Backend");
		context.refresh();

		eventDAO = (EventDao) context.getBean("eventDAO");
		event = (Event) context.getBean("event");
	}
	
	public void add()
	{
		event.setName("Third Event");
		event.setTime("23:00");
		event.setDate(new Date());
		event.setDescription(" ");
		event.setVenue("BLOCK D, MG Road, Hyd");
		
		Date_Time dt = new Date_Time();
		String date = dt.getDateTime();
		event.setPostedTime(date);
	
		boolean value = eventDAO.addEvent(event);
		log.info("Added successfully "+value);
		System.out.println("Success");
	}
	
	public void delete()
	{
		boolean value = eventDAO.deleteEvent(65);
		if(value)
			log.info("Delete success");
		else
			log.error("Delete Unsuccess");
	}
	
	public void getEvent()
	{
		Event event = eventDAO.getEvent(63);
		if(event == null)
		{
			System.out.println("Event not found");
			log.info("Event not found");
		}
		else
		{
			log.info("Event is found");
			System.out.println("Event");
			System.out.println("Name -"+event.getName()+ "\t Location -"+event.getVenue());
		}
	}
	
	public void listEvent()
	{
		List<Event> events = eventDAO.listEvent();
		if(event == null)
		{
			System.out.println("Events not found");
			log.info("Event not found");
		}
		else
		{
			log.info("Events list is found");
			System.out.println("Event");
			for(Event event : events)
				System.out.println("Name -"+event.getName()+ "\t Location -"+event.getVenue()+"\t Date -"+event.getDate());
		}
	}
	
	public static void main(String[] args) 
	{
		EventTestCases test = new EventTestCases();
//		test.add();
//		test.delete();
//		test.getEvent();
		test.listEvent();
	}
}