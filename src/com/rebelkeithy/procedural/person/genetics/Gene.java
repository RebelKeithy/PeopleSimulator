package com.rebelkeithy.procedural.person.genetics;

import com.rebelkeithy.procedural.Utils;

public class Gene
{
	public byte gene;
	
	public Gene()
	{
		
	}
	
	public Gene(byte gene) 
	{
		this.gene = gene;
	}

	public void setBit(int bit, boolean value)
	{
		if(value)
			gene |= (1 << bit);
		else
			gene &= ~(1 << bit);
	}
	
	public boolean bit(int i)
	{
		return ((gene >> i) & 1) == 1;
	}
	
	public byte value()
	{
		byte value = 0;
		for(int i = 0; i < 8; i++)
		{
			value += (gene >> i) & 1;
		}
		return value;
	}
	
	public Gene combine(Gene other)
	{
		byte newGene = 0;
		
		for(int i = 0; i < 8; i += 2)
		{
			int b0 = 0;
			int b1 = 0;
			if(Utils.rand.nextBoolean()) // get b0 from father
			{
				if(Utils.rand.nextBoolean())
					b0 |= (gene >> i) & 1;
				else
					b0 |= (gene >> (i + 1)) & 1;
				
				if(Utils.rand.nextBoolean())
					b1 |= (other.gene >> i) & 1;
				else
					b1 |= (other.gene >> (i + 1)) & 1;
			}
			else
			{
				if(Utils.rand.nextBoolean())
					b1 |= (gene >> i) & 1;
				else
					b1 |= (gene >> (i + 1)) & 1;
				
				if(Utils.rand.nextBoolean())
					b0 |= (other.gene >> i) & 1;
				else
					b0 |= (other.gene >> (i + 1)) & 1;
			}
			
			newGene |= (b0 << i);
			newGene |= (b1 << (i + 1));
		}
		
		/*
		for(int i = 0; i < 8; i++)
		{
			if(Utils.rand.nextBoolean())
				newGene |= this.gene & (1 << i);
			else
				newGene |= other.gene & (1 << i);
		}
		*/
				
		Gene child = new Gene();
		child.gene = newGene;
		
		return child;
	}
}
