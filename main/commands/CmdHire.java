package main.commands;

import main.Company;
import main.Employee;
import main.RecordedCommand;
import main.exceptions.ExDuplicateEmployeeName;
import main.exceptions.ExInsufficientCommandArguments;

public class CmdHire extends RecordedCommand {
    private Employee e;


    @Override
    public void execute(String[] cmdParts) {
        try {
            if (cmdParts.length<2) {
                throw new ExInsufficientCommandArguments();
            }
            Company company = Company.getInstance();
            if(company.employeeAdded(cmdParts[1])) {
                throw new ExDuplicateEmployeeName();
            }
            e = company.createEmployee(cmdParts[1]);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");

        } catch (ExDuplicateEmployeeName e) {
            System.out.println(e.getMessage());
        } catch (ExInsufficientCommandArguments e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void undoMe() {
        Company.getInstance().deleteEmployee(e);
        addRedoCommand(this);
    }


    @Override
    public void redoMe() {
        Company.getInstance().addEmployee(e);
        addUndoCommand(this);
    }

    
}
