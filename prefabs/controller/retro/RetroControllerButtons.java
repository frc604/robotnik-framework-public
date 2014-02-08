package com._604robotics.robotnik.prefabs.controller.retro;

import edu.wpi.first.wpilibj.tables.ITable;

public class RetroControllerButtons {
    public final RetroControllerButton Top;
    public final RetroControllerButton Trigger;
    
    public RetroControllerButtons (ITable table) {
        this.Top     = new RetroControllerButton(table, "TOP");
        this.Trigger = new RetroControllerButton(table, "TRIGGER");
    }
}