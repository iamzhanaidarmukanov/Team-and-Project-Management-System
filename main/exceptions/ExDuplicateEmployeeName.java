package main.exceptions;

public class ExDuplicateEmployeeName extends Exception {
    private static final long serialVersionUID = 1L;
	
	public ExDuplicateEmployeeName() {
		super("Employee name already exists.");
	}
	
	public ExDuplicateEmployeeName(String msg) {
		super(msg);
	}
}
