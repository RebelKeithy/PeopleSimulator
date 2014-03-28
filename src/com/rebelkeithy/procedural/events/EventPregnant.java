package com.rebelkeithy.procedural.events;

import java.util.Random;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.person.Person;

public class EventPregnant extends Event
{
	private Person father;
	private Person mother;

	public EventPregnant(EventManager eventManager, int date, Person father, Person mother) 
	{
		super(eventManager, date);
		this.father = father;
		this.mother = mother;
	}
	
	public void apply()
	{
		involved.add(mother);
		involved.add(father);
		mother.setPregnant(true);
		
		Random rand = new Random();
		int birthDate = Calendar.instance().getDate() + 58 + (int)(rand.nextGaussian() * 10);
		
		eventManager.addDelayedEvent(new EventChild(eventManager, birthDate, father, mother));
		
		note = mother.fullName() + " became pregnant";
	}

}
