package main.commands;

import main.Company;
import main.Project;
import main.RecordedCommand;
import main.exceptions.ExDuplicateProjectCode;
import main.exceptions.ExInsufficientCommandArguments;
import main.exceptions.ExInsufficientManpower;

public class CmdCreateProject extends RecordedCommand {
    
    private Project p;

    @Override
    public void execute(String[] cmdParts) {

        try {
            if (cmdParts.length < 3) {
                throw new ExInsufficientCommandArguments();
            }
            Company company = Company.getInstance();
            if (company.projectAdded(cmdParts[1])) {
                throw new ExDuplicateProjectCode();
            }
            if (Integer.parseInt(cmdParts[2]) <= 0) {
                throw new ExInsufficientManpower();
            }
            p = company.createProject(cmdParts[1], cmdParts[2]);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ExInsufficientCommandArguments e) {
            System.out.println(e.getMessage());
        } catch (ExDuplicateProjectCode e) {
            System.out.println(e.getMessage());
        } catch (ExInsufficientManpower e) {
            System.out.println(e.getMessage());
            System.out.println("Consider changing " + cmdParts[2] + " to a positive non-zero amount.");
        } catch(NumberFormatException e){
            System.out.println("Wrong number format -- Please enter an integer.");
        }
    }

    @Override
    public void undoMe() {
        Company.getInstance().deleteProject(p);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Company.getInstance().addProject(p);
        addUndoCommand(this);
    }
}