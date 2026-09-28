/* Student Inteface program will include all the methods that will be implemented by the student user

@author Brianna Graham 
*/

import java.util.ArrayList;

public Interface StudentInterface{
    
    //View all the open courses 
    public void viewOpenCourses(ArrayList<Courses> courses);

    // Registers Students into a course 
    public boolean registerForCourse(ArrayList<Courses> courses, String courseName, int courseSectionNumber);

    //Withdraw from a course 
    public boolean withdrawFromCourse(ArrayList<Courses> courses, String courseName, int courseSectionNumber);

    // View all the courses the student is enrolled in 
    public void viewMyCurrentCourses(ArrayList<Courses> courses);

}