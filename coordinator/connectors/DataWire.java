package com._604robotics.robotnik.coordinator.connectors;

import com._604robotics.robotnik.data.DataSource;
import com._604robotics.robotnik.data.DataSink;
import com._604robotics.robotnik.trigger.TriggerSource;

public class DataWire {
    private final DataSink sink;
    private final DataSource source;
    private final TriggerSource activator;
    
    public DataWire (DataSink sink, DataSource source, TriggerSource activator) {
        this.sink = sink;
        this.source = source;
        this.activator = activator;
    }
    
    protected void conduct () {
        if (this.activator == null || this.activator.get())
            this.sink.write(this.source.get());
    }
}