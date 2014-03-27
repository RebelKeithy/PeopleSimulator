package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.Town;
import com.rebelkeithy.procedural.person.Person;

public class EventDeath extends Event
{

	public EventDeath(int date) 
	{
		super(date);
	}
	
	public void apply(Town town, Person person)
	{
		town.removePerson(person);
		
		involved.add(person);
		this.note = person.fullName() + " died at the age of " + ((Calendar.instance().getDate() - person.birthDate)/365);
	}
}
