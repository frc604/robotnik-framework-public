package com._604robotics.robotnik.prefabs.controller.custom;

import com._604robotics.robotnik.trigger.TriggerAccess;
import edu.wpi.first.wpilibj.tables.ITable;

public class CustomControllerButton implements TriggerAccess {
    private final ITable table;
    private final String id;
    
    protected CustomControllerButton (ITable table, String id) {
        this.table = table;
        this.id = id;
    }
    
    public boolean get () {
        return table.getBoolean(id, false);
    }
}
