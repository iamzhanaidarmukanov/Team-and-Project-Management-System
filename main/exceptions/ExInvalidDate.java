package main.exceptions;

public class ExInvalidDate extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExInvalidDate() {
		super("Invalid date.");
	}
	
	public ExInvalidDate(String msg) {
		super(msg);
	}
}
