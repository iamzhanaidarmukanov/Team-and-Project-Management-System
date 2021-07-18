package main.exceptions;

public class ExDuplicateTeamName extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExDuplicateTeamName() {
		super("Team name already exists.");
	}
	
	public ExDuplicateTeamName(String msg) {
		super(msg);
	}
}
