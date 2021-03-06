package main;

import java.util.*;

public class Project implements Comparable<Project> {

    private String projectName;
    private Integer manpower;
    private Team assignedTeam;
    private Day startDate;
    private Day endDate;
    private ArrayList<Employee> workerRecordHistory = new ArrayList<>();;

    public Project(String pN, Integer mp) {
        this.projectName = pN;
        this.manpower = mp;
    }

    // Getters

    public String getName() {
        return this.projectName;
    }

    public Integer getManpower() {
        return manpower;
    }

    public Team getAssignedTeam() {
        return assignedTeam;
    }

    public String getAssignedTeamStr() {
        if (assignedTeam != null) {
            return assignedTeam.getName();
        }
        return "(Not Assigned)";
    }

    public Day getStartDate() {
        if (startDate != null) {
            return startDate;
        }
        return null;
    }
    public String getStartDateStr() {
        if (startDate != null) {
            return startDate.toString();
        }
        return "";
    }

    public Day getEndDate() {
        if (endDate != null) {
            return endDate;
        }
        return null;
    }
    public String getEndDateStr() {
        if (endDate != null) {
            return endDate.toString();
        }
        return "";
    }

    // Comparing projects
    @Override
    public int compareTo(Project another) {
        if (this.projectName.equals(another.projectName)) {
            return 0;
        } else if (this.projectName.compareTo(another.projectName) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    // Assignment handling
    public void assignTeam(Team t) {
        this.assignedTeam = t;
    }

    public void setStartDate(Day sd) {
        this.startDate = sd;
    }

    public void setEndDate(Day ed) {
        this.endDate = ed;
    }

    public void unassignTeam() {
        this.assignedTeam = null;
    }

    public void resetStartDate() {
        this.startDate = null;
    }

    public void resetEndDate() {
        this.endDate = null;
    }


    // Listing
    public static void list(ArrayList<Project> list) {
        // Learn: "-" means left-aligned
        System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", "Project", "Est manpower", "Team", "Start Day", "End Day");

        for (Project p : list) {
            System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", p.projectName, p.manpower + " man-days", p.getAssignedTeamStr(), p.getStartDateStr(), p.getEndDateStr());
        }
    }

    // Searching project
    public static Project searchProject(ArrayList<Project> list, String pT) {
        for (Project p : list) {
            if (p.getName().equals(pT)) {
                return p;
            }
        }
        return null;
    }

    // Is Assigned?
    public boolean isTeamAssigned() {
		if (assignedTeam != null) {
            return true;
        }
        return false;
    }

    public void listWorkerDetails() {
        ArrayList<String> workerRecords = new ArrayList<>();
        System.out.println("Est manpower : " + this.manpower + " man-days");
        System.out.println("Team         : " + this.assignedTeam.getName() + " (Leader is " + this.assignedTeam.getHeadName() + ")");
        System.out.println("Work period  : " + this.startDate.toString() + " to " + this.endDate.toString());
        System.out.println("\nMembers: ");
        for(int i=0; i<workerRecordHistory.size(); i++){
            if(workerRecordHistory.get(i).getProjectEndDay(this, this.assignedTeam).compareTo(this.startDate) != -1){
                workerRecords.add("  " + workerRecordHistory.get(i).getName() 
                                + " (" + workerRecordHistory.get(i).projectStartDate(this, assignedTeam) 
                                + " to " + workerRecordHistory.get(i).projectEndDate(this, assignedTeam) + ")");
            }
        }
        Collections.sort(workerRecords);
        for(String s: workerRecords){
            System.out.println(s);
        }
    }

    public void addAssignedTeamMembersHistory(Employee e){
        workerRecordHistory.add(e);
    }

    public Day getProjectTakenDate(){
        return startDate;
    }

    public Day getProjectFinishedDate(){
        return endDate;
    }


}
