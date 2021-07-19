package main;

import java.util.*;
import java.util.Collections;

public class Company {

    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private ArrayList<Project> allProjects;
    
    // Using Singleton to instanciate company, to have only one company
    private static Company instance = new Company();

    // Constructor
    private Company() {
        allEmployees = new ArrayList<>();
        allTeams = new ArrayList<>();
        allProjects = new ArrayList<>();
    }

    public static Company getInstance() {
        return instance;
    }


    // Creating Employee, Team or Project
    public Employee createEmployee(String n) {
        Employee e = new Employee(n);
        allEmployees.add(e);
        Collections.sort(allEmployees);
        return e; // the return value is useful for later undoable command.
    }
    public Team createTeam(String teamName, String teamLeader) {
        Employee e = Employee.searchEmployee(allEmployees, teamLeader);
        Team t = new Team(teamName, e);
        allTeams.add(t);
        Collections.sort(allTeams);
        return t;
    }
    public Project createProject(String projectName, String manPower) {
        Project p = new Project(projectName, Integer.parseInt(manPower));
        allProjects.add(p);
        Collections.sort(allProjects);
        return p;
	}

    // Getters
    public Employee getEmployee(String name) {
        Employee e = Employee.searchEmployee(allEmployees, name);
        return e;
    }
    public Team getTeam(String name) {
        Team t = Team.searchTeam(allTeams, name);
        return t;
    }
    public Project getProject(String name) {
        Project p = Project.searchProject(allProjects, name);
        return p;
    }

    // Adding
    public void addEmployee(Employee e) {
        allEmployees.add(e);
        Collections.sort(allEmployees);
    }
    public void addTeam(Team t) {
        Collections.sort(allTeams);
        allTeams.add(t);
    }
    public void addProject(Project p) {
        Collections.sort(allProjects);
        allProjects.add(p);
    }

    // Deleting
    public void deleteEmployee(Employee e) {
        allEmployees.remove(e);
    }
    public void deleteTeam(Team t) {
        allTeams.remove(t);
    }
    public void deleteProject(Project p) {
        allProjects.remove(p);
    }

    // Listing
    public void listEmployees() {
        for(int i=0; i<allEmployees.size(); i++){
            if(allEmployees.get(i).getCurrentTeam()!=null){
                System.out.println(allEmployees.get(i).getName() + " (" + 
                                    allEmployees.get(i).getCurrentTeam().getName() + ")" );
            }
            else{
                System.out.println(allEmployees.get(i).getName());
            }
        }
    }
    public void listTeams() {
        Collections.sort(allTeams);
        Team.list(allTeams);
    }
    public void listProjects() {
        Collections.sort(allProjects);
        Project.list(allProjects);
    }


    // Checking whether already exists
    public boolean employeeAdded(String name){
        if(Employee.searchEmployee(allEmployees, name) != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean teamAdded(String tN){
        if(Team.searchTeam(allTeams, tN) != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean projectAdded(String pN) {
        if (Project.searchProject(allProjects, pN) != null) {
            return true;
        }
        else {
            return false;
        }
    }

	public boolean employeeAddedToTeam(String name) {
        for(int i=0; i < allTeams.size(); i++) {
            if(allTeams.get(i).getHeadName().equals(name)) {
                return true;
            }
            for(int j=0; j<allTeams.get(i).getMembers().size(); j++) {
                if(allTeams.get(i).getMembers().get(j).getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addToTeam(Employee e, Team t) {
        if(e.getCurrentTeam()!=null){
            e.getCurrentTeam().deleteMember(e);
            e.quitTeam();
            e.addLastDay();
        }
    
        e.joinToTeam(t);
        t.addMember(e);
	}

    public void deleteFromTeam(Employee e, Team t) {
        t.deleteMember(e);
        e.quitTeam();
    }

    public void teamTakeProject(Team t, Project p, Day startDay, Day endDay) {
        t.assignProject(p);
        p.assignTeam(t);
        t.bookDaysWorkingOnProject(startDay, endDay);
        p.setStartDate(startDay);
        p.setEndDate(endDay);
	}
	public void teamDropProject(Team t, Project p, Day startDay, Day endDay) {
        t.dropPoject(p);
        p.unassignTeam();
        t.unbookDaysWorkingOnProject(startDay, endDay);
        p.resetStartDate();
        p.resetEndDate();        
    }

    public void suggestTeam(Project p, Integer manpower) {
        ArrayList<Day> finishDays = new ArrayList<>();
        for (int i = 0; i < allTeams.size(); i++) {
            finishDays.add(allTeams.get(i).getFinishDay(manpower));
        }
        Day earliestFinishDay = new Day(finishDays.get(0).toString());
        for(int i = 0; i < finishDays.size(); ++i) {
            if (finishDays.get(i).compareTo(earliestFinishDay) == -1)
                earliestFinishDay = finishDays.get(i);
        }
        for (int i = 0; i < finishDays.size(); ++i) {
            if (earliestFinishDay.toString().equals(finishDays.get(i).toString()))
                System.out.println(allTeams.get(i).getName() + " (Work period:  " 
                                    + allTeams.get(i).getStartDay(manpower).toString() + " to " 
                                    + allTeams.get(i).getFinishDay(manpower).toString() + ")");
        }
    }

    public void listEmployeeDetails(String name) {
        Employee e = getEmployee(name);
        e.listJoinedTeams();
    }
}
