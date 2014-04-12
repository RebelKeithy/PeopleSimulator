package com.rebelkeithy.procedural.events;

import com.rebelkeithy.procedural.Calendar;
import com.rebelkeithy.procedural.person.Friendship;
import com.rebelkeithy.procedural.person.Person;

public class EventFriendship extends Event
{
    public Person person;
    public Person other;

    public EventFriendship(EventManager eventManager, int date, Person person, Person other)
    {
        super(eventManager, date);
        this.person = person;
        this.other = other;
    }

    public void apply()
    {
        note = "generic friendship event";
        involved.add(person);
        involved.add(other);
        
        this.setLog(false);
        
        if(person.friendships.containsKey(other))
        {
            int change = 0;
            
            if(Math.random() > 0.025)
            	change = 10;
            else
            	change = -10;
            

            person.effectFriendship(other, change);
            other.effectFriendship(person, change);
            Friendship friendship = person.friendships.get(other);
            
            if(friendship.getHighest() >= 20 && friendship.getPrev() < 20)
            {
                note = person.fullName() + " and " + other.fullName() + " are good friends";// (" + friendship + ")";
                this.setLog(true);
            }
            if(friendship.getHighest() >= 200 && friendship.getPrev() < 200)
            {
                note = person.fullName() + " and " + other.fullName() + " are best friends";// (" + friendship + ")";
                this.setLog(true);
            }
        }
        else
        {
        	person.effectFriendship(other, 1);
        	other.effectFriendship(person, 1);
            
            note = person.fullName() + " and " + other.fullName() + " meet each other";
            this.setLog(true);
        }
        
        int nextInteractionDate = (int) (10 * (rand.nextGaussian() + 5));
        eventManager.addDelayedEvent(new EventFriendship(eventManager, Calendar.instance().getDate() + nextInteractionDate, person, other));
    }

    public boolean canApply()
    {
        return person.isAlive() && other.isAlive();
    }
}
