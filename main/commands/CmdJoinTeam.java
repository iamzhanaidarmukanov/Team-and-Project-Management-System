package main.commands;

import main.Company;
import main.Employee;
import main.RecordedCommand;
import main.Team;
import main.exceptions.ExEmployeeAddedToTeam;
import main.exceptions.ExInsufficientCommandArguments;
import main.exceptions.ExNoSuchEmployee;
import main.exceptions.ExNoSuchTeam;

public class CmdJoinTeam extends RecordedCommand 
{
	private Employee em;
    private Team te;

	@Override
	public void execute(String[] cmdParts) {
		try {
            if (cmdParts.length<3) {
                throw new ExInsufficientCommandArguments();
            }
            Company company = Company.getInstance();
            if(company.employeeAdded(cmdParts[1])==false){
                throw new ExNoSuchEmployee();
            }
            if(company.employeeAddedToTeam(cmdParts[1])==true){
				throw new ExEmployeeAddedToTeam();
            }
            if(company.teamAdded(cmdParts[2])==false){
                throw new ExNoSuchTeam();
            }
            em = company.getEmployee(cmdParts[1]);
            te = company.getTeam(cmdParts[2]);
            company.addToTeam(em, te);
            addUndoCommand(this); 
            clearRedoList(); 
            System.out.println("Done.");

        } catch (ExInsufficientCommandArguments e) {
            System.out.println(e.getMessage());
        } catch (ExNoSuchEmployee e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeeAddedToTeam e) {
            System.out.println(e.getMessage());
        } catch (ExNoSuchTeam e) {
            System.out.println(e.getMessage());
        }
		
	}
	@Override
	public void undoMe()
	{
        Company.getInstance().deleteFromTeam(em, te);
		addRedoCommand(this); 
	}
	
	@Override
	public void redoMe()
	{
        Company.getInstance().addToTeam(em, te);
        addUndoCommand(this); 
	}
}