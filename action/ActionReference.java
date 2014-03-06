package com._604robotics.robotnik.action;

import com._604robotics.robotnik.data.DataRecipient;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.network.IndexedTable;
import com._604robotics.robotnik.network.Slice;
import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.module.ModuleReference;
import com._604robotics.robotnik.prefabs.trigger.TriggerManual;
import com._604robotics.robotnik.trigger.TriggerAccess;
import com._604robotics.robotnik.trigger.TriggerRecipient;
import java.util.Hashtable;

public class ActionReference implements TriggerRecipient {
    private final Action action;
    private final Slice trigger;
    private final Hashtable fields;
    
    private final TriggerManual activeTrigger = new TriggerManual(false);
    
    public ActionReference (ModuleReference module, Action action, Slice trigger, final IndexedTable dataTable) {
        this.action = action;
        this.trigger = trigger;
        
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
    
    public void reset () {
        this.trigger.putNumber(0D);
        
        final Iterator i = new Iterator(this.fields);
        while (i.next()) ((FieldReference) i.value).reset();
    }
    
    public void sendTrigger (double precedence) {
        final double current = this.trigger.getNumber(0D);
        
        if (precedence > current) {
            this.trigger.putNumber(precedence);
        }
    }
    
    public TriggerAccess active () {
        return (TriggerAccess) this.activeTrigger;
    }
    
    public DataRecipient getField (String name) {
        final FieldReference field = (FieldReference) this.fields.get(name);
        if (field == null) InternalLogger.missing("FieldReference", name);
        return field;
    }
    
    public void begin () {
        this.action.begin();
        this.activeTrigger.set(true);
    }
    
    public void run () {
        this.action.run();
    }
    
    public void end () {
        this.action.end();
        this.activeTrigger.set(false);
    }
}