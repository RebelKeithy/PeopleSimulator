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
	public int deathDate;
	
	public char gender;
	public Genome height;
	public Genome eyeColor;
	public Genome hairColor;
	public Genome attractiveness;
	public Genome attraction;
	public Genome intelligence;
	
	public boolean pregnant;
	
	public Map<Relationship, List<Person>> relations;
	public List<Person> friends;
	public Map<Person, Friendship> friendships;
	
	public Person()
	{
		relations = new HashMap<Relationship, List<Person>>();
		for(Relationship relation : Relationship.values())
			relations.put(relation, new ArrayList<Person>());
		friends = new ArrayList<Person>();
		friendships = new HashMap<Person, Friendship>();
		deathDate = -1;
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
	
	public void effectFriendship(Person person, int amount)
	{
		if(!friendships.containsKey(person))
		{
			friends.add(person);
			friendships.put(person, new Friendship(amount));
		}
		
		Friendship friendship = friendships.get(person);
		friendship.addAmount(amount);
	}
	
	public float attractionTo(Person person)
	{
		float sum = ((AttractivenessGenes)attraction).atrib1.value() + ((AttractivenessGenes)attraction).atrib2.value() + ((AttractivenessGenes)attraction).atrib3.value();
		float value1 = ((AttractivenessGenes)(person.attractiveness)).atrib1.value() * ((AttractivenessGenes)(attraction)).atrib1.value() / sum;
		float value2 = ((AttractivenessGenes)(person.attractiveness)).atrib2.value() * ((AttractivenessGenes)(attraction)).atrib2.value() / sum;
		float value3 = ((AttractivenessGenes)(person.attractiveness)).atrib3.value() * ((AttractivenessGenes)(attraction)).atrib3.value() / sum;
		
		float baseAttraction = (value1 + value2 + value3);
		
		baseAttraction -= Math.pow((3*(ageYears() - person.ageYears()))/((ageYears() + person.ageYears())/2.0), 2);
		
		if(friendships.containsKey(person))
			baseAttraction += friendships.get(person).getCurrent()/200.0;
		
		return baseAttraction;
	}
	
	public void setPregnant(boolean pregnant)
	{
		if(gender == 'M')
			return;
		
		this.pregnant = pregnant;
	}
	
	public boolean isAlive()
	{
		return deathDate == -1;
	}
	
	public boolean isPregnant()
	{
		return pregnant && gender == 'F';
	}
	
	public double ageDiff(Person person)
	{
		return Math.pow((3*(ageYears() - person.ageYears()))/((ageYears() + person.ageYears())/2.0), 2);
	}
	
	@Override
	public String toString()
	{
		String ret = "------------------------------------------------\r\n";
		ret += "Name: \t\t" + fullName() + "\r\n";
		ret += "Age: \t\t" + ageYears() + (isAlive() ? "" : " (deceased)") + "\r\n";
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

	public int ageYears() 
	{
		if(deathDate == -1)
			return (Calendar.instance().getDate() - this.birthDate) / 365;
		
		return (deathDate - this.birthDate) / 365;
	}
	
	@Override
	public int hashCode()
	{
		return fullName().hashCode();
	}

	public boolean isRelated(Person canidate) 
	{
		for(Relationship relation : Relationship.values())
			if(relations.get(relation).contains(canidate))
				return true;
		
		return false;
	}

    public Relationship getRelationTo(Person person)
    {
        for(Relationship rel : Relationship.values())
        {
            if(relations.get(rel).contains(person))
            {
                return rel;
            }
        }
        
        return null;
    }
}
