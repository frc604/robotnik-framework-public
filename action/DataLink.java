package com._604robotics.robotnik.action;

import com._604robotics.robotnik.data.DataAccess;

public class DataLink implements DataAccess {
    private DataAccess source = null;
    
    protected DataLink () {}
    
    protected void link (DataAccess source) {
        this.source = source;
    }

    public double get () {
        return source == null ? 0D : source.get();
    }
}
