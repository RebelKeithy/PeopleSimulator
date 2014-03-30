package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.person.Person;

public class EventDeath extends Event
{
	private Person person;

	public EventDeath(EventManager eventManager, int date, Person person) 
	{
		super(eventManager, date);
		this.person = person;
	}
	
	public void apply()
	{
		eventManager.town.killPerson(person);
		person.deathDate = Calendar.instance().getDate();
		
		involved.add(person);
		this.note = person.fullName() + " died at the age of " + ((Calendar.instance().getDate() - person.birthDate)/365);
	}
}
