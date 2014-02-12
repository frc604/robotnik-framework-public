package com._604robotics.robotnik.procedure;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.module.ModuleManager;
import java.util.Enumeration;
import java.util.Vector;

public class Procedure {
    private final Coordinator global;
    
    private final Vector steps = new Vector();
    private int currentStep = 0;
    private boolean initialized = false;
    
    public Procedure () {
        this(new Coordinator());
    }
    
    public Procedure (Coordinator global) {
        this.global = global;
    }
    
    protected void apply (ModuleManager modules) {}
    
    public void attach (ModuleManager modules) {
        global.attach(modules);
        
        steps.removeAllElements();
        this.apply(modules);
        
        final Enumeration i = steps.elements();
        while (i.hasMoreElements()) ((Step) i.nextElement()).attach(modules);
    }
    
    protected void add (Step step) {
        steps.addElement(step);
    }
    
    public void update () {
        if (currentStep >= steps.size()) return;
        
        final Step step = (Step) steps.elementAt(currentStep);
        
        if (step.complete()) {
            currentStep++;
            initialized = false;
        } else {
            if (!initialized) {
                initialized = true;
                step.initialize();
            }
            step.update();
        }
    }
    
    public void reset () {
        currentStep = 0;
        initialized = false;
    }
}
