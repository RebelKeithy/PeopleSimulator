package com.rebelkeithy.procedural.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.rebelkeithy.procedural.person.Person;

public class PersonListRenderer extends DefaultListCellRenderer
{
    private static final long serialVersionUID = 1L;

    public PersonListRenderer()
    {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        Person person = (Person)value;
        
        // Assumes the stuff in the list has a pretty toString
        setText(person.fullName() + " (age " + person.ageYears() + ")");

        if(person.isAlive() == false && person.gender == 'M')
            setBackground(new Color(185, 185, 205));
        else if(person.isAlive() == false)
            setBackground(new Color(205, 185, 185));
        else if(person.gender == 'M')
            setBackground(new Color(225, 225, 255));
        else if(person.gender == 'F')
            setBackground(new Color(255, 225, 225));


        return this;
    }
}
