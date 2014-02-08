package com._604robotics.robotnik.prefabs.controller.retro;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

public class RetroController {
    public static interface Stick {
        public static final String LEFT  = "LEFT";
        public static final String RIGHT = "RIGHT";
    }
    
    public final RetroControllerButtons buttons;
    
    public final RetroControllerAxis axisX;
    public final RetroControllerAxis axisY;
    
    public RetroController (String stick) {
        final ITable table = NetworkTable.getTable("robotnik.controller").getSubTable(stick);
        
        this.buttons = new RetroControllerButtons(table.getSubTable("button"));
        
        final ITable axisTable = table.getSubTable("axis");
        this.axisX = new RetroControllerAxis(axisTable, "X");
        this.axisY = new RetroControllerAxis(axisTable, "Y");
    }
}