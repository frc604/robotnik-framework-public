package com._604robotics.robotnik.exceptions;

public class NonExistentTriggerException extends Exception {

	private static final long serialVersionUID = -6789130806923912458L;

	public NonExistentTriggerException() {}

	public NonExistentTriggerException(String arg0) {}

	public NonExistentTriggerException(Throwable arg0) {}

	public NonExistentTriggerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NonExistentTriggerException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
