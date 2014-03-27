package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.Relationship;

public class EventEngage extends Event
{

	public EventEngage(int date) 
	{
		super(date);
	}

	public void apply(Person man, Person woman)
	{
		involved.add(man);
		involved.add(woman);
		note = man.fullName() + " and " + woman + " became engaged";
		
		man.relations.get(Relationship.Fiance).add(woman);
		woman.relations.get(Relationship.Fiance).add(man);
	}
}
