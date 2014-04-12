package com.rebelkeithy.procedural.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rebelkeithy.procedural.person.Person;

public class Event 
{
	protected EventManager eventManager;
	protected int date;
	protected String note;
	protected List<Person> involved;
	protected boolean log = true;
	
	protected Random rand = new Random();
	
	public Event(EventManager eventManager, int date)
	{
		this.eventManager = eventManager;
		this.date = date;
		involved = new ArrayList<Person>();
	}
	
	public void setLog(boolean log)
	{
	    this.log = log;
	}
	
	public boolean shouldLog()
	{
	    return log;
	}
	
	public void apply()
	{
		
	}
	
	public void setNote(String note)
	{
	    this.note = note;
	}
	
	public int getDate()
	{
		return date;
	}
	
	public String getNote()
	{
		return note;
	}

    public boolean canApply()
    {
        return true;
    }
}
