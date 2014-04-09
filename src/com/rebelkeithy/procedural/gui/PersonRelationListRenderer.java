package com.rebelkeithy.procedural.gui;

import java.awt.Component;

import javax.swing.JList;

import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.Relationship;

public class PersonRelationListRenderer extends PersonListRenderer
{
    private static final long serialVersionUID = 1L;
    
    private Person relative;

    public PersonRelationListRenderer()
    {
        setOpaque(true);
    }
    
    public void setRelative(Person person)
    {
        relative = person;
    }

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        Person person = (Person)value;
        
        // Assumes the stuff in the list has a pretty toString
        String relation = "";
        
        if(relative != null)
        {
            Relationship rel = relative.getRelationTo(person);
            if(rel != null)
            {
                relation = rel.name(person.gender);
            }
        }
        
        setText(relation + ": " + person.fullName() + " (age " + person.ageYears() + ")");

        return this;
    }
}
