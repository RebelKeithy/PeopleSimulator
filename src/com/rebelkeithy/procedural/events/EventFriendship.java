package com.rebelkeithy.procedural.events;

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
            int friendship = person.friendships.get(other);
            
            if(Math.random() > 0.25)
                friendship++;
            else
                friendship--;
            
            
            
            if(friendship == 20)
            {
                note = person.fullName() + " and " + other.fullName() + " are good friends (" + friendship + ")";
                this.setLog(true);
            }
            if(friendship == 200)
            {
                note = person.fullName() + " and " + other.fullName() + " are best friends (" + friendship + ")";
                this.setLog(true);
            }
            
            //note = person.fullName() + " and " + other.fullName() + " are friends (" + friendship + ")";
            
            person.friendships.put(other, friendship);
            other.friendships.put(person, friendship);
        }
        else
        {
            person.friendships.put(other, 1);
            other.friendships.put(person, 1);
            
            note = person.fullName() + " and " + other.fullName() + " meet each other";
            this.setLog(true);
        }
    }
}
