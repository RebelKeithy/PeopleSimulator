package com.rebelkeithy.procedural.person;

public class Friendship
{
	private int highest;
	private int prev;
	private int current;
	
	public Friendship()
	{
		highest = 0;
		prev = 0;
		current = 0;
	}
	
	public Friendship(int amount) 
	{
		this();
		
		addAmount(amount);
	}

	public void addAmount(int amount)
	{
		prev = current;
		current += amount;
		
		if(current > highest)
			highest = current;
	}
	
	public int getHighest()
	{
		return highest;
	}
	
	public int getPrev()
	{
		return prev;
	}
	
	public int getCurrent()
	{
		return current;
	}
}
