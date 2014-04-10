package com.rebelkeithy.procedural.events;

import java.util.Random;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.genetics.IntelligenceGenes;

public class EventStartSchool extends Event
{
    private Person child;
    private int year;

    public EventStartSchool(EventManager eventManager, int date, Person child)
    {
        super(eventManager, date);
        this.child = child;
        year = 1;
    }
    
    public EventStartSchool(EventManager eventManager, int date, Person child, int year)
    {
        this(eventManager, date, child);
        this.year = year;
    }
    
    public void apply()
    {
        involved.add(child);
        
        if(year == 1)
        {            
            note = child.fullName() + " has started school";
            eventManager.addDelayedEvent(new EventStartSchool(eventManager, Calendar.instance().getDate() + 365, child, year+1));
        }
        else
        {
            int grade = 3;
            
            Random rand = new Random();
            grade +=  rand.nextInt(((IntelligenceGenes)child.intelligence).getValue()/2 + 1) - 2;
            if(grade > 3)
                grade = 3;
            
            char gradeChar = 'F';
            if(grade == 0)
                gradeChar = 'D';
            if(grade == 1)
                gradeChar = 'C';
            if(grade == 2)
                gradeChar = 'B';
            if(grade == 3)
                gradeChar = 'A';
                
            if(year < 13)
                note = child.fullName() + " finished year " + (year-1) + " of school with grade " + gradeChar; 
            if(year == 13)
                note = child.fullName() + " graduated highschool with grade " + gradeChar;
            if(year != 13)
                eventManager.addDelayedEvent(new EventStartSchool(eventManager, Calendar.instance().getDate() + 365, child, year+1));
        }
    }

    public boolean canApply()
    {
        return child.isAlive();
    }

}
