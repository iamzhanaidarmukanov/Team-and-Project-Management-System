package main.exceptions;

public class ExDuplicateProjectCode extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExDuplicateProjectCode() {
		super("Project code already exists.");
	}
	
	public ExDuplicateProjectCode(String msg) {
		super(msg);
	}
}
