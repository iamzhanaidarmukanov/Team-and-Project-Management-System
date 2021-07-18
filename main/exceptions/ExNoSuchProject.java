package main.exceptions;

public class ExNoSuchProject extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExNoSuchProject() {
		super("Project does not exist.");
	}
	
	public ExNoSuchProject(String msg) {
		super(msg);
	}
}
