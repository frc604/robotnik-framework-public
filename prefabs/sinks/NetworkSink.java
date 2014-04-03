package com._604robotics.robotnik.prefabs.sinks;

import com._604robotics.robotnik.data.DataSink;
import com._604robotics.robotnik.network.Slice;
import com.sun.squawk.util.StringTokenizer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

public class NetworkSink {
    private NetworkSink () {}
    
    public static class Number implements DataSink {
        private final Slice slice;
        
        public Number (String namespace, String field) {
            final StringTokenizer tokens = new StringTokenizer(namespace, ".");
            
            ITable table = NetworkTable.getTable(tokens.nextToken());
            while (tokens.hasMoreTokens())
                table = table.getSubTable(tokens.nextToken());
            
            this.slice = new Slice(table, field);
        }

        public void write (double value) {
            slice.putNumber(value);
        }
    }
    
    public static class Boolean implements DataSink {
        private final Slice slice;
        
        public Boolean (String namespace, String field) {
            final StringTokenizer tokens = new StringTokenizer(namespace, ".");
            
            ITable table = NetworkTable.getTable(tokens.nextToken());
            while (tokens.hasMoreTokens())
                table = table.getSubTable(tokens.nextToken());
            
            this.slice = new Slice(table, field);
        }

        public void write (double value) {
            slice.putBoolean(value > 0);
        }
    }
}