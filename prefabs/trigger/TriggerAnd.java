package com._604robotics.robotnik.prefabs.trigger;

import com._604robotics.robotnik.trigger.TriggerSource;

public class TriggerAnd implements TriggerSource {
    private final TriggerSource[] triggers;
    
    public TriggerAnd (TriggerSource[] triggers) {
        this.triggers = triggers;
    }
    
    public boolean get () {
        boolean value = true;
        
        for (int i = 0; i < this.triggers.length; i++) {
            value = this.triggers[i].get() && value;
        }
        
        return value;
    }
}