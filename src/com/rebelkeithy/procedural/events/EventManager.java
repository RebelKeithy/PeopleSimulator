package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.Town;
import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.PersonGenerator;

public class EventManager
{
	private EventLog log;
	private PersonGenerator pgen;
	private Town town;
	
	public EventManager(PersonGenerator pgen, Town town)
	{
		log = new EventLog();
		this.pgen = pgen;
		this.town = town;
	}
	
	public void printLog()
	{
		log.printLog();
	}
	
	public void propose(Person man, Person woman)
	{
		EventPropose event = new EventPropose(Calendar.instance().getDate());
		event.apply(man, woman);
		log.addEvent(event);
	}
	
	public void marry(Person husband, Person wife)
	{
		EventMarriage event = new EventMarriage(Calendar.instance().getDate());
		event.apply(husband, wife);
		log.addEvent(event);
	}
	
	public void haveChild(Person father, Person mother)
	{
		EventChild event = new EventChild(Calendar.instance().getDate());
		event.apply(town, pgen, father, mother);
		log.addEvent(event);
	}
	
	public void death(Person person)
	{
		EventDeath event = new EventDeath(Calendar.instance().getDate());
		event.apply(town, person);
		log.addEvent(event);
	}
}
