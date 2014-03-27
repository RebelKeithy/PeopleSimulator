package com.rebelkeithy.procedural.person;

public enum Relationship
{
	Parent("Father", "Mother"),
	Sibling("Brother", "Sister"),
	StepParent("Stepfather", "Stepmother"),
	StepSibling("Step-Brother", "Step-Sister"),
	HalfSibling("Half Brother", "Half Sister"),
	Child("Son", "Daughter"),
	StepChild("Step Son", "Step Daughter"),
	Spouse("Husband", "Wife"),
	Fiance("Fiance", "Fiancee");
	
	String male;
	String female;
	
	Relationship()
	{
		male = this.name();
		female = this.name();
	}
	
	Relationship(String male, String female)
	{
		this.male = male;
		this.female = female;
	}
	
	public String name(char gender)
	{
		if(gender == 'M')
			return male;
		
		return female;
	}
}
