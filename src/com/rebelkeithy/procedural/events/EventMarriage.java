package com.rebelkeithy.procedural.events;

import java.util.List;

import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.Relationship;

public class EventMarriage extends Event
{
	public EventMarriage(int date) 
	{
		super(date);
	}

	public void apply(Person husband, Person wife)
	{
		involved.add(husband);
		involved.add(wife);
		note = husband.fullName() + " married " + wife.fullName();
		
		husband.relations.get(Relationship.Fiance).remove(wife);
		wife.relations.get(Relationship.Fiance).remove(husband);
		
		husband.relations.get(Relationship.Spouse).add(wife);
		wife.relations.get(Relationship.Spouse).add(husband);
		wife.lastName = husband.lastName;
		
		List<Person> husbandsChildren = husband.relations.get(Relationship.Child);
		List<Person> wifesChildren = wife.relations.get(Relationship.Child);
		
		for(Person child : husbandsChildren)
		{
			wife.relations.get(Relationship.StepChild).add(child);
		}
		
		for(Person child : wifesChildren)
		{
			husband.relations.get(Relationship.StepChild).add(child);
		}
	}
}
