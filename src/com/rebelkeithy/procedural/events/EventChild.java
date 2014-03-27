package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.Town;
import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.PersonGenerator;

public class EventChild extends Event 
{
	public EventChild(int date) 
	{
		super(date);
	}
	
	public void apply(Town town, PersonGenerator pgen, Person father, Person mother)
	{
		Person child = pgen.createChild(father, mother);
		town.addPerson(child);
		
		involved.add(father);
		involved.add(mother);
		involved.add(child);
		note = father.firstName + " and " + mother.fullName() + " had a baby, " + child.fullName();
	}

}
