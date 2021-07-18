package main.commands;

import main.Company;
import main.RecordedCommand;
import main.Team;
import main.exceptions.ExDuplicateTeamName;
import main.exceptions.ExEmployeeAddedToTeam;
import main.exceptions.ExInsufficientCommandArguments;
import main.exceptions.ExNoSuchEmployee;

public class CmdSetupTeam extends RecordedCommand {
    Team t;

    public void execute(String[] cmdParts) {

        try {
            if (cmdParts.length < 3) {
                throw new ExInsufficientCommandArguments();
            }
            Company company = Company.getInstance();
            if (company.teamAdded(cmdParts[1])) {
                throw new ExDuplicateTeamName();
            }
            if (company.employeeAdded(cmdParts[2]) == false) {
                throw new ExNoSuchEmployee();
            }
            if (company.employeeAddedToTeam(cmdParts[2])) {
                throw new ExEmployeeAddedToTeam();
            }
            t = company.createTeam(cmdParts[1], cmdParts[2]);
            addUndoCommand(this);
            clearRedoList();

            System.out.println("Done.");
        } catch (ExInsufficientCommandArguments e) {
            System.out.println(e.getMessage());
        } catch (ExDuplicateTeamName e) {
            System.out.println(e.getMessage());
        } catch (ExNoSuchEmployee e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeeAddedToTeam e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        Company.getInstance().deleteTeam(t);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Company.getInstance().addTeam(t);
        addUndoCommand(this);
    }
}
