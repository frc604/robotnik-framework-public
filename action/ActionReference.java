package com._604robotics.robotnik.action;

import com._604robotics.robotnik.data.DataSink;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.network.IndexedTable;
import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.module.ModuleReference;
import com._604robotics.robotnik.prefabs.trigger.TriggerManual;
import com._604robotics.robotnik.trigger.TriggerSource;
import com._604robotics.robotnik.trigger.TriggerSink;
import java.util.Hashtable;

public class ActionReference implements TriggerSink {
    private final String name;
    
    private final Action action;
    private final Hashtable fields;
    
    private boolean triggered = false;
    private int precedence;
    
    private final TriggerManual activeTrigger = new TriggerManual(false);
    
    protected ActionReference (ModuleReference module, String name, Action action, final IndexedTable dataTable) {
        this.name = name;
        this.action = action;
        
        this.fields = Repackager.repackage(action.iterateFields(), new Repackager() {
            public Object wrap (Object key, Object value) {
                return new FieldReference((Field) value, dataTable.getSlice((String) key));
            }
        });
        
        final Iterator di = action.iterateDataLinks();
        while (di.next()) ((DataLink) di.value).link(module.getData((String) di.key));
        
        final Iterator ti = action.iterateTriggerLinks();
        while (ti.next()) ((TriggerLink) ti.value).link(module.getTrigger((String) ti.key));
    }
    
    public TriggerSource active () {
        return (TriggerSource) this.activeTrigger;
    }
    
    public DataSink getField (String name) {
        final FieldReference field = (FieldReference) this.fields.get(name);
        if (field == null) InternalLogger.missing("FieldReference", name);
        return field;
    }
    
    public void write (boolean value, int precedence) {
        this.triggered = value;
        if (this.precedence > precedence)
            this.precedence = precedence;
    }
    
    protected String getName () {
        return name;
    }
    
    protected boolean isTriggered () {
        return triggered;
    }
    
    protected int getPrecedence () {
        return precedence;
    }
    
    protected void reset () {
        final Iterator i = new Iterator(this.fields);
        while (i.next()) ((FieldReference) i.value).reset();
    }
    
    protected void begin () {
        this.action.begin();
        this.activeTrigger.set(true);
    }
    
    protected void run () {
        this.action.run();
    }
    
    protected void end () {
        this.action.end();
        this.activeTrigger.set(false);
    }
}