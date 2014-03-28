package com.rebelkeithy.procedural.events;

import java.util.ArrayList;
import java.util.List;

import com.rebelkeithy.procedural.Utils;
import com.rebelkeithy.procedural.person.Person;

public class EventLog 
{
	public List<Event> events;
	
	public EventLog()
	{
		events = new ArrayList<Event>();
	}
	
	public void addEvent(Event event)
	{
		events.add(event);
	}
	
	public void printLog()
	{
		for(Event event : events)
		{
			System.out.println(Utils.formatDate(event.date) + ": " + event.note);
		}
	}

	public void printLogRelatingTo(Person person) 
	{
		for(Event event : events)
		{
			if(event.involved.contains(person))
				System.out.println(Utils.formatDate(event.date) + ": " + event.note);
		}
	}
}
