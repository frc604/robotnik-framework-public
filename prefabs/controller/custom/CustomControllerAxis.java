package com._604robotics.robotnik.prefabs.controller.custom;

import com._604robotics.robotnik.data.DataAccess;
import edu.wpi.first.wpilibj.tables.ITable;

public class CustomControllerAxis implements DataAccess {
    private final ITable table;
    private final String id;
    
    private double deadband = 0D;
    private double factor = 1D;
    
    protected CustomControllerAxis (ITable table, String id) {
        this.table = table;
        this.id = id;
    }
    
    public double get () {
        final double value = table.getNumber(id, 0D) * this.factor;
        
        return Math.abs(value) < this.deadband ? 0D : value;
    }
    
    public void setDeadband (double deadband) {
        this.deadband = deadband;
    }
    
    public void setFactor (double factor) {
        this.factor = factor;
    }
}
