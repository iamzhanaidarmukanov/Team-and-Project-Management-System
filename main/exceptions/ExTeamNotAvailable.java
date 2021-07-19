package main.exceptions;

public class ExTeamNotAvailable extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExTeamNotAvailable() {
		super("Team is not available in this period");
	}
	
	public ExTeamNotAvailable(String msg) {
		super(msg);
	}
}
