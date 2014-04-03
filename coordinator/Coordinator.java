package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.coordinator.connectors.ConnectorProxy;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.coordinator.connectors.Group;
import com._604robotics.robotnik.data.DataSource;
import com._604robotics.robotnik.data.DataSink;
import com._604robotics.robotnik.data.sources.ConstData;
import com._604robotics.robotnik.data.sources.DataTriggerAdaptor;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.trigger.TriggerAlways;
import com._604robotics.robotnik.trigger.TriggerSource;
import com._604robotics.robotnik.trigger.TriggerSink;
import java.util.Enumeration;
import java.util.Vector;

public class Coordinator {
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
        bind(recipient, TriggerAlways.getInstance(), Binding.Precedence.MINIMUM);
    }
    
    protected void bind (TriggerSink recipient, int precedence) {
        bind(recipient, TriggerAlways.getInstance(), precedence);
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
        wire(recipient, data, null);
    }
    
    protected void wire (DataSink recipient, TriggerSource trigger) {
        wire(recipient, trigger, null);
    }
    
    protected void wire (DataSink recipient, TriggerSource trigger, TriggerSource activator) {
        wire(recipient, new DataTriggerAdaptor(trigger), activator);
    }
    
    protected void wire (DataSink recipient, double value) {
        wire(recipient, value, null);
    }
    
    protected void wire (DataSink recipient, double value, TriggerSource activator) {
        wire(recipient, new ConstData(value), activator);
    }
    
    protected void wire (DataSink recipient, boolean value) {
        wire(recipient, value, null);
    }
    
    protected void wire (DataSink recipient, boolean value, TriggerSource activator) {
        wire(recipient, new ConstData(value ? 1D : 0D), activator);
    }
    
    //// Groups ////////////////////////////////////////////////////////////////
    
    private void group (Group group) {
        this.subGroups.addElement(group);
    }
    
    protected void group (TriggerSource trigger, Coordinator coordinator) {
        group(new Group(trigger, coordinator));
    }
}