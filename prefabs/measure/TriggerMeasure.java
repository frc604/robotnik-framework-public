package com._604robotics.robotnik.prefabs.measure;

import com._604robotics.robotnik.procedure.Measure;
import com._604robotics.robotnik.trigger.TriggerSource;

public class TriggerMeasure extends Measure {
    private final TriggerSource trigger;

    public TriggerMeasure(TriggerSource trigger) {
        this.trigger = trigger;
    }

    public boolean complete () {
        return trigger.get();
    }
}
