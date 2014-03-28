package com.rebelkeithy.procedural;

import com.rebelkeithy.procedural.events.EventManager;
import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.Relationship;

import java.util.ArrayList;
import java.util.Random;

public class Town
{
	private ArrayList<Person> people;
	private EventManager eventManager;
	private Random rand = new Random();
	
	public Town()
	{
		people = new ArrayList<Person>();
	}

	public void setEventManager(EventManager eventManager) 
	{
		this.eventManager = eventManager;
	}
	
	public void timeStep(int days)
	{
		for(int i = 0; i < people.size(); i++)
		{
			Person person = people.get(i);
			
			if(Math.random() < 1/80f && person.gender == 'M' && person.relations.get(Relationship.Fiance).size() > 0)
			{
				eventManager.marry(person, person.relations.get(Relationship.Fiance).get(0));
			}
			
			if(person.ageYears() >= 18 && person.gender == 'M')
			{
				if(rand.nextDouble() > 0.9 && person.relations.get(Relationship.Spouse).size() == 0 && person.relations.get(Relationship.Fiance).size() == 0)
				{
					int j = rand.nextInt(people.size());
					Person canidate = people.get(j);
					if(canidate.gender != person.gender && !person.isRelated(canidate) && canidate.ageYears() > 16 && canidate.relations.get(Relationship.Spouse).isEmpty())
					{
						if(person.attractionTo(canidate) > 4)
						{
							eventManager.propose(person, canidate);
						}
					}
				}
			}
			
			if(rand.nextDouble() > 0.999 && person.gender == 'F' && person.ageYears() < 45 && person.relations.get(Relationship.Spouse).size() != 0)
			{
				eventManager.haveChild(person.relations.get(Relationship.Spouse).get(0), person);
			}
			
			if(person.ageYears() > 65 && (person.ageYears()/20.0 + rand.nextDouble()) > 0.9999)
			{
				eventManager.death(person);
				i--;
			}
		}
	}
	
	public void addPerson(Person person)
	{
		people.add(person);
	}

	public void removePerson(Person person) 
	{
		people.remove(person);
	}
	
	public int population()
	{
		return people.size();
	}
	
	public int population(char gender)
	{
		int pop = 0;
		for(Person person : people)
		{
			if(person.gender == gender)
				pop++;
		}
		return pop;
	}

	public void printPeople() 
	{
		for(Person person : people)
		{
			System.out.println(person);
		}
	}
}
