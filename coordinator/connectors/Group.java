package com._604robotics.robotnik.coordinator.connectors;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.trigger.TriggerSource;

public class Group {
    private final TriggerSource trigger;
    private final Coordinator coordinator;

    public Group (TriggerSource trigger, Coordinator coordinator) {
        this.trigger = trigger;
        this.coordinator = coordinator;
    }
    
    public void attach (ModuleManager modules) {
        coordinator.attach(modules);
    }
    
    public void update () {
        if (trigger.get())
            coordinator.update();
    }
}
