/*
    Student program that will show all the methods a student can use, serializes into a seperate file 
    @author Brianna Graham

*/

// For random 
import java.util.*; 

public class Student extends User implements StudentInteface{

    // Constructor 
    public Student(String password, String firstName, String lastName, ArrayList<Student> students){
        super(generateUsername(firstName, lastName, students), password, firstName, lastName);

    }

    //Generates username using first initial, last initial and 3 random numbers
    private static String generateUsername(String firstName, String lastName, ArrayList<Student> students){

        Random random = new Random();
        String username;
        boolean exists;

        // Activates at least once to check if username exists so doesent just keep going 
        do{

            int numbers = random.nextInt(900) + 100;
            username = ("" + firstName.charAt(0) + lastName.charAt(0) + numbers).toLowerCase();
            exists = false;

            for(Student student : students){

                if(student.getUsername().equalsIgnoreCase(username)){
                    exists = true;
                    break;

                }

            }

        }while(exists);
            
        return username;

    }

    //Returns type of user
    //@return userType returns student
    @Override
    public userType getUserType(){

        return userType.STUDENT;
    }

    //Displays student menu
    @Override
    public void displayMenu(){

        System.out.println("-----------------------------------");
        System.out.println("           STUDENT MENU");
        System.out.println("-----------------------------------");
        System.out.println("1. View All Courses");
        System.out.println("2. View Open Courses");
        System.out.println("3. Register For Course");
        System.out.println("4. Withdraw From Course");
        System.out.println("5. View My Courses");
        System.out.println("6. Logout");
        System.out.println("-----------------------------------");
        
    }

    //Displays all courses that are not full
    @Override
    public void viewOpenCourses(ArrayList<Courses> courses){

        boolean found = false;
        for(Courses course : courses){
            if(course.isFull() == false){

                course.displayCourse();
                found = true;

            }

        }

        if(found == false){

            System.out.println("There are no open courses");

        }
    }

    //Registers student into a course
    @Override
    public boolean registerForCourse(ArrayList<Courses> courses, String courseName, int courseSectionNumber){

        for(Courses course : courses){
            if(course.getCourseName().equalsIgnoreCase(courseName) && course.getCourseSectionNumber() == courseSectionNumber){
                return course.addStudent(this);

            }
        }
        return false;

    }

    //Withdraws student from a course
    @Override
    public boolean withdrawFromCourse(ArrayList<Courses> courses, String courseName, int courseSectionNumber){

        for(Courses course : courses){

            if(course.getCourseName().equalsIgnoreCase(courseName) && course.getCourseSectionNumber() == courseSectionNumber){

                return course.removeStudent(this);

            }
        }
        return false;

    }

    //Displays all courses current student is registered in
    @Override
    public void viewMyCourses(ArrayList<Courses> courses){

        boolean found = false;
        for(Courses course : courses){

            if(course.hasStudent(this)){
                course.displayCourse();
                found = true;

            }
        }

        if(found == false){

            System.out.println("You are not registered in any courses");

        }
    }
}

