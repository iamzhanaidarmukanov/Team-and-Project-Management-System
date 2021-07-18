package main.exceptions;

public class ExNoSuchEmployee extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExNoSuchEmployee() {
		super("Employee name does not exist.");
	}
	
	public ExNoSuchEmployee(String msg) {
		super(msg);
	}
}
