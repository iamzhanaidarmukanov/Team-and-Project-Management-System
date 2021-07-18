package main.exceptions;

public class ExEmployeeAddedToTeam extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExEmployeeAddedToTeam() {
		super("Employee has joined a team already.");
	}
	
	public ExEmployeeAddedToTeam(String msg) {
		super(msg);
	}
}
