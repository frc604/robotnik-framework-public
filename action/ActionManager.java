package com._604robotics.robotnik.action;

import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.meta.Scorekeeper;
import com._604robotics.robotnik.network.IndexedTable;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.module.ModuleReference;
import com._604robotics.robotnik.network.Slice;
import java.util.Hashtable;

public class ActionManager {
    private final String moduleName;
    
    private final ActionController controller;
    private final Hashtable actionTable;
    
    private String triggeredAction = "";
    private String lastAction = "";
    
    private final Slice triggeredActionSlice;
    private final Slice lastActionSlice;
    
    public ActionManager (final ModuleReference module, String moduleName, ActionController controller, IndexedTable statusTable, final IndexedTable dataTable) {
        this.moduleName = moduleName;
        
        this.controller = controller;
        
        this.triggeredActionSlice = statusTable.getSlice("triggeredAction");
        this.lastActionSlice = statusTable.getSlice("lastAction");
        
        this.triggeredActionSlice.putString("");
        this.lastActionSlice.putString("");
        
        this.actionTable = Repackager.repackage(controller.iterateActions(), new Repackager() {
           public Object wrap (Object key, Object value) {
               return new ActionReference(module, (Action) value, dataTable);
           }
        });
    }
    
    public ActionReference getAction (String name) {
        ActionReference ref = (ActionReference) this.actionTable.get(name);
        if (ref == null) InternalLogger.missing("ActionReference", name);
        return ref;
    }
    
    public void reset () {
        final Iterator i = new Iterator(this.actionTable);
        while (i.next()) ((ActionReference) i.value).reset();
    }
    
    public void update () {
        final Scorekeeper r = new Scorekeeper(0);
        final Iterator i = new Iterator(actionTable);
        
        while (i.next()) {
            if (((ActionReference) i.value).isTriggered())
                r.consider(i.key, ((ActionReference) i.value).getPrecedence());
        }
        
        triggeredAction = r.score > 0 ? (String) r.victor : "";
        triggeredActionSlice.putString(triggeredAction);
    }
    
    public void execute () {
        final String selectedAction = controller.pickAction(lastAction, triggeredAction);
        
        if (!lastAction.equals("") && !lastAction.equals(selectedAction))
            ActionProxy.end(moduleName, lastAction, this.getAction(lastAction));

        if (!selectedAction.equals("")) {
            final ActionReference action = this.getAction(selectedAction);
            
            if (lastAction.equals("") || !lastAction.equals(selectedAction))
                ActionProxy.begin(moduleName, selectedAction, action);
            
            ActionProxy.run(moduleName, selectedAction, action);
        }
        
        lastActionSlice.putString(selectedAction);
    }
    
    public void end () {
        if (!lastAction.equals(""))
            ActionProxy.end(moduleName, lastAction, this.getAction(lastAction));
        lastActionSlice.putString("");
    }
}