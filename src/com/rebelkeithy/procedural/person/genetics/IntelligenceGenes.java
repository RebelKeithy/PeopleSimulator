package com.rebelkeithy.procedural.person.genetics;

import com.rebelkeithy.procedural.Utils;


public class IntelligenceGenes implements Genome
{	
	public Gene intelligence;

	public IntelligenceGenes()
	{
		intelligence = new Gene();
	}
			
	public IntelligenceGenes(byte i) 
	{
		intelligence = new Gene(i);
	}
	
	
	public String toString()
	{
		String string = "";
		
		if(getValue() < 3)
			string = "Dull";
		if(getValue() > 5)
			string = "Smart";
		
		return string;
	}
	
	public byte getValue()
	{
		return intelligence.value();
	}

	@Override
	public IntelligenceGenes combine(Genome abstractGene) 
	{
		IntelligenceGenes gene = (IntelligenceGenes)abstractGene;
		
		IntelligenceGenes child = new IntelligenceGenes();
		child.intelligence = intelligence.combine(gene.intelligence);
		
		return child;
	}
	
	@Override
	public String geneString()
	{
		return "(" + Utils.toBinaryString(intelligence.gene) + ")";
	}
}
