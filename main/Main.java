package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

               if (cmdParts[0].equals("startNewDay")) {
                   (new CmdStartNewDay()).execute(cmdParts);
               }
               if (cmdParts[0].equals("hire")) {
                   (new CmdHire()).execute(cmdParts);
               }


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
