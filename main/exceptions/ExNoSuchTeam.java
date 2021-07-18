package main.exceptions;

public class ExNoSuchTeam extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExNoSuchTeam() {
		super("Team does not exist.");
	}
	
	public ExNoSuchTeam(String msg) {
		super(msg);
	}
}
