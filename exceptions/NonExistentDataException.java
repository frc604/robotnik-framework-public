package com._604robotics.robotnik.exceptions;

public class NonExistentDataException extends Exception {

	private static final long serialVersionUID = 121078116205737454L;

	public NonExistentDataException() {}

	public NonExistentDataException(String arg0) {
		super(arg0);
	}

	public NonExistentDataException(Throwable arg0) {
		super(arg0);
	}

	public NonExistentDataException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NonExistentDataException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
