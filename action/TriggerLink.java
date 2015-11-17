package com._604robotics.robotnik.action;

import com._604robotics.robotnik.trigger.TriggerSource;

public class TriggerLink implements TriggerSource {
    private TriggerSource source = null;
    
    protected TriggerLink () {}
    
    protected void link (TriggerSource source) {
        this.source = source;
    }

    public boolean get () {
        return source == null ? false : source.get();
    }
}
