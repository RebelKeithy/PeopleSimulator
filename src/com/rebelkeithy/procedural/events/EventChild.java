package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.person.Person;

public class EventChild extends Event 
{
	private Person father;
	private Person mother;
	
	public EventChild(EventManager eventManager, int date, Person father, Person mother) 
	{
		super(eventManager, date);
		this.father = father;
		this.mother = mother;
	}
	
	public void apply()
	{
		Person child = eventManager.pgen.createChild(father, mother);
		eventManager.town.addPerson(child);
		mother.setPregnant(false);
		
		involved.add(father);
		involved.add(mother);
		involved.add(child);
		note = father.firstName + " and " + mother.fullName() + " had a baby, " + child.fullName();
	}

}
