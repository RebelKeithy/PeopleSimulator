package com.rebelkeithy.procedural.person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.person.genetics.AttractivenessGenes;
import com.rebelkeithy.procedural.person.genetics.Genome;


public class Person 
{
	public String firstName;
	public String lastName;
	public int birthDate;
	
	public char gender;
	public Genome height;
	public Genome eyeColor;
	public Genome hairColor;
	public Genome attractiveness;
	public Genome attraction;
	
	public Map<Relationship, List<Person>> relations;
	
	public Person()
	{
		relations = new HashMap<Relationship, List<Person>>();
		for(Relationship relation : Relationship.values())
			relations.put(relation, new ArrayList<Person>());
	}
	
	public void addRelation(Relationship relation, Person person)
	{
		List<Person> relatives = relations.get(relation);
		
		if(relatives == null)
		{
			relatives = new ArrayList<Person>();
			relations.put(relation, relatives);
		}
		
		relatives.add(person);
	}
	
	public float attractionTo(Person person)
	{
		float sum = ((AttractivenessGenes)attraction).atrib1.value() + ((AttractivenessGenes)attraction).atrib2.value() + ((AttractivenessGenes)attraction).atrib3.value();
		float value1 = ((AttractivenessGenes)(person.attractiveness)).atrib1.value() * ((AttractivenessGenes)(attraction)).atrib1.value() / sum;
		float value2 = ((AttractivenessGenes)(person.attractiveness)).atrib2.value() * ((AttractivenessGenes)(attraction)).atrib2.value() / sum;
		float value3 = ((AttractivenessGenes)(person.attractiveness)).atrib3.value() * ((AttractivenessGenes)(attraction)).atrib3.value() / sum;
		
		float baseAttraction = (value1 + value2 + value3);
		
		baseAttraction -= Math.pow((3*(birthDate - person.birthDate))/((birthDate + person.birthDate)/2), 2);
		
		return baseAttraction;
	}
	
	@Override
	public String toString()
	{
		String ret = "------------------------------------------------\r\n";
		ret += "Name: \t\t" + fullName() + "\r\n";
		ret += "Age: \t\t" + ((Calendar.instance().getDate() - this.birthDate) / 365) + "\r\n";
		ret += "Gender: \t" + gender + "\r\n";
		ret += "Hair Color: \t" + hairColor + " " + hairColor.geneString() + "\r\n";
		ret += "Eye Color: \t" + eyeColor + " " + eyeColor.geneString() + "\r\n";
		ret += "Height: \t" + height + " " + height.geneString() + "\r\n";
		ret += "Attractive: \t" + attractiveness + "\r\n";
		ret += "Attraction: \t" + attraction + "\r\n";
		ret += "\r\n   RELATIONS\r\n";
		for(Relationship relation : relations.keySet())
		{
			List<Person> relatives = relations.get(relation);
			for(Person person : relatives)
			{
				ret += relation.name(person.gender) + ": " + person.firstName + " " + person.lastName + "\r\n";
			}
		}
		
		ret += "------------------------------------------------";
		
		return ret;
	}

	public String fullName() 
	{
		return firstName + " " + lastName;
	}
}
