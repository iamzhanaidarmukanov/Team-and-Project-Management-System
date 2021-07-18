package main.commands;

import main.Company;
import main.Day;
import main.Project;
import main.RecordedCommand;
import main.SystemDate;
import main.Team;
import main.exceptions.ExAreadyAssignedTeamProject;
import main.exceptions.ExCurrentDateInvalid;
import main.exceptions.ExInsufficientCommandArguments;
import main.exceptions.ExInvalidDate;
import main.exceptions.ExNoSuchProject;
import main.exceptions.ExNoSuchTeam;

public class CmdTakeProject extends RecordedCommand {

    private Team t;
    private Project p;
    private Day startDay;
    private Day endDay;

    @Override
	public void execute(String[] cmdParts)
	{
        try {    
            if (cmdParts.length<4) {
                throw new ExInsufficientCommandArguments();
            }
            Company company = Company.getInstance();
            if(company.teamAdded(cmdParts[1])==false) {
                throw new ExNoSuchTeam();
            }
            if(company.projectAdded(cmdParts[2])==false){
                throw new ExNoSuchProject();
            }
            if(SystemDate.getInstance().toString().equals(cmdParts[3])){
                throw new ExCurrentDateInvalid();
            }
            if(Day.isValid(cmdParts[3])==false){
                throw new ExInvalidDate();
            }
            t = company.getTeam(cmdParts[1]);
            p = company.getProject(cmdParts[2]);
            if(p.isTeamAssigned()==true){
                throw new ExAreadyAssignedTeamProject();
            }
            startDay = new Day(cmdParts[3]);

            int daysToWork = p.getManpower() / t.getMembers().size();
            endDay = startDay.plusNumOfDays(daysToWork);

            // Exception that team is busy

            company.teamTakeProject(t,p, startDay, endDay);


            addUndoCommand(this); 
            clearRedoList(); 

            System.out.println("Done.");

        } catch (ExInsufficientCommandArguments e){
            System.out.println(e.getMessage());
        } catch (ExCurrentDateInvalid e){
            System.out.println(e.getMessage());
        } catch (ExNoSuchTeam e){
            System.out.println(e.getMessage());
        } catch (ExNoSuchProject e){
            System.out.println(e.getMessage());
        } catch (ExAreadyAssignedTeamProject e){
            System.out.println(e.getMessage());
        } catch (ExInvalidDate e){
            System.out.println(e.getMessage());
        }
	}
	
	@Override
	public void undoMe()
	{
        Company.getInstance().teamDropProject(t, p, startDay, endDay);
		addRedoCommand(this); 
	}
	
	@Override
	public void redoMe()
	{
        Company.getInstance().teamTakeProject(t,p, startDay, endDay);
		addRedoCommand(this); 
	}

}
