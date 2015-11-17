package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.coordinator.connectors.ConnectorProxy;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.coordinator.connectors.Group;
import com._604robotics.robotnik.data.DataSource;
import com._604robotics.robotnik.data.DataSink;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.trigger.TriggerSource;
import com._604robotics.robotnik.trigger.TriggerSink;
import java.util.Enumeration;
import java.util.Vector;

public class Coordinator {
    private static final TriggerSource ALWAYS_ON = new TriggerSource() {
        public boolean get () {
            return true;
        }
    };
    
    private static class FixedValue implements DataSource {
        private final double value;

        public FixedValue (double value) {
            this.value = value;
        }

        public double get () {
            return value;
        }
    }
    
    private static class TriggerWrapper implements DataSource {
        private final TriggerSource trigger;

        public TriggerWrapper (TriggerSource trigger) {
            this.trigger = trigger;
        }

        public double get () {
            return this.trigger.get() ? 1D : 0D;
        }
    }
    
    private final Vector triggerBindings = new Vector();
    private final Vector dataWires = new Vector();
    private final Vector subGroups = new Vector();
    
    protected void apply (ModuleManager modules) {}
    
    public void attach (ModuleManager modules) {
        this.triggerBindings.removeAllElements();
        this.dataWires.removeAllElements();
        this.subGroups.removeAllElements();
        
        this.apply(modules);
        
        final Enumeration i = this.subGroups.elements();
        while (i.hasMoreElements()) ((Group) i.nextElement()).attach(modules);
    }
    
    public void update () {
        final Enumeration wires = this.dataWires.elements();
        while (wires.hasMoreElements()) ConnectorProxy.pipe((DataWire) wires.nextElement());
        
        final Enumeration bindings = this.triggerBindings.elements();
        while (bindings.hasMoreElements()) ConnectorProxy.pipe((Binding) bindings.nextElement());
        
        final Enumeration groups = this.subGroups.elements();
        while (groups.hasMoreElements()) ((Group) groups.nextElement()).update();
    }
    
    //// Bindings ////////////////////////////////////////////////////////////
    
    private void bind (Binding binding) {
        this.triggerBindings.addElement(binding);
    }
    
    protected void bind (TriggerSink recipient, TriggerSource trigger, int precedence) {
        bind(new Binding(recipient, trigger, precedence));
    }
    
    protected void bind (TriggerSink recipient) {
        bind(recipient, ALWAYS_ON, Binding.Precedence.MINIMUM);
    }
    
    protected void bind (TriggerSink recipient, int precedence) {
        bind(recipient, ALWAYS_ON, precedence);
    }
    
    protected void bind (TriggerSink recipient, TriggerSource trigger) {
        bind(recipient, trigger, Binding.Precedence.MINIMUM);
    }
    
    //// DataWires /////////////////////////////////////////////////////////////
    
    private void wire (DataWire dataWire) {
        this.dataWires.addElement(dataWire);
    }
    
    protected void wire (DataSink recipient, DataSource data, TriggerSource activator) {
        wire(new DataWire(recipient, data, activator));
    }
    
    protected void wire (DataSink recipient, DataSource data) {
        wire(recipient, data, ALWAYS_ON);
    }
    
    protected void wire (DataSink recipient, TriggerSource trigger) {
        wire(recipient, trigger, ALWAYS_ON);
    }
    
    protected void wire (DataSink recipient, TriggerSource trigger, TriggerSource activator) {
        wire(recipient, new TriggerWrapper(trigger), activator);
    }
    
    protected void wire (DataSink recipient, double value) {
        wire(recipient, value, ALWAYS_ON);
    }
    
    protected void wire (DataSink recipient, double value, TriggerSource activator) {
        wire(recipient, new FixedValue(value), activator);
    }
    
    protected void wire (DataSink recipient, boolean value) {
        wire(recipient, value, ALWAYS_ON);
    }
    
    protected void wire (DataSink recipient, boolean value, TriggerSource activator) {
        wire(recipient, new FixedValue(value ? 1D : 0D), activator);
    }
    
    //// Groups ////////////////////////////////////////////////////////////////
    
    protected void group (TriggerSource trigger, Coordinator coordinator) {
        this.subGroups.addElement(new Group(trigger, coordinator));
    }
    
    protected void group (Coordinator coordinator) {
        group(ALWAYS_ON, coordinator);
    }
}