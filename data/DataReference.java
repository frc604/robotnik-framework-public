package com._604robotics.robotnik.data;

import com._604robotics.robotnik.network.Slice;

public class DataReference implements DataSource {
    private final Data data;
    private final Slice slice;
    
    private double lastValue = 0D;
    
    protected DataReference (Data data, Slice slice) {
        this.data = data;
        this.slice = slice;
    }
    
    public double get () {
        return lastValue;
    }
    
    protected void update () {
        lastValue = data.run();
        slice.putNumber(lastValue);
    }
}
