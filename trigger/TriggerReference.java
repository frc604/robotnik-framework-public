package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.network.Slice;
import com._604robotics.robotnik.prefabs.trigger.TriggerNot;

public class TriggerReference implements TriggerSource {
    private final Trigger trigger;
    private final Slice slice;
    
    private boolean lastValue = false;
    private TriggerSource inverse = null;
    
    protected TriggerReference (Trigger trigger, Slice slice) {
        this.trigger = trigger;
        this.slice = slice;
    }
    
    public TriggerSource not () {
        if (inverse == null) {
            inverse = new TriggerNot(this);
        }
        
        return inverse;
    }
    
    public boolean get () {
        return lastValue;
    }
    
    protected void update () {
        lastValue = trigger.run();
        slice.putBoolean(lastValue);
    }
}
