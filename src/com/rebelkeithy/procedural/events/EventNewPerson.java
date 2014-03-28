package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.person.Person;

public class EventNewPerson extends Event
{

	public EventNewPerson(EventManager eventManager, int date) 
	{
		super(eventManager, date);
	}

	public void apply()
	{
		Person person = eventManager.pgen.createRandomPerson();
		person.birthDate -= 365 * (18 + (Math.random() * 20));
		eventManager.town.addPerson(person);
		note = person.fullName() + " has moved into town";
	}
}
