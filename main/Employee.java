package main;

import java.util.*;

public class Employee implements Comparable<Employee>{

    private String name;
    private Team aTeam;
    private ArrayList<Team> joinedTeams = new ArrayList<>();
    private ArrayList<Day> teamJoinDates = new ArrayList<>();

    // Constructor
    public Employee(String n) {
        this.name = n;
    }

    @Override
    public int compareTo(Employee another) {
        if (this.name.equals(another.name)) {
            return 0;
        } else if (this.name.compareTo(another.name) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    // Implementing required methods

    public String getName() {
        return this.name;
    }

    public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) {
        // search in the arrayList for the employee with the given name
        for (Employee e : list) {
            if (e.getName().equals(nameToSearch)) {
                return e;
            }
        }
        return null;
    }


    public void joinToTeam(Team t) {
        this.aTeam = t;
        Day d = new Day(SystemDate.getInstance().toString());
        this.teamJoinDates.add(d);
        this.joinedTeams.add(t);
        if (t.getProject() != null) {
            t.getProject().addAssignedTeamMembersHistory(this);
        }
    }
    public void joinToTeam(Employee e, Team t) {
        this.aTeam = t;
        t.addMember(e);
    }

    public void addLastDay(){
        Day d = new Day(SystemDate.getInstance().previous().toString());
        this.teamJoinDates.add(d);
    }

    public Team getCurrentTeam() {
		return this.aTeam;
	}

    public void quitTeam() {
        this.aTeam = null;
	}

    public void listJoinedTeams() {
        System.out.println("The teams that " + this.name + " has joined: ");
        for(int i=0; i<joinedTeams.size(); i++){
            if(joinedTeams.get(i).getName().equals(aTeam.getName())){
                if(joinedTeams.get(i).getHeadName().equals(this.name)){
                    System.out.println(joinedTeams.get(i).getName() + " (" + teamJoinDates.get(teamJoinDates.size()-1)+ " to --), as a leader");
                }
                else{
                    System.out.println(joinedTeams.get(i).getName() + " (" + teamJoinDates.get(teamJoinDates.size()-1)+ " to --)");

                }

            }
            else{
                System.out.println(joinedTeams.get(i).getName() + " (" + teamJoinDates.get(2*i)+ " to " + teamJoinDates.get(2*i+1) +")");
            }
        }
    }
    
    public String projectStartDate(Project p, Team t){
        for(int i=0; i<joinedTeams.size(); i++){
            if(joinedTeams.get(i).getName().equals(t.getName())){
                if(teamJoinDates.get(2*i).compareTo(p.getProjectTakenDate()) == -1){// if employee joined team earlier than project taken date
                    return p.getProjectTakenDate().toString();
                }
                else{
                    return teamJoinDates.get(2*i).toString();
                }
            }
        }
        return "";
    }

    public String projectEndDate(Project p, Team t){
        for(int i=0; i<joinedTeams.size(); i++){
            if(joinedTeams.get(i).getName().equals(t.getName())){
                if(teamJoinDates.size()==2*i+1){
                    return p.getProjectFinishedDate().toString();
                }
                else if(teamJoinDates.size()>2*i+1 && p.getProjectFinishedDate().compareTo(teamJoinDates.get(2*i+1)) == -1){
                    return p.getProjectFinishedDate().toString();
                }
                else{
                    return teamJoinDates.get(2*i+1).toString();
                }
            }
        }
        return "";
    }

    public Day getProjectEndDay(Project p, Team t){
        for(int i=0; i<joinedTeams.size(); i++){
            if(joinedTeams.get(i).getName().equals(t.getName())){
                if(teamJoinDates.size()==2*i+1){
                    return p.getProjectFinishedDate();
                }
                else if(teamJoinDates.size()>2*i+1 && p.getProjectFinishedDate().compareTo(teamJoinDates.get(2*i+1)) == -1){
                    return p.getProjectFinishedDate();
                }
                else{
                    return teamJoinDates.get(2*i+1);
                }
            }
        }
        return null;
    }

}
