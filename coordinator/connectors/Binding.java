package com._604robotics.robotnik.coordinator.connectors;

import com._604robotics.robotnik.trigger.TriggerSource;
import com._604robotics.robotnik.trigger.TriggerSink;

public class Binding {
    public static interface Precedence {
        public static int MINIMUM = Integer.MIN_VALUE;
        public static int MAXIMUM = Integer.MAX_VALUE;
    }
    
    private final TriggerSink sink;
    private final TriggerSource trigger;
    private final int precedence;
    
    public Binding (TriggerSink sink, TriggerSource trigger, int precedence) {
        this.sink = sink;
        this.trigger = trigger;
        this.precedence = precedence;
    }

    protected void conduct () {
        sink.write(trigger.get(), precedence);
    }
}