package com.rebelkeithy.procedural.events;

import java.util.ArrayList;
import java.util.List;

import com.rebelkeithy.procedural.person.Person;

public class Event 
{
	protected EventManager eventManager;
	protected int date;
	protected String note;
	protected List<Person> involved;
	
	public Event(EventManager eventManager, int date)
	{
		this.eventManager = eventManager;
		this.date = date;
		involved = new ArrayList<Person>();
	}
	
	public void apply()
	{
		
	}
	
	public int getDate()
	{
		return date;
	}
}
