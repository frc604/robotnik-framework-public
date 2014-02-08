package com._604robotics.robotnik.prefabs.controller.custom;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import java.util.Hashtable;

public class CustomController {
    private final Hashtable axisCache = new Hashtable();
    private final Hashtable buttonCache = new Hashtable();

    private final ITable axisTable;
    private final ITable buttonTable;
    
    public CustomController (String id) {
        final ITable table = NetworkTable.getTable("robotnik.controller").getSubTable(id);
        this.axisTable   = table.getSubTable("axis");
        this.buttonTable = table.getSubTable("button");
    }
    
    public CustomControllerAxis getAxis (String id) {
        if (axisCache.containsKey(id)) {
            return (CustomControllerAxis) axisCache.get(id);
        } else {
            final CustomControllerAxis axis = new CustomControllerAxis(axisTable, id);
            axisCache.put(id, axis);
            return axis;
        }
    }
    
    public CustomControllerButton getButton (String id) {
        if (buttonCache.containsKey(id)) {
            return (CustomControllerButton) buttonCache.get(id);
        } else {
            final CustomControllerButton button = new CustomControllerButton(buttonTable, id);
            buttonCache.put(id, button);
            return button;
        }
    }
}