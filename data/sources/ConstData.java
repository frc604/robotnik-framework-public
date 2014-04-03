package com._604robotics.robotnik.data.sources;

import com._604robotics.robotnik.data.DataSource;

public class ConstData implements DataSource {
    private final double value;

    public ConstData (double value) {
        this.value = value;
    }
    
    public double get() {
        return value;
    }
}
