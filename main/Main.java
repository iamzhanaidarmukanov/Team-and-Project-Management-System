package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import main.commands.CmdChangeTeam;
import main.commands.CmdCreateProject;
import main.commands.CmdHire;
import main.commands.CmdJoinTeam;
import main.commands.CmdListEmployees;
import main.commands.CmdListProject;
import main.commands.CmdListTeams;
import main.commands.CmdSetupTeam;
import main.commands.CmdShowEmployeeDetails;
import main.commands.CmdStartNewDay;
import main.commands.CmdSuggestTeam;
import main.commands.CmdTakeProject;

public class Main {

    public static void main(String[] args) {
        
        // Declaring in scanner to read input from keyboard
        Scanner in = new Scanner(System.in);

        // Reading filepath name to the testcases directory
        System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
        
        // Testcases file object
        Scanner inFile = null;

        try {

           inFile = new Scanner(new File(filepathname));

           String commandLine = inFile.nextLine(); // Input test/a1.txt
           String[] commandLineParts = commandLine.split("\\|");

           System.out.println("\n> " + commandLine);
           SystemDate.createTheInstance(commandLineParts[1]);

           while(inFile.hasNext()) {
               String cmdLine = inFile.nextLine().trim();
               if (cmdLine.equals("")) {
                   continue;
               }

               System.out.println("\n> " + cmdLine);
               String[] cmdParts = cmdLine.split("\\|");

               if (cmdParts[0].equals("startNewDay")) {(new CmdStartNewDay()).execute(cmdParts);}
               else if (cmdParts[0].equals("undo")) {RecordedCommand.undoOneCommand();}
               else if (cmdParts[0].equals("redo")) {RecordedCommand.redoOneCommand();}
               else if (cmdParts[0].equals("hire")) {(new CmdHire()).execute(cmdParts);}
               else if (cmdParts[0].equals("setupTeam")) {(new CmdSetupTeam()).execute(cmdParts);}
               else if (cmdParts[0].equals("createProject")) {(new CmdCreateProject()).execute(cmdParts);}
               else if (cmdParts[0].equals("listEmployees")) {(new CmdListEmployees()).execute(cmdParts);} 
               else if (cmdParts[0].equals("listTeams")) {(new CmdListTeams()).execute(cmdParts);} 
               else if (cmdParts[0].equals("listProjects")) {(new CmdListProject()).execute(cmdParts);}
               else if(cmdParts[0].equals("joinTeam")) {(new CmdJoinTeam()).execute(cmdParts);}
               else if(cmdParts[0].equals("changeTeam")) {(new CmdChangeTeam()).execute(cmdParts);}
               else if(cmdParts[0].equals("takeProject")) {(new CmdTakeProject()).execute(cmdParts);}
               else if(cmdParts[0].equals("suggestTeam")) {(new CmdSuggestTeam()).execute(cmdParts);}
               else if(cmdParts[0].equals("showEmployeeDetails")) {(new CmdShowEmployeeDetails()).execute(cmdParts);}
            //    else if(cmdParts[0].equals("showProjectWorkerDetails")) {(new CmdShowProjectWorkerDetails()).execute(cmdParts);}
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } finally {
            if (inFile != null) {
                inFile.close();
            }
            in.close();
        }
    }
}
