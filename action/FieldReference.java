package com._604robotics.robotnik.action;

import com._604robotics.robotnik.data.DataSink;
import com._604robotics.robotnik.network.Slice;

public class FieldReference implements DataSink {
    private final Field field;
    private final Slice slice;

    public FieldReference (Field field, Slice slice) {
        this.field = field;
        this.slice = slice;
    }
    
    protected void reset () {
        field.reset();
    }
    
    public void write (double value) {
        field.write(value);
        slice.putNumber(value);
    }
}
