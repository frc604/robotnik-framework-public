package com._604robotics.robotnik.prefabs.trigger;

import com._604robotics.robotnik.trigger.TriggerSource;

public class TriggerOr implements TriggerSource {
    private final TriggerSource[] triggers;
    
    public TriggerOr (TriggerSource[] triggers) {
        this.triggers = triggers;
    }
    
    public boolean get () {
        for (int i = 0; i < this.triggers.length; i++) {
            if (this.triggers[i].get()) {
                return true;
            }
        }
        
        return false;
    }
}