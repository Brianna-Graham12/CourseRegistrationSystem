/* 
Serializes and deserializes the course and students because those are the objects that will change and what you want saved 
@author Brianna Graham

*/


/*
Serialization manager saves and loads the courses and students in the program.
Uses serialization so the program can remember information after it closes.

@author Brianna Graham
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Serialization{

    //Saves the new courses
    public static void saveCourses(ArrayList<Courses> courses){

        try{

            // Writes data to the file 
            FileOutputStream fos = new FileOutputStream("Courses.ser");

            //Wraps so java can pass objects through 
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //Writes the courses into the object file 
            oos.writeObject(courses);

            //Close both streams
            oos.close();
            fos.close();

            System.out.println("Courses saved successfully");

        }
        catch(IOException ioe){

            ioe.printStackTrace();

        }
    }

    //Loads the course ArrayList
    public static ArrayList<Courses> loadCourses(){

        ArrayList<Courses> courses = null;
        try{

            //FileInputStream receives bytes from a file
            FileInputStream fis = new FileInputStream("Courses.ser");

            //ObjectInputStream reconstructs the data into an object
            ObjectInputStream ois = new ObjectInputStream(fis);

            //Casts the object back into an ArrayList of Courses
            courses = (ArrayList<Courses>)ois.readObject();

            //Close both streams
            ois.close();
            fis.close();

        }
        // Catches when file cant be read or written 
        catch(IOException ioe){
            return null;

        }
        // Cant find the courses object 
        catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
            return null;

        }
        return courses;
    }


    //Saves the student ArrayList
    public static void saveStudents(ArrayList<Student> students){
        try{

            FileOutputStream fos = new FileOutputStream("Students.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(students);
            oos.close();
            fos.close();
            System.out.println("Students saved successfully");

        }
        catch(IOException ioe){
            ioe.printStackTrace();

        }
    }

    //Loads the student ArrayList
    public static ArrayList<Student> loadStudents(){

        ArrayList<Student> students = null;
        try{

            FileInputStream fis = new FileInputStream("Students.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            students = (ArrayList<Student>)ois.readObject();
            ois.close();
            fis.close();

        }
        catch(IOException ioe){
            return null;

        }
        catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
            return null;

        }

        return students;
    }
}