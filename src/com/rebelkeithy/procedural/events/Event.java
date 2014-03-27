package com.rebelkeithy.procedural.events;

import java.util.ArrayList;
import java.util.List;

import com.rebelkeithy.procedural.person.Person;

public class Event 
{
	protected int date;
	protected String note;
	protected List<Person> involved;
	
	public Event(int date)
	{
		this.date = date;
		involved = new ArrayList<Person>();
	}
	
	public int getDate()
	{
		return date;
	}
}
