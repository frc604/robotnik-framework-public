package com._604robotics.robotnik.action;

import com._604robotics.robotnik.trigger.TriggerAccess;

public class TriggerLink implements TriggerAccess {
    private TriggerAccess source = null;
    
    protected TriggerLink () {}
    
    protected void link (TriggerAccess source) {
        this.source = source;
    }

    public boolean get () {
        return source == null ? false : source.get();
    }
}
