package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.network.Slice;
import com._604robotics.robotnik.prefabs.trigger.TriggerNot;

public class TriggerReference implements TriggerSource {
    private final Trigger trigger;
    private final Slice value;
    
    private TriggerSource inverse = null;
    
    public TriggerReference (Trigger trigger, Slice value) {
        this.trigger = trigger;
        this.value = value;
    }
    
    public TriggerSource not () {
        if (this.inverse == null) {
            this.inverse = new TriggerNot(this);
        }
        
        return this.inverse;
    }
    
    public boolean get () {
        return this.value.getBoolean(false);
    }
    
    protected void update () {
        this.value.putBoolean(this.trigger.run());
    }
}
