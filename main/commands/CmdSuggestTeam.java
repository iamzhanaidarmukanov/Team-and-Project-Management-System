package main.commands;

import main.Company;
import main.Project;
import main.RecordedCommand;
import main.exceptions.ExNoSuchProject;


public class CmdSuggestTeam extends RecordedCommand {

    @Override
    public void execute(String[] cmdParts) {

        try {
            Company company = Company.getInstance();
            if(company.projectAdded(cmdParts[1]) == false) {
                throw new ExNoSuchProject();
            }
            Project p = company.getProject(cmdParts[1]);
            Integer manpower = p.getManpower();
            company.suggestTeam(p, manpower);
        } catch (ExNoSuchProject e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {}

    @Override
    public void redoMe() {}
}
