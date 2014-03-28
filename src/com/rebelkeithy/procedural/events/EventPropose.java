package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.Relationship;

public class EventPropose extends Event
{

	public EventPropose(int date) 
	{
		super(date);
	}

	public void apply(Person man, Person woman)
	{
		involved.add(man);
		involved.add(woman);
		
		if(woman.attractionTo(man) > 3 && woman.relations.get(Relationship.Spouse).isEmpty() && woman.relations.get(Relationship.Fiance).isEmpty())
		{
			man.relations.get(Relationship.Fiance).add(woman);
			woman.relations.get(Relationship.Fiance).add(man);
			note = man.fullName() + " proposed to " + woman.fullName() + ", they are now engaged";
			note += " (attraction guy->girl: " + man.attractionTo(woman) + ", girl->guy: " + woman.attractionTo(man) + ")"; 
		}
		else
		{
			note = man.fullName() + " proposed to " + woman.fullName() + " but was rejected";
			note += " (attraction guy->girl: " + man.attractionTo(woman) + ", girl->guy: " + woman.attractionTo(man) + ")"; 
			man.effectFriendship(woman, -2);
		}
	}
}
