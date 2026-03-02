package model.Users;

import model.Users.User;

/**
 * A Manager has a name and an integer ID.
 */
public class Manager extends User {
    private final int managerID;

    public Manager(String name, int managerID) {
        super(name);
        this.managerID = managerID;
    }

    public int getManagerID() {
        return managerID;
    }

    @Override
    public String toString() {
        
        return String.format("%s (Manager)", getName());
    }
}
