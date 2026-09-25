/*

An java file that sets up basic parameters for both admin and student.
User stores login information for student and admin and tells what type of user is logging in.

@author Brianna Graham 

*/

public class User{

    private String username;         
    private String password; 
    private String firstName;
    private String lastName;

 /*   

    Declares an enum for the different avaiable options for usertype. 
    Would be cleaner than searching through an array or an if statement. 
    Use enum in a switch statement to check for correect type and catch if given an answer thats not in the enum.
    
*/

    public enum userType{ADMIN, STUDENT};
    
   
  /* 
    @param username - username to login to CRS
    @param paassword - password used to login to CRS
    @param firstName - firstName of student, for admin would be a default 
    @param lastName - lastName of student , for admin would be default 

*/
   
 //   Constructor method 
  //  Initializes a brand new user for student who uses all four parameters

    public User(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password; 
        this.firstName = firstName; 
        this.lastName = lastName;
    }

    // Constructor method for 2 parameters, for admin type 

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
   
    /* A get method that returns the users username 
     @return username 
    */

    public String getUsername(){
        return username; 
    }

    /* Setter method is not needed because im auto - generating username for student 

     The admin already has a set username 
    
     Gets the Firstname 
     @return firstName

    */

    public String getfirstName(String firstName){
        return firstName;
    }

    /* Gets lastname of user 
    @return lastName 
    */

    public String getlastName(){
        return lastName;
    }

    /* A method that takes a number n , and will give out the user type 
    //If option is not in the usertypes 
    @throws IllegalArgumentException if string is not a user
    @return userType either admin or student
    */

    public userType whichUser(String option){
        switch(option.toUpperCase()){
            case "ADMIN":
                return userType.ADMIN;
            case "STUDENT":
                return userType.STUDENT;    
            default:
                throw new IllegalArgumentException("This is an invalid input. Please enter either student or admin.");
        } 
    }

    /*
    @return boolean, checks if the password is correct true/false 
    
    Takes in users entered password and verifiees with password in file 
     */  

    public boolean verifyPassword(String enteredPassword){

        return password.equals(enteredPassword);

    }

    // User student needs to set a password in the case of a forgotten password 

    public void setPassword(String password){
        this.password = password; 
    }
}