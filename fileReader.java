/* Program should read through the university courses file and add it the program before it even 
asks the user a question. 

@author Brianna Graham 
*/

import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileReader{

// @param String fileName, takes in the name of the course file 
//@return the whole list of courses and their information 
//Takes any file and checks if it even runs 
    public static ArrayList<Courses> readCourses(String fileName){
        ArrayList<Courses> courses = new ArrayList<Courses>();
        //Deals with error handling 
        try{

            File courseFile = new File(fileName);
            Scanner reader = new Scanner(courseFile);
        //Skips first line  
            reader.nextLine();

            while(reader.hasNextLine()){

                String line = reader.nextLine();
                Scanner courseReader = new Scanner(line);
            // comma seperated file 
                courseReader.useDelimiter(",");

                String courseName = courseReader.next();
                String courseId = courseReader.next();
                int maximumStudents = courseReader.nextInt();

            //Skips the current students and skips registerd because nobody has registered yet

                courseReader.next();
                courseReader.next();

                String courseInstructor = courseReader.next();
                int courseSectionNumber = courseReader.nextInt();
                String courseLocation = courseReader.next();

            // Creates a singular course object 

                Courses course = new Courses(courseName,courseId, maximumStudents,
                    courseInstructor, courseSectionNumber, courseLocation);

                courses.add(course);
                courseReader.close();
            
            }

            reader.close();

        }   
        catch(FileNotFoundException e){
            System.out.println("University File Not Found!");
        }
        return courses; 
    }
    
}