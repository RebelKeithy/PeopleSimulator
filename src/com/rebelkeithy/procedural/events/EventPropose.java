package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.Relationship;

public class EventPropose extends Event
{
	private Person man;
	private Person woman;

	public EventPropose(EventManager eventManager, int date, Person man, Person woman) 
	{
		super(eventManager, date);
		this.man = man;
		this.woman = woman;
	}

	public void apply()
	{
		involved.add(man);
		involved.add(woman);
		
		if(woman.attractionTo(man) > 3 && woman.relations.get(Relationship.Spouse).isEmpty() && woman.relations.get(Relationship.Fiance).isEmpty())
		{
			man.relations.get(Relationship.Fiance).add(woman);
			woman.relations.get(Relationship.Fiance).add(man);
			note = man.fullName() + " proposed to " + woman.fullName() + ", they are now engaged";
			note += " (attraction guy->girl: " + man.attractionTo(woman) + ", girl->guy: " + woman.attractionTo(man) + ")"; 
			int marriageDate = Calendar.instance().getDate() + (int)(80 + Math.random() * 20);
			eventManager.addDelayedEvent(new EventMarriage(eventManager, marriageDate, man, woman));
		}
		else
		{
			note = man.fullName() + " proposed to " + woman.fullName() + " but was rejected";
			note += " (attraction guy->girl: " + man.attractionTo(woman) + ", girl->guy: " + woman.attractionTo(man) + ")"; 
			man.effectFriendship(woman, -2);
		}
	}
}
