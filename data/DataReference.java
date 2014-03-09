package com._604robotics.robotnik.data;

import com._604robotics.robotnik.network.Slice;

public class DataReference implements DataAccess {
    private final Data data;
    private final Slice value;
    
    protected DataReference (Data data, Slice value) {
        this.data = data;
        this.value = value;
    }
    
    public double get () {
        return this.value.getNumber(0D);
    }
    
    protected void update () {
        this.value.putNumber(this.data.run());
    }
}
