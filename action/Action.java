package com._604robotics.robotnik.action;

import com._604robotics.robotnik.data.DataAccess;
import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.trigger.TriggerAccess;
import java.util.Hashtable;

public class Action {
    private final Hashtable fields = new Hashtable();
    
    private final Hashtable dataLinks    = new Hashtable();
    private final Hashtable triggerLinks = new Hashtable();
    
    public void begin () {}
    public void run () {}
    public void end () {}
    
    protected Field field (String name, double defaultValue) {
        final Field field = new Field(defaultValue);
        fields.put(name, field);
        return field;
    }
    
    protected Field field (String name, boolean defaultValue) {
        return field(name, defaultValue ? 1 : 0);
    }
    
    protected DataAccess data (String name) {
        return (DataLink) dataLinks.put(name, new DataLink());
    }
    
    protected TriggerAccess trigger (String name) {
        return (TriggerLink) triggerLinks.put(name, new TriggerLink());
    }
    
    protected Iterator iterateFields () {
        return new Iterator(fields);
    }
    
    protected Iterator iterateDataLinks () {
        return new Iterator(dataLinks);
    }
    
    protected Iterator iterateTriggerLinks () {
        return new Iterator(triggerLinks);
    }
}