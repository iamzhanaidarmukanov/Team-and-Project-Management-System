package main;

import java.util.*;
import main.exceptions.ExInsufficientCommandArguments;

public class CmdStartNewDay extends RecordedCommand {

    ArrayList<String> allDates = new ArrayList<>();

    @Override
    public void execute(String[] cmdParts) {
        try {

            // Insufficient arguments exception handling
            if (cmdParts.length < 2)
                throw new ExInsufficientCommandArguments();

            SystemDate ss = SystemDate.getInstance();

            allDates.add(ss.toString());
            ss.set(cmdParts[1]);
            System.out.println("Done.");

            clearRedoList();
			addUndoCommand(this);
        } catch (ExInsufficientCommandArguments e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void undoMe() {
        SystemDate ss = SystemDate.getInstance();

		String previousDate = allDates.get(allDates.size() - 1);
		allDates.remove(allDates.size() - 1);
		allDates.add(ss.toString());
		ss.set(previousDate);

		addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        SystemDate ss = SystemDate.getInstance();

		String previousDate = allDates.get(allDates.size() - 1);
		allDates.remove(allDates.size() - 1);
		allDates.add(ss.toString());
		ss.set(previousDate);

		addUndoCommand(this);
    }
    
}
