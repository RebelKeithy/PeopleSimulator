package com.rebelkeithy.procedural.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.rebelkeithy.procedural.Town;
import com.rebelkeithy.procedural.Utils;
import com.rebelkeithy.procedural.events.Event;
import com.rebelkeithy.procedural.person.Person;
import com.rebelkeithy.procedural.person.Relationship;

public class Gui extends JFrame implements ListSelectionListener, ActionListener
{
	Town town;
	
	String[] people;
	JList<String> list;
	
	JLabel personName;
	JLabel lAge;
	JLabel lBirth;
	JLabel lDeath;
	JLabel lEyeColor;
	JLabel lHairColor;
	JLabel lHeight;
	JList<String> relations;
	
	JList<String> eventList;
	JTextArea eventText;
	
	JTextField search;
	
	Event[] events;
	
	public Gui(Town town)
	{
		this.town = town;
		
		setTitle("Simple example");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		panel.setLayout(new GridLayout(1, 2));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		panel.add(leftPanel);
		//panel.setLayout(new BorderLayout());
		//panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
		people = town.getAllPeople();
		
		list = new JList<String>(people);
		list.setVisible(true);
		list.addListSelectionListener(this);
		
		JScrollPane pane = new JScrollPane();
		pane.getViewport().add(list);
		pane.setPreferredSize(new Dimension(250, 200));
		
		personName = new JLabel("Name");
		lAge = new JLabel("");
		lBirth = new JLabel("");
		lDeath = new JLabel("");
		lEyeColor = new JLabel("");
		lHairColor = new JLabel("");
		lHeight = new JLabel("");
		relations = new JList<String>();
		eventList = new JList<String>();
		eventList.addListSelectionListener(this);
		eventText = new JTextArea("Blank Text");
		search = new JTextField("");
		search.addActionListener(this);
		search.setMaximumSize(new Dimension(Integer.MAX_VALUE, search.getPreferredSize().height));
		

		leftPanel.add(search);
		leftPanel.add(pane);
		
		JPanel personPanel = new JPanel();
		personPanel.setLayout(new BoxLayout(personPanel, BoxLayout.PAGE_AXIS));
		personPanel.add(personName);
		personPanel.add(lAge);
		personPanel.add(lBirth);
		personPanel.add(lDeath);
		personPanel.add(lEyeColor);
		personPanel.add(lHairColor);
		personPanel.add(lHeight);
		personPanel.add(relations);
		
		
		JPanel eventPanel = new JPanel();
		eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.PAGE_AXIS));
		
		JPanel familyPanel = new JPanel();
		
		JScrollPane familyScrollPane = new JScrollPane();
		familyScrollPane.getViewport().add(relations);
		familyPanel.add(familyScrollPane);
		
		JScrollPane eventScrollPane = new JScrollPane();
		eventScrollPane.getViewport().add(eventList);
		eventPanel.add(eventScrollPane);
		//eventPanel.add(eventText);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Person", personPanel);
		tabbedPane.addTab("Family", familyPanel);
		tabbedPane.addTab("Events", eventPanel);
		
		panel.add(tabbedPane);
		
		
		
		//panel.add(list);
	}
	
	public void valueChanged(ListSelectionEvent e) 
	{
		if (e.getValueIsAdjusting() == false) 
		{
			if(e.getSource() == list)
		    {
		        if (list.getSelectedIndex() != -1)
		        {
		        	String name = list.getSelectedValue();
		        	Person person = town.getPerson(name);
		            personName.setText(person.fullName());
		            lAge.setText("Age: " + person.ageYears());
		            lBirth.setText("Born: " + Utils.formatDate(person.birthDate));
		            if(person.isAlive())
		            	lDeath.setText("Died: N/A");
		            else
		            	lDeath.setText("Died: " + Utils.formatDate(person.deathDate));
		            lEyeColor.setText("Eye Color: " + person.eyeColor);
		            lHairColor.setText("Hair Color: " + person.hairColor);
		            lHeight.setText("Height: " + person.height);
		            
		            Vector<String> listValues = new Vector<String>();
		            for(Relationship rel : Relationship.values())
		            {
		            	List<Person> rels = person.relations.get(rel);
		            	for(Person p : rels)
		            	{
		            		listValues.add(rel.name(p.gender) + ": " + p.fullName());
		            	}
		            }
		            
		            relations.setListData(listValues);
		            
		            events = town.getEventManager().getEventsByPerson(person);
		            String[] data = new String[events.length];
		            
		            for(int i = 0; i < events.length; i++)
		            {
		            	data[i] = Utils.formatDate(events[i].getDate()) + ": " +  events[i].getNote();
		            }
		            
		            
		            eventList.setListData(data);
		        }
		    }
			
			if(e.getSource() == eventList)
			{
				if(eventList.getSelectedIndex() != -1)
				{
					int index = eventList.getSelectedIndex();
					Event event = events[index];
					
					eventText.setText(Utils.formatDate(event.getDate()) + ": " + event.getNote());
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource() == search)
		{
			if(search.getText().length() > 0)
			{
				Vector<String>subset = new Vector<String>();
				for(String name : people)
				{
					if(name.contains(search.getText()))
					{
						subset.add(name);
					}
				}
				
				list.setListData(subset);
			}
			else
			{
				list.setListData(people);
			}
		}
	}
}
