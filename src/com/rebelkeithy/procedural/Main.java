package com.rebelkeithy.procedural;

import java.io.File;

import com.rebelkeithy.procedural.events.EventManager;
import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.PersonGenerator;

public class Main 
{
	public static void main(String[] args)
	{
		NameGenerator gen = new NameGenerator();
		PersonGenerator pgen = new PersonGenerator(gen);
		Town town = new Town();
		EventManager eventManager = new EventManager(pgen, town);
		
		gen.loadFirstNames(new File("firstNames.txt"));
		gen.loadLastNames(new File("lastNames.txt"));

		Calendar.instance().advance(365 * 1900);
		Person father = pgen.createRandomPerson('M');
		Person mother = pgen.createRandomPerson('F');
		town.addPerson(father);
		town.addPerson(mother);

		Calendar.instance().advance((int) (365*(16 + Math.random() * 10)));
		eventManager.marry(father, mother);
		Calendar.instance().advance((int) (90 + 365 * Math.random() * 3));
		eventManager.haveChild(father, mother);
		Calendar.instance().advance((int) (300 + 365 * Math.random() * 3));
		eventManager.haveChild(father, mother);
		Calendar.instance().advance((int) (365*10 + 365 * Math.random() * 3));
		Calendar.instance().advance((int) (365*30 + 365 * Math.random() * 10));
		eventManager.death(father);
		
		town.printPeople();
		System.out.println();
		eventManager.printLog();
		
		/*
		father.addRelation(Relationship.Spouse, mother);
		mother.addRelation(Relationship.Spouse, father);
		mother.lastName = father.lastName;
		
		Person child = pgen.createChild(father, mother);
		Person child2 = pgen.createChild(father, mother);
		System.out.println(father);
		System.out.println(mother);
		//System.out.println(child);
		//System.out.println(child2);
		System.out.println("Attraction of father->mother: " + father.attractionTo(mother));
		System.out.println("Attraction of mother->father: " + mother.attractionTo(father));
		*/
		
		
	}
}
