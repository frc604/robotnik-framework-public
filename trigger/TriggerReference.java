package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.network.Slice;

public class TriggerReference implements TriggerSource {
    private final Trigger trigger;
    private final Slice slice;
    
    private boolean lastValue = false;
    
    protected TriggerReference (Trigger trigger, Slice slice) {
        this.trigger = trigger;
        this.slice = slice;
    }
    
    public boolean get () {
        return lastValue;
    }
    
    protected void update () {
        lastValue = trigger.run();
        slice.putBoolean(lastValue);
    }
}
