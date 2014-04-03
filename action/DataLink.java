package com._604robotics.robotnik.action;

import com._604robotics.robotnik.data.DataSource;

public class DataLink implements DataSource {
    private DataSource source = null;
    
    protected DataLink () {}
    
    protected void link (DataSource source) {
        this.source = source;
    }

    public double get () {
        return source == null ? 0D : source.get();
    }
}
