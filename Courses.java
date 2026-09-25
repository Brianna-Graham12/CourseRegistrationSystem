/*
Program that creates the course object and serilalizes it 
Checks if course is full and allows for student to remove themself from the course 




@author Brianna Graham
*/

import java.io.Serializable;
import java.util.ArrayList;


//Class that holds all of the information for one course
public class Courses implements Serializable{


    //Course variables
    private String courseName;
    private String courseId;
    private int maximumStudents;
    private int currentStudents;

    //ArrayList that stores the students registered in the course
    private ArrayList<Students> registeredStudents;

    private String courseInstructor;
    private int courseSectionNumber;
    private String courseLocation;



    /*
    Constructor used to create a course

    @param courseName, name of the course
    @param courseId, ID of the course
    @param maximumStudents, maximum amount of students allowed
    @param courseInstructor, instructor of the course
    @param courseSectionNumber, section number of the course
    @param courseLocation, location of the course
    */

    public Courses(String courseName, String courseId, int maximumStudents,
                     String courseInstructor, int courseSectionNumber,
                   String courseLocation){

        this.courseName = courseName;
        this.courseId = courseId;
        this.maximumStudents = maximumStudents;

        //All courses start off with no students
        this.currentStudents = 0;
        this.registeredStudents = null;

        this.courseInstructor = courseInstructor;
        this.courseSectionNumber = courseSectionNumber;
        this.courseLocation = courseLocation;

    }



    //Gets the course name

    /*
    @return String name of the course
    */

    public String getCourseName(){
        return courseName;

    }

    //Gets the course ID

    
    //@return String ID of the course
    

    public String getCourseId(){
        return courseId;

    }

    //Gets the maximum amount of students allowed in the course
    //@return int maximum amount of students
    
    public int getMaximumStudents(){
        return maximumStudents;

    }

    //Gets the current amount of students in the course

    
    // @return int, amount of students currently registered
    

    public int getCurrentStudents(){
        return currentStudents;

    }

    //Gets the ArrayList of students registered in the course

    
    //@return ArrayList, students that are registered in the course
    

    public ArrayList<Students> getRegisteredStudents(){
        return registeredStudents;

    }



    //Gets the course instructor

    /*
    @return String, instructor of the course
    */

    public String getCourseInstructor(){
        return courseInstructor;

    }



    //Gets the course section number

    /*
    @return int, section number of the course
    */

    public int getCourseSectionNumber(){
        return courseSectionNumber;

    }



    //Gets the course location

    /*
    @return String, location of the course
    */

    public String getCourseLocation(){
        return courseLocation;

    }



    //Sets a new maximum amount of students

    /*
    @param maximumStudents, new maximum amount of students
    */

    public void setMaximumStudents(int maximumStudents){
        this.maximumStudents = maximumStudents;

    }

    //Sets a new course instructor
    //@param courseInstructor, new instructor for the course
    
    public void setCourseInstructor(String courseInstructor){
        this.courseInstructor = courseInstructor;
    }

    //Sets a new course section number
    //@param courseSectionNumber, new section number for the course

    public void setCourseSectionNumber(int courseSectionNumber){
        this.courseSectionNumber = courseSectionNumber;

    }

    //Sets a new course location

    /*
    @param courseLocation, new location for the course
    */

    public void setCourseLocation(String courseLocation){
        this.courseLocation = courseLocation;

    }

    //Checks if the course is full
    //@return boolean, checks if the course is full true/false

    public boolean isFull(){
        return currentStudents >= maximumStudents;

    }

    /*
    Checks if student is already registered in the course
    @param student student that is being checked
    @return boolean  checks if student is already registered true/false
    */

    public boolean hasStudent(Students student){

        //If there are no students in the course
        if(registeredStudents == null){
            return false;
        }

        //Loops through all students registered in the course
        for(Students registered : registeredStudents){

            //Checks if usernames are the same
            if(registered.getUsername().equalsIgnoreCase(student.getUsername())){
                return true;
            }
        }

        //Student was not found
        return false;
    }

    /*Adds a student into the course
    @param student student that is being added
    @return boolean checks if student was successfully added true/false
    */

    public boolean addStudent(Students student){
        //If course is full student cannot be added
        if(isFull()){
            return false;
        }
        //If student is already in course they cannot be added again
        if(hasStudent(student)){
            return false;
        }

        //If this is the first student create the ArrayList
        if(registeredStudents == null){
            registeredStudents = new ArrayList<Students>();

        }

        //Adds student into the registered students ArrayList
        registeredStudents.add(student);

        //Adds one to the amount of students in the course
        currentStudents++;
        return true;
    }
    /*
    Removes a student from the course
    @param student, student that is being removed
    @return boolean, checks if student was successfully removed true/false
    */

    public boolean removeStudent(Students student){
        //If there are no students there is nobody to remove
        if(registeredStudents == null){
            return false;

        }
        //Loops through the registered students
        for(int i = 0; i < registeredStudents.size(); i++){

            //Gets the student at the current index
            Students registered = registeredStudents.get(i);

            //Checks if the usernames are the same
            if(registered.getUsername().equalsIgnoreCase(student.getUsername())){

                //Removes student from ArrayList
                registeredStudents.remove(i);

                //Subtracts one from the amount of students
                currentStudents--;

                return true;
            }
        }

        //Student was not found in the course
        return false;

    }
    //@return a display table for one course 
    public String displayCourse(){
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("                  COURSE INFORMATION");
        System.out.println("-------------------------------------------------------");

        System.out.println("Course Name:               " + courseName);
        System.out.println("Course Instructor:         " + courseInstructor);
        System.out.println("Course Id:                 " + courseId);
        System.out.println("Course Location:           " + courseLocation);
        System.out.println("Course Section Number:     " + courseSectionNumber);
        System.out.println("Seats left:                " + currentStudents "/" + maximumStudents);
    } 


}