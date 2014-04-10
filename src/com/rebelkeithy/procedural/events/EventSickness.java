package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.person.Person;

public class EventSickness extends Event
{
	private int stage;
	private Person person;
	
	public EventSickness(EventManager eventManager, int date, Person person, int stage) 
	{
		super(eventManager, date);
		
		this.person = person;
		this.stage = stage;
	}
	
	public void apply()
	{
		involved.add(person);
		
		if(stage == 0)
		{
			note = person.fullName() + " contracted sickness";
			
			int nextStageDate = (int) (Calendar.instance().getDate() + 3 + Math.random() * 7);
            Event event = new EventSickness(eventManager, nextStageDate, person, stage+1);
            event.setLog(false);
            eventManager.addDelayedEvent(event);
		}
		else
		{
			if(Math.random() > 0.65)
			{
				int nextStageDate = (int) (Calendar.instance().getDate() + 3 + Math.random() * 7);
				if(Math.pow(Math.random(), stage/2) < 0.1)
				{
					note = person.fullName() + " is on the verge of death";
					this.setLog(true);
					eventManager.addDelayedEvent(new EventDeath(eventManager, nextStageDate, person));
				}
				else
				{
					note = person.fullName() + "'s sickness is worse";
					Event event = new EventSickness(eventManager, nextStageDate, person, stage+1);
					event.setLog(false);
					eventManager.addDelayedEvent(event);
				}
			}
			else
			{
                this.setLog(true);
				note = person.fullName() + "'s sickness is gone";
			}
		}
	}
}
