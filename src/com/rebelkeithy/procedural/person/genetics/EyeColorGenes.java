package com.rebelkeithy.procedural.person.genetics;

import com.rebelkeithy.procedural.Utils;


public class EyeColorGenes implements Genome
{
	public static int brownBlueDom = 0;
	public static int brownBlueRec = 1;
	public static int greenBlueDom = 2;
	public static int greenBlueRec = 3;
	
	public Gene color;
	public Gene intensity;

	public EyeColorGenes()
	{
		color = new Gene();
		intensity = new Gene();
	}
			
	public EyeColorGenes(boolean bbd, boolean bbr, boolean gbd, boolean gbr, byte i) 
	{
		color = new Gene();
		intensity = new Gene();
		
		setBrownBlue(bbd, bbr);
		setGreenBlue(gbd, gbr);
		setIntensity(i);
	}

	public void setBrownBlue(boolean dominate, boolean reccessive)
	{
		color.setBit(brownBlueDom, dominate);
		color.setBit(brownBlueRec, reccessive);
	}
	
	public void setGreenBlue(boolean dominate, boolean reccessive)
	{
		color.setBit(greenBlueDom, dominate);
		color.setBit(greenBlueRec, reccessive);
	}
	
	public void setIntensity(byte value)
	{
		intensity.gene = value;
	}
	
	
	public String toString()
	{
		String colorString = "";
		if(color.bit(brownBlueDom) == true || color.bit(brownBlueRec) == true)
			colorString = "Brown";
		else if(color.bit(greenBlueDom) == true || color.bit(greenBlueRec) == true)
			colorString = "Green";
		else
			colorString = "Blue";

		if(getIntensityValue() < 3)
			colorString = "Light " + colorString;
		if(getIntensityValue() > 5)
			colorString = "Dark " + colorString;
		
		if((color.bit(brownBlueDom) == true || color.bit(brownBlueRec) == true) && getIntensityValue() > 6)
			colorString = "Black";
		
		return colorString;
	}
	
	public byte getIntensityValue()
	{
		return intensity.value();
	}

	@Override
	public EyeColorGenes combine(Genome abstractGene) 
	{
		EyeColorGenes gene = (EyeColorGenes)abstractGene;
		
		EyeColorGenes child = new EyeColorGenes();
		child.color = color.combine(gene.color);
		child.intensity = intensity.combine(gene.intensity);
		
		return child;
	}
	
	@Override
	public String geneString()
	{
		return "(" + Utils.toBinaryString(color.gene) + ") (" + Utils.toBinaryString(intensity.gene) + ")";
	}
}
