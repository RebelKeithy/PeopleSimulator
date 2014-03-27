package com.rebelkeithy.procedural.person.genetics;

import com.rebelkeithy.procedural.Utils;

public class AttractivenessGenes implements Genome
{
	public Gene atrib1;
	public Gene atrib2;
	public Gene atrib3;
	
	public AttractivenessGenes()
	{
		atrib1 = new Gene();
		atrib2 = new Gene();
		atrib3 = new Gene();
	}

	public AttractivenessGenes(byte value1, byte value2, byte value3) 
	{
		atrib1 = new Gene(value1);
		atrib2 = new Gene(value2);
		atrib3 = new Gene(value3);
	}

	@Override
	public Genome combine(Genome abstractGene) 
	{
		AttractivenessGenes gene = (AttractivenessGenes)abstractGene;
		
		AttractivenessGenes child = new AttractivenessGenes();
		
		child.atrib1 = atrib1.combine(gene.atrib1);
		child.atrib2 = atrib2.combine(gene.atrib2);
		child.atrib3 = atrib3.combine(gene.atrib3);
		
		return child;
	}

	@Override
	public String geneString() 
	{
		return "(" + Utils.toBinaryString(atrib1.value()) + ") (" + Utils.toBinaryString(atrib2.gene) + ") (" + Utils.toBinaryString(atrib3.gene) + ")";
	}
	
	@Override
	public String toString()
	{
		return "" + atrib1.value() + "/" + atrib2.value() + "/" + atrib3.value();
	}
	
	
}
