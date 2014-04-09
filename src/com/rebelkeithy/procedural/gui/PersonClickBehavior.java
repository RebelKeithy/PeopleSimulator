package com.rebelkeithy.procedural.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import com.rebelkeithy.procedural.person.Person;

public class PersonClickBehavior implements MouseListener
{
    Gui gui;
    
    public PersonClickBehavior(Gui gui)
    {
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent arg0)
    {
        @SuppressWarnings("unchecked")
        JList<Person> list = (JList<Person>)arg0.getSource();
        
        if(arg0.getClickCount() == 2)
        {
            int sel = list.locationToIndex(arg0.getPoint());
            Person person = list.getModel().getElementAt(sel);

            gui.setPerson(person);
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }
    
}
