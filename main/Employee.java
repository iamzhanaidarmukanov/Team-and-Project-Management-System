package main;

import java.util.*;

public class Employee implements Comparable<Employee>{

    private String name;
    private Team aTeam;

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
        t.addMember(this);
    }
    public void joinToTeam(Employee e, Team t) {
        this.aTeam = t;
        t.addMember(e);
    }

    public Team getCurrentTeam() {
		return this.aTeam;
	}

    public void quitTeam() {
        this.aTeam = null;
	}
    
}
