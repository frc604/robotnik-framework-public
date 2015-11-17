package com._604robotics.robotnik.prefabs.trigger;

import com._604robotics.robotnik.trigger.TriggerSource;

public class TriggerToggle {
    private final TriggerSource trigger;
    
    private boolean last = false;
    private boolean state;
    
    private class TriggerState implements TriggerSource {
        private final boolean which;
        
        public TriggerState (boolean which) {
            this.which = which;
        }
        
        public boolean get () {
            update();
            return state == which;
        }
    }
    
    public final TriggerSource off = new TriggerState(false);
    public final TriggerSource on  = new TriggerState(true);
    
    public TriggerToggle (TriggerSource trigger, boolean defaultValue) {
        this.trigger = trigger;
        this.state = defaultValue;
    }
    
    private void update () {
        final boolean now = trigger.get();
        
        if (!last && now) {
            state = !state;
        }
        
        last = now;
    }
}