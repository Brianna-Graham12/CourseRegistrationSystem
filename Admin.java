/*
Admin file shows all the methods that only an admin can use. 
Inherits from user and implements from admin interface.

@author Brianna Graham
*/

import java.io.*;
import java.util.*;

public class Admin extends User, implements AdminInterface{

    // Only one admin username and password 

    private static final String adminUserName = "Admin";
    private static final String adminPassword = "Admin001";

    // Scanner for admin input 

    private Scanner input = new Scanner(System.in);

    // Different options for an instructor to edit a course 

    public enum courseEdit{MAXIMUMS_TUDENTS, INSTRUCTOR, SECTION, LOCATION }

    //Constructor 
    public Admin(){
        super(adminUserName, adminPassword);
    }

    //@return userType, returns admin 
    //Returns type of user 

    @Override 
    public userType getUserType(){
        returns userType.ADMIN;
    }
}