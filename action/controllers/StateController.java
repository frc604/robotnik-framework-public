package com._604robotics.robotnik.action.controllers;

import com._604robotics.robotnik.action.ActionController;
import com._604robotics.robotnik.action.ActionReference;

public class StateController extends ActionController {
    protected ActionReference pickAction (ActionReference defaultAction, ActionReference lastAction, ActionReference triggeredAction) {
        return triggeredAction == null
                   ? lastAction == null
                       ? defaultAction
                       : lastAction
                   : triggeredAction;
    }
}