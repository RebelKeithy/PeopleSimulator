package com.rebelkeithy.procedural.events;

import java.util.PriorityQueue;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.Town;
import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.PersonGenerator;

public class EventManager
{
	private EventLog log;
	public PersonGenerator pgen;
	public Town town;
	
	private PriorityQueue<DelayedEvent> eventQueue;
	
	public EventManager(PersonGenerator pgen, Town town)
	{
		log = new EventLog();
		this.pgen = pgen;
		this.town = town;
		
		eventQueue = new PriorityQueue<DelayedEvent>();
	}
	
	public void timeStep()
	{
		while(!eventQueue.isEmpty() && eventQueue.peek().isRead())
		{
			Event event = eventQueue.poll().event;
			event.apply();
			log.addEvent(event);
		}
	}
	
	public void printLog()
	{
		log.printLog();
	}

	public void printLogRelatingTo(Person person) 
	{
		log.printLogRelatingTo(person);
	}
	
	public void propose(Person man, Person woman)
	{
		EventPropose event = new EventPropose(this, Calendar.instance().getDate(), man, woman);
		event.apply();
		log.addEvent(event);
	}
	
	public void marry(Person husband, Person wife)
	{
		EventMarriage event = new EventMarriage(this, Calendar.instance().getDate(), husband, wife);
		event.apply();
		log.addEvent(event);
	}
	
	public void haveChild(Person father, Person mother)
	{
		EventChild event = new EventChild(this, Calendar.instance().getDate(), father, mother);
		event.apply();
		log.addEvent(event);
	}
	
	public void death(Person person)
	{
		EventDeath event = new EventDeath(this, Calendar.instance().getDate(), person);
		event.apply();
		log.addEvent(event);
	}

	public void addDelayedEvent(Event event) 
	{
		eventQueue.add(new DelayedEvent(event, event.date));
	}

	public void newPerson() 
	{
		Event event = new EventNewPerson(this, Calendar.instance().getDate());
		event.apply();
		log.addEvent(event);
	}

	public void makePregnant(Person father, Person mother) {
		Event event = new EventPregnant(this, Calendar.instance().getDate(), father, mother);
		event.apply();
		log.addEvent(event);
	}
}
