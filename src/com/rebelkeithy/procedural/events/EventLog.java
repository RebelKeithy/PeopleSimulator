package com.rebelkeithy.procedural.events;

import java.util.ArrayList;
import java.util.List;

import com.rebelkeithy.procedural.Utils;

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
}
