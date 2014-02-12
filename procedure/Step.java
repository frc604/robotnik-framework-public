package com._604robotics.robotnik.procedure;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.module.ModuleManager;

public class Step {
    private final Measure measure;
    private final Coordinator coordinator;

    public Step (Measure measure, Coordinator coordinator) {
        this.measure = measure;
        this.coordinator = coordinator;
    }
    
    public void initialize () {
        measure.initialize();
    }
    
    public boolean complete () {
        return measure.complete();
    }
    
    public void attach (ModuleManager modules) {
        coordinator.attach(modules);
    }
    
    public void update () {
        coordinator.update();
    }
}
