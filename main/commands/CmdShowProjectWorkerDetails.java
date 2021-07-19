package main.commands;

import main.Company;
import main.RecordedCommand;
import main.exceptions.ExInsufficientCommandArguments;
import main.exceptions.ExNoSuchProject;

public class CmdShowProjectWorkerDetails extends RecordedCommand {

    public void execute(String[] cmdParts) {
        try {
            Company company = Company.getInstance();
            if (cmdParts.length < 2) {
                throw new ExInsufficientCommandArguments();
            }
            if (company.projectAdded(cmdParts[1]) == false) {
                throw new ExNoSuchProject();
            }
            company.listProjectWorkerDetails(cmdParts[1]);
        } catch (ExInsufficientCommandArguments e) {
            System.out.println(e.getMessage());
        } catch (ExNoSuchProject e) {
            System.out.println(e.getMessage());
        } 
    }

    @Override
    public void undoMe() {}

    @Override
    public void redoMe() {}

}
