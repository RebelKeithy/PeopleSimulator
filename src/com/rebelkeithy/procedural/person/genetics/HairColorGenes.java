package com.rebelkeithy.procedural.person.genetics;

import com.rebelkeithy.procedural.Utils;


public class HairColorGenes implements Genome
{
	//public byte height;
	public Gene brightness;
	public Gene red;

	public HairColorGenes() 
	{
		this((byte)0, (byte)0);
	}
	
	public HairColorGenes(byte height, byte red) 
	{
		this.brightness = new Gene(height);
		this.red = new Gene((byte) (red & 0b11));
	}
	
	@Override
	public String toString()
	{
		int value = getHeightValue();
		
		if(red.value() > 1)
			return "Red";
		
		if(value == 0)
			return "White";
		if(value < 3)
			return "Blond";
		if(value < 5)
			return "Brown";
		if(value < 6)
			return "Dark Brown";
		if(value <= 8)
			return "Black";
		
		return "Abnormal";
	}

	public byte getHeightValue()
	{
		return brightness.value();
	}

	@Override
	public Genome combine(Genome abstractGene) 
	{
		HairColorGenes gene = (HairColorGenes)abstractGene;	
		
		HairColorGenes child = new HairColorGenes();
		child.brightness = brightness.combine(gene.brightness);
		child.red = red.combine(gene.red);
		
		return child;
	}

	
	@Override
	public String geneString()
	{
		return "(" + Utils.toBinaryString(brightness.gene) + ") (" + Utils.toBinaryString(red.gene) + ")";
	}
}
