package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.Calendar;

public class DelayedEvent implements Comparable<DelayedEvent>
{
	public Event event;
	public int date;
	
	public DelayedEvent(Event event, int date)
	{
		this.event = event;
		this.date = date;
	}
	
	public boolean isReady()
	{
		return date <= Calendar.instance().getDate();
	}

	@Override
	public int compareTo(DelayedEvent other) 
	{
		return date - other.date;
	}
}
