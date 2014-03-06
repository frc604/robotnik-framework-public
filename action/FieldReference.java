package com._604robotics.robotnik.action;

import com._604robotics.robotnik.data.DataRecipient;
import com._604robotics.robotnik.network.Slice;

public class FieldReference implements DataRecipient {
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
