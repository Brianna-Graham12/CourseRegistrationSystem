/*
 Admin interface will include all the methods that will be implemeted by admin.  
 @author Brianna Graham
 */

import.java.util;

public Interface AdminInterface{

    // Creates Course 
    public void createCourse(ArrayList<Courses> courses, Courses course);
    
    // Deletes Course 
    public boolean deleteCourse(ArrayList<Courses> courses, String courseId, int courseSectionNumber);

    //Editing a course 
    public void editCourse(ArrayList<Courses> courses, String courseId, int courseSectionNumber);

    //Displays Course by course id
    //Shows every section 
    public void viewCourse(ArrayList<Courses> courses, String courseId);

    //Registers a student 
    public void registerStudent(ArrayList<Student> students, Students student);

    //View the full courses 
    public void viewFullCourses(ArrayList<Courses> courses);

    //Writes the full course to a seperate file 
    public void writeFullCourses(ArrayList<Courses> courses, String fileName);

    // View the students in this particular course 
    public void viewStudentsInCourse(ArrayList<Courses> courses, String courseId, int courseSectionNumber);

    //Be able to view a specific students courses
    public void viewStudentsCourses(ArrayList<Courses> courses, String firstName, String lastName);

    // Sort Courses by their available seats 
    public void sortCourses(ArrayList<Courses> courses);
}
