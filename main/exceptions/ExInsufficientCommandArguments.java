package main.exceptions;

public class ExInsufficientCommandArguments extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExInsufficientCommandArguments() {
		super("Insufficient command arguments.");
	}
	
	public ExInsufficientCommandArguments(String msg) {
		super(msg);
	}
}
