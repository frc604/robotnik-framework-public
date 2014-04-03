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
    private final ActionReference defaultAction;
    
    private ActionReference triggeredAction = null;
    private ActionReference lastAction = null;
    
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
               return new ActionReference(module, (String) key, (Action) value, dataTable.getSubTable((String) key));
           }
        });
        this.defaultAction = controller.getDefaultAction() != null
                           ? (ActionReference) actionTable.get(controller.getDefaultAction())
                           : null;
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
                r.consider(i.value, ((ActionReference) i.value).getPrecedence());
        }
        
        triggeredAction = r.score > 0 ? (ActionReference) r.victor : null;
        triggeredActionSlice.putString(triggeredAction.getName());
    }
    
    public void execute () {
        final ActionReference selectedAction = controller.pickAction(defaultAction, lastAction, triggeredAction);
        
        if (lastAction != null && lastAction != selectedAction)
            ActionProxy.end(moduleName, lastAction);

        if (selectedAction != null) {
            if (lastAction == null || lastAction != selectedAction)
                ActionProxy.begin(moduleName, selectedAction);
            
            ActionProxy.run(moduleName, selectedAction);
        }
        
        lastAction = selectedAction;
        lastActionSlice.putString(selectedAction.getName());
    }
    
    public void end () {
        if (lastAction != null)
            ActionProxy.end(moduleName, lastAction);
        
        triggeredAction = null;
        lastAction = null;
        
        triggeredActionSlice.putString("");
        lastActionSlice.putString("");
    }
}