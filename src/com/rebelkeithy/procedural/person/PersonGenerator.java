package com.rebelkeithy.procedural.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.NameGenerator;
import com.rebelkeithy.procedural.person.genetics.AttractivenessGenes;
import com.rebelkeithy.procedural.person.genetics.EyeColorGenes;
import com.rebelkeithy.procedural.person.genetics.HairColorGenes;
import com.rebelkeithy.procedural.person.genetics.HeightGenes;

public class PersonGenerator 
{
	private NameGenerator ng;
	private Random rand;
	
	public PersonGenerator(NameGenerator ng)
	{
		this.ng = ng;
		rand = new Random();
	}
	
	public Person createRandomPerson()
	{
		if(rand.nextBoolean())
			return createRandomPerson('M');
		
		return createRandomPerson('F');
	}
	
	public Person createRandomPerson(char gender)
	{
		Person person = new Person();

		person.gender = gender;

		person.birthDate = (int) (Calendar.instance().getDate() - (365 * rand.nextDouble()));
		person.hairColor = new HairColorGenes((byte)rand.nextInt(256), (byte)rand.nextInt(4));
		person.eyeColor = new EyeColorGenes(rand.nextBoolean() && rand.nextBoolean(), rand.nextBoolean() && rand.nextBoolean(), rand.nextBoolean() && rand.nextBoolean(), rand.nextBoolean() && rand.nextBoolean(), (byte)rand.nextInt(256));
		person.height = new HeightGenes((byte)rand.nextInt(256));
		person.attractiveness = new AttractivenessGenes((byte)rand.nextInt(256), (byte)rand.nextInt(256), (byte)rand.nextInt(256));
		person.attraction = new AttractivenessGenes((byte)rand.nextInt(256), (byte)rand.nextInt(256), (byte)rand.nextInt(256));
		person.firstName = ng.getRandomFirstName(person.gender);
		person.lastName = ng.getRandomLastName(0.5f);
		
		return person;
	}
	
	public Person createChild(Person father, Person mother)
	{
		Person child = new Person();
		
		if(rand.nextBoolean())
			child.gender = 'M';
		else
			child.gender = 'F';
		
		child.birthDate = Calendar.instance().getDate();
		child.hairColor = father.hairColor.combine(mother.hairColor);
		child.eyeColor = father.eyeColor.combine(mother.eyeColor);
		child.height = father.height.combine(mother.height);
		child.attractiveness = father.attractiveness.combine(mother.attractiveness);
		child.attraction = father.attraction.combine(mother.attraction);
		child.lastName = father.lastName;
		child.firstName = ng.getRandomFirstName(child.gender);

		child.addRelation(Relationship.Parent, father);
		child.addRelation(Relationship.Parent, mother);
		
		List<Person> fathersChildren = new ArrayList<Person>(father.relations.get(Relationship.Child));
		List<Person> mothersChildren = new ArrayList<Person>(mother.relations.get(Relationship.Child));
		
		while(!fathersChildren.isEmpty())
		{
			Person relative = fathersChildren.get(0);
			if(mothersChildren.contains(relative))
			{
				child.addRelation(Relationship.Sibling, relative);
				relative.addRelation(Relationship.Sibling, child);
				mothersChildren.remove(relative);
			}
			else
			{
				child.addRelation(Relationship.HalfSibling, relative);
				relative.addRelation(Relationship.HalfSibling, child);
			}
			fathersChildren.remove(0);
		}
		
		for(Person relative : mothersChildren)
		{
			child.addRelation(Relationship.HalfSibling, relative);
			relative.addRelation(Relationship.HalfSibling, child);
		}
		
		father.addRelation(Relationship.Child, child);
		mother.addRelation(Relationship.Child, child);
		
		
		
		return child;
	}
}
