/*
Main java program that will accept user input 
Runs course registration system 
Loads courses and students, controls admin and student menu 

@author Brianna Graham

*/

import java.util.*;

public class Main{

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        //Loads saved courses
        ArrayList<Courses> courses = Serialization.loadCourses();

        //If there are no saved courses reads original csv file
        if(courses == null){
            courses = fileReader.readCourses("MyUniversityCourses.csv");
        }

        //Loads saved students
        ArrayList<Student> students = Serialization.loadStudents();

        //If there are no saved students creates empty ArrayList
        if(students == null){
            students = new ArrayList<Student>();
        }

        //Creates the one admin
        Admin admin = new Admin();

        boolean programRunning = true;

        while(programRunning){

            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("    COURSE REGISTRATION SYSTEM");
            System.out.println("-----------------------------------");
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.print("Enter number choice: ");

            int choice = input.nextInt();
            input.nextLine();

            switch(choice){

                case 1:
                    //Admin login
                    System.out.print("Enter username: ");
                    String adminUsername = input.nextLine();
                    System.out.print("Enter password: ");
                    String adminPassword = input.nextLine();
                    if(admin.getUsername().equalsIgnoreCase(adminUsername && admin.verifyPassword(adminPassword)){

                        System.out.println("Admin login successful");
                        adminMenu(admin, courses, students, input);
                    }
                    else{
                        System.out.println("Incorrect username or password");
                    }
                    break;

                case 2:

                    //Student login
                    System.out.print("Enter username: ");
                    String studentUsername = input.nextLine();
                    System.out.print("Enter password: ");
                    String studentPassword = input.nextLine();
                    Student currentStudent = null;
                    //Searches for matching student username
                    for(Student student : students){

                        if(student.getUsername().equalsIgnoreCase(studentUsername)){
                            currentStudent = student;
                            break;
                        }
                    }

                    //Checks if student exists and password is correct
                    if(currentStudent != null && currentStudent.verifyPassword(studentPassword)){

                        System.out.println("Student login successful");
                        studentMenu(currentStudent, courses, input);
                    }
                    else{
                        System.out.println("Incorrect username or password");
                    }
                    break;

                case 3:

                    //Saves newest information before program closes
                    Serialization.saveCourses(courses);
                    Serialization.saveStudents(students);
                    System.out.println("Program closed");
                    programRunning = false;

                    break;

                default:

                    System.out.println("Invalid choice");
            }
        }

        input.close();
    }
    //Controls admin menu
    public static void adminMenu(Admin admin, ArrayList<Courses> courses,
        ArrayList<Student> students, Scanner input){

        boolean loggedIn = true;

        while(loggedIn){

            admin.displayMenu();
            System.out.print("Enter choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch(choice){

                case 1:

                    //Create course
                    System.out.print("Enter course name: ");
                    String courseName = input.nextLine();
                    System.out.print("Enter course ID: ");
                    String courseId = input.nextLine();
                    System.out.print("Enter maximum students: ");
                    int maximumStudents = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter instructor: ");
                    String instructor = input.nextLine();
                    System.out.print("Enter section number: ");
                    int sectionNumber = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter course location: ");
                    String location = input.nextLine();

                    Courses newCourse = new Courses(courseName,courseId,maximumStudents,instructor,courseSectionNumber,location);
                    admin.createCourse(courses, newCourse);
                    break;

                case 2:

                    //Delete course
                    System.out.print("Enter course ID: ");
                    String deleteId = input.nextLine();
                    System.out.print("Enter section number: ");
                    int deleteSection = input.nextInt();
                    input.nextLine();
                    boolean deleted = admin.deleteCourse(courses,deleteId, deleteSection);

                    if(deleted){
                        System.out.println("Course deleted successfully");
                    }
                    else{
                        System.out.println("Course not found");
                    }

                    break;

                case 3:

                    //Edit course
                    System.out.print("Enter course ID: ");
                    String editId = input.nextLine();

                    System.out.print("Enter section number: ");
                    int editSection = input.nextInt();
                    input.nextLine();

                    admin.editCourse(courses, editId, editSection);

                    break;

                case 4:

                    //View course
                    System.out.print("Enter course ID: ");
                    String viewId = input.nextLine();

                    admin.viewCourse(courses, viewId);

                    break;

                case 5:

                    //Register student
                    System.out.print("Enter student's first name: ");
                    String firstName = input.nextLine();

                    System.out.print("Enter student's last name: ");
                    String lastName = input.nextLine();

                    System.out.print("Enter student's password: ");
                    String password = input.nextLine();

                    Student newStudent = new Student (password,firstName,lastName,students);
                    admin.registerStudent(students, newStudent);
                    System.out.println("Student username: "+ newStudent.getUsername());
                    break;

                case 6:

                    //Opens reports menu
                    reportMenu(admin, courses, input);
                    break;

                case 7:

                    loggedIn = false;
                    System.out.println("Admin logged out");
                    break;

                default:

                    System.out.println("Invalid choice");
            }
        }
    }


    //Controls admin reports menu
    public static void reportMenu(Admin admin, ArrayList<Courses> courses,
        Scanner input){

        boolean reportsOpen = true;

        while(reportsOpen){

            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("            REPORTS MENU");
            System.out.println("-----------------------------------");
            System.out.println("1. View All Courses");
            System.out.println("2. View Full Courses");
            System.out.println("3. Write Full Courses To File");
            System.out.println("4. View Students In Course");
            System.out.println("5. View Student Courses");
            System.out.println("6. Sort Courses");
            System.out.println("7. Back");
            System.out.println("-----------------------------------");
            System.out.print("Enter numerical choice: ");

            int choice = input.nextInt();
            input.nextLine();

            switch(choice){

                case 1:
                    admin.displayAllCourses(courses);
                    break;

                case 2:
                    admin.viewFullCourses(courses);
                    break;

                case 3:

                    System.out.print("Enter file name: ");
                    String fileName = input.nextLine();
                    admin.writeFullCourses(courses, fileName);
                    break;

                case 4:

                    System.out.print("Enter course ID: ");
                    String courseId = input.nextLine();

                    System.out.print("Enter section number: ");
                    int courseSectionNumber = input.nextInt();
                    input.nextLine();

                    admin.viewStudentsInCourse(courses,courseId,courseSectionNumbe);
                    break;

                case 5:

                    System.out.print("Enter student's first name: ");
                    String firstName = input.nextLine();
                    System.out.print("Enter student's last name: ");
                    String lastName = input.nextLine();
                    admin.viewStudentCourses(courses, firstName, lastName);
                    break;

                case 6:
                    admin.sortCourses(courses);
                    break;

                case 7:

                    reportsOpen = false;
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    //Controls student menu
    public static void studentMenu(Student student, ArrayList<Courses> courses, Scanner input){

        boolean loggedIn = true;
        while(loggedIn){

            student.displayMenu();

            System.out.print("Enter numerical choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch(choice){

                case 1:

                    //View all courses
                    student.displayAllCourses(courses);
                    break;

                case 2:

                    //View open courses
                    student.viewOpenCourses(courses);
                    break;

                case 3:

                    //Register for course
                    System.out.print("Enter course name: ");
                    String courseName = input.nextLine();
                    System.out.print("Enter section number: ");
                    int courseSectionNumber = input.nextInt();
                    input.nextLine();

                    boolean registered = student.registerForCourse(courses,courseName,courseSectionNumber);

                    if(registered){
                        System.out.println("Registered successfully");
                    }
                    else{
                        System.out.println("Could not register for course");
                    }
                    break;

                case 4:

                    //Withdraw from course
                    System.out.print("Enter course name: ");
                    String withdrawCourse = input.nextLine();
                    System.out.print("Enter section number: ");
                    int withdrawSection = input.nextInt();
                    input.nextLine();
                    boolean withdrawn = student.withdrawFromCourse(courses,withdrawCourse,withdrawSection);

                    if(withdrawn){
                        System.out.println("Withdrawn successfully");
                    }
                    else{
                        System.out.println("Could not withdraw from course");
                    }
                    break;

                case 5:

                    //View courses current student is registered in
                    student.viewMyCourses(courses);
                    break;

                case 6:

                    loggedIn = false;
                    System.out.println("Student logged out");
                    break;

                default:

                    System.out.println("Invalid choice");
            }
        }
    }
}