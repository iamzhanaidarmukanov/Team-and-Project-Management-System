package main.exceptions;


public class ExAreadyAssignedTeamProject extends Exception {

    private static final long serialVersionUID = 1L;

    public ExAreadyAssignedTeamProject() {
        super("Project has already been assigned to a team.");
    }

    public ExAreadyAssignedTeamProject(String message) {
        super(message);
    }
}