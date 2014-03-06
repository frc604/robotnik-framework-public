package com._604robotics.robotnik.action;

public class Field {
    private final double defaultValue;
    private double currentValue;
    
    public Field (double defaultValue) {
        this.defaultValue = defaultValue;
        currentValue = defaultValue;
    }
    
    public double value () {
        return currentValue;
    }
    
    public boolean on () {
        return currentValue > 0;
    }
    
    protected void reset () {
        currentValue = defaultValue;
    }
    
    protected void write (double value) {
        currentValue = value;
    }
}
