package com.rebelkeithy.procedural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.rebelkeithy.procedural.events.EventFactory;
import com.rebelkeithy.procedural.events.EventFriendship;
import com.rebelkeithy.procedural.events.EventManager;
import com.rebelkeithy.procedural.events.EventSickness;
import com.rebelkeithy.procedural.events.EventStartSchool;
import com.rebelkeithy.procedural.events.EventType;
import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.Relationship;

public class Town
{
	private ArrayList<Person> people;
	private ArrayList<Person> deceased;
	private Map<Integer, Integer> mortalityRate;
	private EventManager eventManager;
	private Random rand = new Random();
	
	public Town()
	{
		people = new ArrayList<Person>();
		deceased = new ArrayList<Person>();
		mortalityRate = new HashMap<Integer, Integer>();
	}

	public void setEventManager(EventManager eventManager) 
	{
		this.eventManager = eventManager;
	}
	
	public void timeStep(int days)
	{
		eventManager.timeStep();
		
		for(int i = 0; i < people.size(); i++)
		{
			Person person = people.get(i);
			
			if(person.ageYears() >= 18 && person.gender == 'M')
			{
				if(rand.nextDouble() > 0.95 && person.relations.get(Relationship.Spouse).size() == 0 && person.relations.get(Relationship.Fiance).size() == 0)
				{
					int j = rand.nextInt(people.size());
					Person canidate = people.get(j);
					
					if(person.friendships.size() > 0)
					{
					    int f = rand.nextInt(person.friendships.size());
		                List<Person> friends = new ArrayList<Person>(person.friendships.keySet());
					    canidate = friends.get(f);
					}
					
					if(canidate.gender != person.gender && !person.isRelated(canidate) && canidate.ageYears() > 16 && canidate.relations.get(Relationship.Spouse).isEmpty())
					{
						if(person.attractionTo(canidate) > 4)
						{
							eventManager.preformEvent(EventFactory.createEvent(EventType.Propose, eventManager, person, canidate));
							//eventManager.propose(person, canidate);
						}
					}
				}
			}
			
			if(person.ageYears() > 4 && rand.nextDouble() < 1/(float)(10*person.friendships.size()))
			{
			    int j = rand.nextInt(people.size());
			    Person randomPerson = people.get(j);
			    
			    if(!person.friendships.containsKey(randomPerson) && person != randomPerson && person.ageDiff(randomPerson) < 1)
			    {
			        eventManager.preformEvent(new EventFriendship(eventManager, Calendar.instance().getDate(), person, randomPerson));
			    }
			}
			
			// Update friendship
			if(!person.friendships.isEmpty() && rand.nextDouble() > 1/(float)(100*person.friendships.size()))
			{
			    int j = rand.nextInt(person.friendships.size());
			    List<Person> friends = new ArrayList<Person>(person.friendships.keySet());
			    Person friend = friends.get(j);
			    eventManager.preformEvent(new EventFriendship(eventManager, Calendar.instance().getDate(), person, friend));
			}
			
			if(person.ageYears() == 5 && Calendar.instance().getDay() == 200)
			{
			    eventManager.preformEvent(new EventStartSchool(eventManager, Calendar.instance().getDate(), person));
			}
			
			if(rand.nextDouble() < 1/(2*365.0) && person.gender == 'F' && !person.isPregnant() && person.ageYears() < 45 && person.relations.get(Relationship.Spouse).size() != 0 && person.relations.get(Relationship.Spouse).get(0).isAlive())
			{
				eventManager.preformEvent(EventFactory.createEvent(EventType.Pregnant, eventManager, person.relations.get(Relationship.Spouse).get(0), person));
				//eventManager.makePregnant(person.relations.get(Relationship.Spouse).get(0), person);
			}
			
			if(person.ageYears() > 65 && (rand.nextDouble()) > 0.9999)
			{
				eventManager.preformEvent(EventFactory.createEvent(EventType.Death, eventManager, person));
				//eventManager.death(person);
				i--;
			}
			
			if(rand.nextDouble() < 1/(2.0*365.0))
			{
				eventManager.preformEvent(new EventSickness(eventManager, Calendar.instance().getDate(), person, 0));
			}
		}
		
		if(rand.nextDouble() < 1/365.0)
		{
			//eventManager.preformEvent(EventFactory.createEvent(EventType.NewPerson, eventManager));
			//eventManager.newPerson();
		}
	}
	
	public void addPerson(Person person)
	{
		people.add(person);
	}
	
	public void killPerson(Person person)
	{
		if(people.contains(person))
		{
			people.remove(person);
			deceased.add(person);
			int age = person.ageYears();
			if(mortalityRate.containsKey(age))
			{
				int count = mortalityRate.get(age);
				mortalityRate.put(age, count + 1);
			}
			else
			{
				mortalityRate.put(age, 1);
			}
		}
	}
	
	public void printMortalityRate()
	{
		int totalDeaths = deceased.size();
		for(int age : mortalityRate.keySet())
		{
			int deaths = mortalityRate.get(age);
			System.out.println(age + ": " + (deaths/(float)totalDeaths));
		}
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

	public void printPopulation() 
	{
		for(Person person : people)
		{
			System.out.println(person.fullName());
		}
	}

	public String[] getPopulation() 
	{
		String[] pop = new String[people.size()];
		
		for(int i = 0; i < people.size(); i++)
		{
			pop[i] = people.get(i).fullName();
		}
		
		return pop;
	}
	
	public String[] getAllPeopleNames()
	{
		String[] pop = new String[people.size() + deceased.size()];
		
		for(int i = 0; i < people.size(); i++)
		{
			pop[i] = people.get(i).fullName();
		}
		
		for(int i = 0; i < deceased.size(); i++)
		{
			pop[i+people.size()] = deceased.get(i).fullName();
		}
		
		return pop;
	}
	
	public Person[] getAllPeople()
	{
	    Person[] pop = new Person[people.size() + deceased.size()];

        for(int i = 0; i < people.size(); i++)
        {
            pop[i] = people.get(i);
        }
        
        for(int i = 0; i < deceased.size(); i++)
        {
            pop[i+people.size()] = deceased.get(i);
        }
	    
        return pop;
	}

	public Person getPerson(String fullname) 
	{
		for(Person person : people)
		{
			if(person.fullName().equals(fullname))
			{
				return person;
			}
		}

		for(Person person : deceased)
		{
			if(person.fullName().equals(fullname))
			{
				return person;
			}
		}
		
		
		return null;
	}

	public EventManager getEventManager() 
	{
		return eventManager;
	}
}
