/*
Admin file shows all the methods that only an admin can use. 
Inherits from user and implements from admin interface.

@author Brianna Graham
*/

import java.io.*;
import java.util.*;

public class Admin extends User implements AdminInterface{

    // Only one admin username and password 

    private static final String adminUserName = "Admin";
    private static final String adminPassword = "Admin001";

    // Scanner for admin input 

    private Scanner input = new Scanner(System.in);

    // Different options for an instructor to edit a course 

    public enum courseEdit{MAXIMUM_STUDENTS, INSTRUCTOR, SECTION, LOCATION }

    //Constructor 
    public Admin(){
        super(adminUserName, adminPassword);
    }

    //@return userType, returns admin 
    //Returns type of user 

    @Override 
    public userType getUserType(){
        return userType.ADMIN;
    }

    // DIsplays admin menu 
    @Override 
    public void displayMenu(){
        
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("             ADMIN MENU");
        System.out.println("-----------------------------------");
        System.out.println("1. Create Course");
        System.out.println("2. Delete Course");
        System.out.println("3. Edit Course");
        System.out.println("4. View Course");
        System.out.println("5. Register Student");
        System.out.println("6. Reports");
        System.out.println("7. Logout");
        System.out.println("-----------------------------------");
    }

    // Creates a new course 
    @Override
    public void createCourse(ArrayList<Courses> courses, Courses course){
        
        for(Courses newCourse : courses){
            if(newCourse.getCourseId().equalsIgnoreCase(course.getCourseId()) && newCourse.getCourseSectionNumber() == course.getCourseSectionNumber()){

            
                System.out.println("Course already exists.");
                return;
            }
        }

        courses.add(course);
        System.out.println("Course created succesfully.");

    }
    // Deletes a course 
    @Override
    public boolean deleteCourse(ArrayList<Courses> courses, String courseId, int courseSectionNumber){
        for(int i = 0; i < courses.size(); i++){
            Courses course = courses.get(i); 

            if(course.getCourseId().equalsIgnoreCase(courseId) && course.getCourseSectionNumber() == courseSectionNumber){
                
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    // Edits course by only allowing the admin to change certain objects 

    @Override
    public void editCourse(ArrayList<Courses> courses, String courseId, int courseSectionNumber){

        for(Courses course : courses){
            if(course.getCourseId().equalsIgnoreCase(courseId) && course.getCourseSectionNumber() == courseSectionNumber){

                System.out.println("What would you like to edit?");
                System.out.println("Maximum Students");
                System.out.println("Instructor");
                System.out.println("Section");
                System.out.println("Location");

                String choice = input.nextLine();
                try{

                    courseEdit option = courseEdit.valueOf(choice.toUpperCase().replace(" ","_"));

                    switch(option){
                        case MAXIMUM_STUDENTS:

                            System.out.print("Enter new maximum students: ");
                            int maximumStudents = input.nextInt();
                            input.nextLine();
                            course.setMaximumStudents(maximumStudents);
                            break;

                        case INSTRUCTOR:

                            System.out.print("Enter new instructor: ");
                            String instructor = input.nextLine();
                            course.setCourseInstructor(instructor);
                            break;

                        case SECTION:

                            System.out.print("Enter new section number: ");
                            int section = input.nextInt();
                            input.nextLine();
                            course.setCourseSectionNumber(section);
                            break;

                        case LOCATION:

                            System.out.print("Enter new location: ");
                            String location = input.nextLine();
                            course.setCourseLocation(location);
                            break;
                    }
                    System.out.println("Course updated succesfully");

                }
                catch(IllegalArgumentException e){
                    System.out.println("Invalid choice");

                }

                return;
            }
        }
        System.out.println("Course not found");
    }

    // Finds a course and displays the information 

    @Override
    public void viewCourse(ArrayList<Courses> courses, String courseId){

        boolean found = false;

        for(Courses course : courses){
            if(course.getCourseId().equalsIgnoreCase(courseId)){
                course.displayCourse();
                found = true;
            }
        }

        if(found == false){
            System.out.println("Course not found. ");
        }
    }

    // Registers new student at the university

    @Override
    public void registerStudent(ArrayList<Students> students, Students student){

        for(Students currentStudent : students){

            if(currentStudent.getUsername().equalsIgnoreCase(student.getUsername())){
                System.out.println("Student already exists");
                return;

            }
        }
        students.add(student);
        System.out.println("Student registered succesfully");

    }

    // Displays full courses 
    @Override
    public void viewFullCourses(ArrayList<Courses> courses){

        boolean found = false;
        for(Courses course : courses){
            if(course.isFull()){
                course.displayCourse();
                found = true;
            }
        }
        if(found == false){
            System.out.println("There are no full courses");

        }
    }

    // Writes the file course into a file 
    @Override
    public void writeFullCourses(ArrayList<Courses> courses, String fileName){

        try{

            FileWriter writer = new FileWriter(fileName);
            for(Courses course : courses){

                if(course.isFull()){

                    writer.write("Course Name: " + course.getCourseName() + "\n");
                    writer.write("Course ID: " + course.getCourseId() + "\n");
                    writer.write("Section: " + course.getCourseSectionNumber() + "\n");
                    writer.write("Students: " + course.getCurrentStudents() + "/" + course.getMaximumStudents() + "\n");
                    writer.write("-----------------------------------\n");

                }

            }

            writer.close();
            System.out.println("Full courses written to file");

        }
        catch(IOException e){
            System.out.println("Error writing to file");

        }
    }

    // View Students in a course 
    @Override
    public void viewStudentsInCourse(ArrayList<Courses> courses, String courseId, int courseSectionNumber){

        for(Courses course : courses){
            if(course.getCourseId().equalsIgnoreCase(courseId) && course.getCourseSectionNumber() == courseSectionNumber){

                ArrayList<Students> students = course.getRegisteredStudents();
                if(students == null){
                    System.out.println("No students are registered");
                    return;

                }
                for(Students student : students){
                    System.out.println(student.getFirstName() + " " + student.getLastName());

                }
                return;
            }
        }
        System.out.println("Course not found");
    }

    // Displays every course a student is registered in 

    @Override
    public void viewStudentCourses(ArrayList<Courses> courses, String firstName, String lastName){

        boolean found = false;
        for(Courses course : courses){

            ArrayList<Students> students = course.getRegisteredStudents();
            if(students != null){
                for(Students student : students){

                    if(student.getFirstName().equalsIgnoreCase(firstName) && student.getLastName().equalsIgnoreCase(lastName)){

                        course.displayCourse();
                        found = true;
                        break;

                    }
                }
            }
        }

        if(found == false){
            System.out.println("No courses found for student.");

        }
    }

   // Sorts all the courses by students capacity 

    @Override
    public void sortCourses(ArrayList<Courses> courses){

        for(int i = 0; i < courses.size() - 1; i++){
            int smallest = i;
            for(int j = i + 1; j < courses.size(); j++){
                if(courses.get(j).getCurrentStudents()< courses.get(smallest).getCurrentStudents()){
                    smallest = j;

                }
            }
            Courses temp = courses.get(i);
            courses.set(i, courses.get(smallest));
            courses.set(smallest, temp);

        }
        displayAllCourses(courses);
    }
}

