package com.rebelkeithy.procedural.person.genetics;

public interface Genome
{
	public Genome combine(Genome gene);
	public String geneString();
}
