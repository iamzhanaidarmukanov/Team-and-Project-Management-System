package main;

import java.util.*;

public class Team implements Comparable<Team> {

    private String teamName;
    private Employee head;
    private Day dateSetup; // the date this team was setup
    private Project currentProject;
    private ArrayList<Employee> members = new ArrayList<>();

    // Constructor
    public Team(String n, Employee hd) {
        this.teamName = n;
        this.head = hd;
        this.dateSetup = SystemDate.getInstance().clone();
        this.members.add(head);
        this.head.joinToTeam(this);
    }


    // Getters
    public String getName() {
        return this.teamName;
    }
    public String getHeadName() {
        return this.head.getName();
    }
    public Project getProject() {
        return this.currentProject;
    }


    public static void list(ArrayList<Team> list) {
        // Learn: "-" means left-aligned
        System.out.printf("%-15s%-10s%-14s%-20s\n", "Team Name", "Leader", "Setup Date", "Members");

        for (Team t : list) {
            System.out.printf("%-15s%-10s%-14s%-30s\n", t.teamName, t.head.getName(), t.dateSetup, t.listMembers());
        }
    }


    @Override
    public int compareTo(Team another) {
        if (this.teamName.equals(another.teamName)) {
            return 0;
        } else if (this.teamName.compareTo(another.teamName) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public void addMember(Employee e) {
        this.members.add(e);
    }

    public void deleteMember(Employee e) {
        this.members.remove(e);
    }

    public ArrayList<Employee> getMembers(){
        return members;
    }

    public String listMembers() {
        if (this.members.size() == 0) {
            return "(no member)";
        } 
        else {
            this.members.remove(head);
            Collections.sort(this.members);
            String membersString = "";
            if (this.members.size() == 0) {
                return "(no member)";
            }
            for (int i = 0; i < this.members.size() - 1; i++) {
                membersString += this.members.get(i).getName() + " ";
            }
            membersString += this.members.get(this.members.size() - 1).getName();
            this.members.add(head);
            return membersString;
        }
    }

    public static Team searchTeam(ArrayList<Team> list, String nameOfTeam) {
        for (Team t : list) {
            if (t.getName().equals(nameOfTeam)) {
                return t;
            }
        }
        return null;
    }

    public void assignProject(Project p) {
        this.currentProject = p;
    }
    public void dropPoject(Project p) {
        this.currentProject = null;
    }

}
