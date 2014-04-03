package com._604robotics.robotnik.coordinator.connectors;

import com._604robotics.robotnik.logging.Logger;

public class ConnectorProxy {
    private static boolean active = true;
    
    public static void disable () { active = false; }
    
    public static void pipe (Binding binding) {
        if (active) {
            try {
                binding.conduct();
            } catch (Exception ex) {
                Logger.error("Caught error while piping a Binding", ex);
            }
        } else {
            binding.conduct();
        }
    }
    
    public static void pipe (DataWire wire) {
        if (active) {
            try {
                wire.conduct();
            } catch (Exception ex) {
                Logger.error("Caught error while piping a DataWire", ex);
            }
        } else {
            wire.conduct();
        }
    }
}
