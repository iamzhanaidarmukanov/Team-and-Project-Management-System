package main.commands;

import main.Company;
import main.Employee;
import main.RecordedCommand;
import main.Team;
import main.exceptions.ExInsufficientCommandArguments;
import main.exceptions.ExNoSuchEmployee;
import main.exceptions.ExNoSuchTeam;
import main.exceptions.ExSameTeam;

public class CmdChangeTeam extends RecordedCommand 
{
	private Employee e;
    private Team currentTeam;
    private Team newTeam;

	@Override
	public void execute(String[] cmdParts)
	{
        try {    
            if (cmdParts.length<3)
                throw new ExInsufficientCommandArguments();
            
            Company company = Company.getInstance();

            if(company.employeeAdded(cmdParts[1])==false){
                throw new ExNoSuchEmployee();
            }

            if(company.teamAdded(cmdParts[2])==false){
                throw new ExNoSuchTeam();
            }

            e = company.getEmployee(cmdParts[1]);
            currentTeam = company.getTeam(e.getCurrentTeam().getName());
            newTeam = company.getTeam(cmdParts[2]);

            if(currentTeam.equals(newTeam)){
                throw new ExSameTeam();
            }

            company.addToTeam(e, newTeam);


            addUndoCommand(this); 
            clearRedoList(); 

            System.out.println("Done.");

        } catch (ExInsufficientCommandArguments e){
            System.out.println(e.getMessage());
        } catch(ExSameTeam e){
            System.out.println(e.getMessage());
        }catch(ExNoSuchEmployee e){
            System.out.println(e.getMessage());
        }catch(ExNoSuchTeam e){
            System.out.println(e.getMessage());
        }
	}
	
	@Override
	public void undoMe()
	{
        Company.getInstance().addToTeam(e, currentTeam);
		addRedoCommand(this); 
	}
	
	@Override
	public void redoMe()
	{
        Company.getInstance().addToTeam(e, newTeam);
        addUndoCommand(this); 
	}
}