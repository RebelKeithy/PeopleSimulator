package com.rebelkeithy.procedural;

public class Calendar 
{
	private static Calendar instance = new Calendar();
	
	private int date;
	
	private Calendar()
	{
		date = 0;
	}
	
	public static Calendar instance()
	{
		return instance;
	}
	
	public void advance()
	{
		advance(1);
	}
	
	public void advance(int days)
	{
		date += days;
	}
	
	public int getDate()
	{
		return date;
	}
}
