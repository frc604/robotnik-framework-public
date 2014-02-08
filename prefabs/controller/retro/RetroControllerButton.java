package com._604robotics.robotnik.prefabs.controller.retro;

import com._604robotics.robotnik.trigger.TriggerAccess;
import edu.wpi.first.wpilibj.tables.ITable;

public class RetroControllerButton implements TriggerAccess {
    private final ITable table;
    private final String axis;
    
    protected RetroControllerButton (ITable table, String axis) {
        this.table = table;
        this.axis = axis;
    }
    
    public boolean get () {
        return table.getBoolean(axis, false);
    }
}
