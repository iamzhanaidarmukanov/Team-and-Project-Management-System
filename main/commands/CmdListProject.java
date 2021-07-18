package main.commands;

import main.Company;
import main.RecordedCommand;

public class CmdListProject extends RecordedCommand {

    @Override
	public void execute(String[] cmdParts) {
        Company company = Company.getInstance();
		company.listProjects();
    }
    
    @Override
	public void undoMe() {

	}

	@Override
	public void redoMe() {

	}

}
