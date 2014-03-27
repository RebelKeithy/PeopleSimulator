package com.rebelkeithy.procedural;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class NameGenerator 
{
	private List<Name> firstMaleNames;
	private List<Name> firstFemaleNames;
	private List<Name> lastNames;
	
	private int firstMaleNamesWeight;
	private int firstFemaleNamesWeight;
	private int lastNamesWeight;
	
	public class Name
	{
		public String name;
		public int weight;
		
		public Name(String name, int weight)
		{
			this.name = name;
			this.weight = weight;
		}
	}
	
	public NameGenerator()
	{
		firstMaleNames = new ArrayList<Name>();
		firstFemaleNames = new ArrayList<Name>();
		lastNames = new ArrayList<Name>();
		
		firstMaleNamesWeight = 0;
		firstFemaleNamesWeight = 0;
		lastNamesWeight = 0;
	}
	
	public String getRandomName(char gender)
	{
		return getRandomName(gender, 1f);
	}

	public String getRandomLastName()
	{
		return getRandomLastName(1);
	}
	
	public String getRandomLastName(float modifier)
	{
		String lastName = "";

		Random rand = new Random();
		int choice = (int) (rand.nextInt(lastNamesWeight) * modifier + lastNamesWeight * (1-modifier));
		for(int i = 0; i < lastNames.size(); i++)
		{
			Name name = lastNames.get(i);
			choice -= name.weight;
			if(choice < 0)
			{
				lastName = name.name;
				break;
			}
		}
		
		return lastName;
	}
	
	public String getRandomFirstName(char gender)
	{
		return getRandomFirstName(gender, 1);
	}

	public String getRandomFirstName(char gender, float modifier)
	{
		String firstName = "";
		
		List<Name> names = firstMaleNames;
		int weight = firstMaleNamesWeight;
		if(gender == 'F')
		{
			names = firstFemaleNames;
			weight = firstFemaleNamesWeight;
		}
		
		Random rand = new Random();
		int choice = (int) (rand.nextInt(weight) * modifier + weight * (1-modifier));
		
		for(int i = 0; i < names.size(); i++)
		{
			Name name = names.get(i);
			choice -= name.weight;
			if(choice < 0)
			{
				firstName = name.name;
				break;
			}
		}
		
		return firstName;
	}
	
	public String getRandomName(char gender, float modifier)
	{
		String firstName = "";
		String lastName = "";
		
		List<Name> names = firstMaleNames;
		int weight = firstMaleNamesWeight;
		if(gender == 'F')
		{
			names = firstFemaleNames;
			weight = firstFemaleNamesWeight;
		}
		
		Random rand = new Random();
		int choice = (int) (rand.nextInt(weight) * modifier + weight * (1-modifier));
		float firstProb = 1;
		
		for(int i = 0; i < names.size(); i++)
		{
			Name name = names.get(i);
			choice -= name.weight;
			if(choice < 0)
			{
				firstName = name.name;
				firstProb = name.weight/(float)weight;
				break;
			}
		}
		
		choice = (int) (rand.nextInt(lastNamesWeight) * modifier + lastNamesWeight * (1-modifier));
		
		float lastProb = 1;
		for(int i = 0; i < lastNames.size(); i++)
		{
			Name name = lastNames.get(i);
			choice -= name.weight;
			if(choice < 0)
			{
				lastName = name.name;
				lastProb = name.weight/(float)lastNamesWeight;
				break;
			}
		}
		
		//System.out.println("Creating name with odds: " + (firstProb * lastProb));
		return firstName + " " + lastName;
	}
	
	public void loadLastNames(File file)
	{
		try 
		{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String input = br.readLine();
			while(input != null)
			{
				String[] data = input.split(",");
				data[0] = data[0].charAt(0) + data[0].substring(1).toLowerCase();
				Name name = new Name(data[0], Integer.parseInt(data[2]));
				lastNames.add(name);
				lastNamesWeight += Integer.parseInt(data[2]);

				input = br.readLine();
			}
			
			br.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void loadFirstNames(File file)
	{
		try 
		{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String input = br.readLine();
			while(input != null)
			{
				String[] data = input.split(",");
				Name name = new Name(data[0], Integer.parseInt(data[2]));
				if(data[1].equals("M"))
				{
					firstMaleNames.add(name);
					firstMaleNamesWeight += Integer.parseInt(data[2]);
				}
				else
				{
					firstFemaleNames.add(name);
					firstFemaleNamesWeight += Integer.parseInt(data[2]);
				}
				
				input = br.readLine();
			}
			
			br.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
