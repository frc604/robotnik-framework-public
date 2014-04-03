package com._604robotics.robotnik.prefabs.trigger;

import com._604robotics.robotnik.trigger.TriggerSource;

public class TriggerAlways implements TriggerSource {
    private TriggerAlways () {}
    private static final TriggerSource instance = new TriggerAlways();
    
    public static TriggerSource getInstance () {
        return instance;
    }
    
    public boolean get () {
        return true;
    }
}