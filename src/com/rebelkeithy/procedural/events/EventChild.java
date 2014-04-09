package com.rebelkeithy.procedural.events;


import org.apache.commons.math3.distribution.GammaDistribution;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.person.Person;

public class EventChild extends Event 
{
    private EventManager eventManager;
	private Person father;
	private Person mother;
	
	private static GammaDistribution walkingAgeGenerator = new GammaDistribution(30, 12.26);
	
	public EventChild(EventManager eventManager, int date, Person father, Person mother) 
	{
		super(eventManager, date);
		this.eventManager = eventManager;
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
		
		int walkingAge = (int) walkingAgeGenerator.sample();
		Event walk = new Event(eventManager, Calendar.instance().getDate() + walkingAge);
		walk.setNote(child.fullName() + " learned to walk");
		walk.involved.add(child);
		eventManager.addDelayedEvent(walk);
		
		note = father.firstName + " and " + mother.fullName() + " had a baby, " + child.fullName();
	}

}
