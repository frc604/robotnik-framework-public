package com._604robotics.robotnik.prefabs.trigger;

import com._604robotics.robotnik.trigger.TriggerSource;

public class TriggerManual implements TriggerSource {
    private boolean triggered;
    
    public TriggerManual (boolean defaultValue) {
        this.triggered = defaultValue;
    }
    
    public boolean get () {
        return this.triggered;
    }
    
    public void set (boolean triggered) {
        this.triggered = triggered;
    }
}