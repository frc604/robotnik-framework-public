package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.ConnectorProxy;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.coordinator.connectors.Group;
import com._604robotics.robotnik.data.DataAccess;
import com._604robotics.robotnik.data.DataRecipient;
import com._604robotics.robotnik.data.sources.ConstData;
import com._604robotics.robotnik.data.sources.DataTriggerAdaptor;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.trigger.TriggerAlways;
import com._604robotics.robotnik.trigger.TriggerAccess;
import com._604robotics.robotnik.trigger.TriggerRecipient;
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
    
    protected void bind (TriggerRecipient recipient, TriggerAccess trigger, boolean safety) {
        bind(new Binding(recipient, trigger, safety));
    }
    
    protected void bind (TriggerRecipient recipient) {
        bind(recipient, TriggerAlways.getInstance(), false);
    }
    
    protected void bind (TriggerRecipient recipient, boolean safety) {
        bind(recipient, TriggerAlways.getInstance(), safety);
    }
    
    protected void bind (TriggerRecipient recipient, TriggerAccess trigger) {
        bind(recipient, trigger, false);
    }
    
    //// DataWires /////////////////////////////////////////////////////////////
    
    private void wire (DataWire dataWire) {
        this.dataWires.addElement(dataWire);
    }
    
    protected void wire (DataRecipient recipient, DataAccess data, TriggerAccess activator) {
        wire(new DataWire(recipient, data, activator));
    }
    
    protected void wire (DataRecipient recipient, DataAccess data) {
        wire(recipient, data, null);
    }
    
    protected void wire (DataRecipient recipient, TriggerAccess trigger) {
        wire(recipient, trigger, null);
    }
    
    protected void wire (DataRecipient recipient, TriggerAccess trigger, TriggerAccess activator) {
        wire(recipient, new DataTriggerAdaptor(trigger), activator);
    }
    
    protected void wire (DataRecipient recipient, double value) {
        wire(recipient, value, null);
    }
    
    protected void wire (DataRecipient recipient, double value, TriggerAccess activator) {
        wire(recipient, new ConstData(value), activator);
    }
    
    protected void wire (DataRecipient recipient, boolean value) {
        wire(recipient, value, null);
    }
    
    protected void wire (DataRecipient recipient, boolean value, TriggerAccess activator) {
        wire(recipient, new ConstData(value ? 1D : 0D), activator);
    }
    
    //// Groups ////////////////////////////////////////////////////////////////
    
    private void group (Group group) {
        this.subGroups.addElement(group);
    }
    
    protected void group (TriggerAccess trigger, Coordinator coordinator) {
        group(new Group(trigger, coordinator));
    }
}