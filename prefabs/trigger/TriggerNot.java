package com._604robotics.robotnik.prefabs.trigger;

import com._604robotics.robotnik.trigger.TriggerSource;

public class TriggerNot implements TriggerSource {
    private final TriggerSource trigger;
    
    public TriggerNot (TriggerSource trigger) {
        this.trigger = trigger;
    }
    
    public boolean get () {
        return !this.trigger.get();
    }
}