package com._604robotics.robotnik.data.sources;

import com._604robotics.robotnik.data.DataSource;
import com._604robotics.robotnik.trigger.TriggerSource;

public class DataTriggerAdaptor implements DataSource {
    private final TriggerSource trigger;
    
    public DataTriggerAdaptor (TriggerSource trigger) {
        this.trigger = trigger;
    }
    
    public double get () {
        return this.trigger.get() ? 1D : 0D;
    }
}
