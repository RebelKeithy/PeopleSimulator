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
			if(event.note != null)
				System.out.println(Utils.formatDate(event.date) + ": " + event.note);
			else
				System.out.println(Utils.formatDate(event.date) + ": Event " + event + " is missing note");
		}
	}

	public void printLogRelatingTo(Person person) 
	{
		for(Event event : events)
		{
			if(event.involved.contains(person))
			{
				if(event.note != null)
					System.out.println(Utils.formatDate(event.date) + ": " + event.note);
				else
					System.out.println(Utils.formatDate(event.date) + ": Event " + event + " is missing note");
			}
		}
	}

	public Event[] getEventsByPerson(Person person) 
	{
		List<Event> returnEvents = new ArrayList<Event>();
		
		for(Event event : events)
		{
			if(event.involved.contains(person))
			{
				returnEvents.add(event);
			}
		}
		
		return returnEvents.toArray(new Event[returnEvents.size()]);
	}
}
