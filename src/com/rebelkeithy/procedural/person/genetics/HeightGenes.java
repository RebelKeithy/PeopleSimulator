package com.rebelkeithy.procedural.person.genetics;

import com.rebelkeithy.procedural.Utils;


public class HeightGenes implements Genome
{
	//public byte height;
	public Gene height;

	public HeightGenes() 
	{
		this((byte)0);
	}
	
	public HeightGenes(byte height) 
	{
		this.height = new Gene(height);
	}
	
	public String toString()
	{
		int value = getHeightValue();
		if(value == 0)
			return "Very Short";
		if(value < 3)
			return "Short";
		if(value < 6)
			return "Average";
		if(value < 8)
			return "Tall";
		if(value == 8)
			return "Very Tall";
		
		return "Abnormal";
	}

	public byte getHeightValue()
	{
		return height.value();
	}

	@Override
	public Genome combine(Genome abstractGene) 
	{
		HeightGenes gene = (HeightGenes)abstractGene;
		
		Gene newHeight = height.combine(gene.height);
				
		HeightGenes child = new HeightGenes();
		child.height = newHeight;
		
		return child;
	}
	
	@Override
	public String geneString()
	{
		return "(" + Utils.toBinaryString(height.gene) + ")";
	}

}
