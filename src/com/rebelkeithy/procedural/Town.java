package com.rebelkeithy.procedural;

import com.rebelkeithy.procedural.person.Person;

import java.util.ArrayList;

public class Town
{
	private ArrayList<Person> people;
	
	public Town()
	{
		people = new ArrayList<Person>();
	}
	
	public void addPerson(Person person)
	{
		people.add(person);
	}

	public void removePerson(Person person) 
	{
		people.remove(person);
	}

	public void printPeople() 
	{
		for(Person person : people)
		{
			System.out.println(person);
		}
	}
}
