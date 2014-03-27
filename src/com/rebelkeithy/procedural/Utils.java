package com.rebelkeithy.procedural;

import java.util.Random;

public class Utils 
{
	public static Random rand = new Random();
	
	public static String toBinaryString(byte num)
	{
		String ret = "";
		for(int i = 0; i < 8; i++)
		{
			if(((num >> i) & 1) == 1)
			{
				ret = "1" + ret;
			}
			else
			{
				ret = "0" + ret;
			}
		}
		
		return ret;
	}

	public static String formatDate(int date) 
	{
		int year = date/365;
		int day = date%365;
		int month = day/30;
		day = day%30;
		
		return year + "-" + month + "-" + day;
	}
}
