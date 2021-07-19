package main.commands;

import main.Company;
import main.RecordedCommand;
import main.exceptions.ExInsufficientCommandArguments;
import main.exceptions.ExNoSuchEmployee;

public class CmdShowEmployeeDetails extends RecordedCommand{

    public void execute(String[] cmdParts) {
        try {
            Company company = Company.getInstance();
            if (cmdParts.length < 2) {
                throw new ExInsufficientCommandArguments();
            }
            if (company.employeeAdded(cmdParts[1]) == false) {
                throw new ExNoSuchEmployee();
            }
            company.listEmployeeDetails(cmdParts[1]);
        } catch (ExInsufficientCommandArguments e) {
            System.out.println(e.getMessage());
        } catch (ExNoSuchEmployee e) {
            System.out.println(e.getMessage());
        } 
    }

    @Override
    public void undoMe() {}

    @Override
    public void redoMe() {}

}
