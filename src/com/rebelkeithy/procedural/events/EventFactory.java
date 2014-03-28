package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.person.Person;

public class EventFactory 
{
	public static Event createEvent(EventType type, EventManager manager, Person... people)
	{
		if(type == EventType.Child)
			return new EventChild(manager, Calendar.instance().getDate(), people[0], people[1]);
		if(type == EventType.Death)
			return new EventDeath(manager, Calendar.instance().getDate(), people[0]);
		if(type == EventType.Marriage)
			return new EventMarriage(manager, Calendar.instance().getDate(), people[0], people[1]);
		if(type == EventType.NewPerson)
			return new EventNewPerson(manager, Calendar.instance().getDate());
		if(type == EventType.Pregnant)
			return new EventPregnant(manager, Calendar.instance().getDate(), people[0], people[1]);
		if(type == EventType.Propose)
			return new EventPropose(manager, Calendar.instance().getDate(), people[0], people[1]);
		
		return null;
	}
}
