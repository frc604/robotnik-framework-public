package com._604robotics.robotnik.prefabs.controller.retro;

import com._604robotics.robotnik.data.DataAccess;
import edu.wpi.first.wpilibj.tables.ITable;

public class RetroControllerAxis implements DataAccess {
    private final ITable table;
    private final String axis;
    
    private double deadband = 0D;
    private double factor = 1D;
    
    protected RetroControllerAxis (ITable table, String axis) {
        this.table = table;
        this.axis = axis;
    }
    
    public double get () {
        final double value = table.getNumber(axis, 0D) * this.factor;
        
        return Math.abs(value) < this.deadband ? 0D : value;
    }
    
    public void setDeadband (double deadband) {
        this.deadband = deadband;
    }
    
    public void setFactor (double factor) {
        this.factor = factor;
    }
}
