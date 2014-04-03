package com._604robotics.robotnik.trigger;

public interface TriggerSink {
    public abstract void write (boolean value, int precedence);
}
