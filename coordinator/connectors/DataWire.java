package com._604robotics.robotnik.coordinator.connectors;

import com._604robotics.robotnik.data.DataAccess;
import com._604robotics.robotnik.data.DataRecipient;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class DataWire {
    private final DataRecipient recipient;
    private final DataAccess source;
    private final TriggerAccess activator;
    
    public DataWire (DataRecipient recipient, DataAccess source, TriggerAccess activator) {
        this.recipient = recipient;
        this.source = source;
        this.activator = activator;
    }
    
    public boolean isActive () {
        return this.activator == null || this.activator.get();
    }
    
    public void conduct () {
        if (this.activator == null || this.activator.get())
            this.recipient.write(this.source.get());
    }
}