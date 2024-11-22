package model;

import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A model manager providing a single access point to the model
 * @author Allan Henriksen
 * @version 1.0
 */
public class StudentModelManager
{
  private String fileName;

  /**
   * 1-argument constructor setting the file name.
   * @param fileName the name and path of the file where students will be saved and retrieved
   */
   public StudentModelManager(String fileName)
   {
      this.fileName = fileName;
   }

  /**
   * Uses the MyFileHandler class to retrieve a StudentList object with all Students.
   * @return a StudentList object with all stored students
   */
   public StudentList getAllStudents()
   {
      StudentList allStudents = new StudentList();

      try
      {
         allStudents = (StudentList) MyFileHandler.readFromBinaryFile(fileName);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch (IOException e)
      {
         System.out.println("IO Error reading file");
      }
      catch (ClassNotFoundException e)
      {
         System.out.println("Class Not Found");
      }
      return allStudents;
   }

  /**
   * Use the MyFileHandler class to retrieve all students from a given country.
   * @param fromCountry the country to retrieve students from
   * @return a StudentList object with students from the given country
   */
   public StudentList getStudentsFrom(String fromCountry)
   {
      StudentList studentsFromCountry = new StudentList();
      StudentList allStudents = getAllStudents();

      for(int i = 0; i < allStudents.size(); i++)
      {
        if(allStudents.get(i).getCountry().equals(fromCountry))
        {
          studentsFromCountry.add(allStudents.get(i));
        }
      }

      return studentsFromCountry;
   }

  /**
   * Use the MyFileHandler class to save a list of students.
   * @param students the list of students that will be saved
   */
   public void saveStudents(StudentList students)
   {
      try
      {
         MyFileHandler.writeToBinaryFile(fileName, students);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch (IOException e)
      {
         System.out.println("IO Error writing to file");
      }
   }

  /**
   * Uses the MyFileHandler class to change the country of a student with
   * the given first name and last name.
   * @param firstName the first name of the student
   * @param lastName the last name of the student
   * @param country the student's new country
   */
   public void changeCountry(String firstName, String lastName, String country)
   {
      StudentList allStudents = getAllStudents();

      for (int i = 0; i < allStudents.size(); i++)
      {
         Student student = allStudents.get(i);

         if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName))
         {
            student.setCountry(country);
         }
      }

      saveStudents(allStudents);
   }

   // Add more methods to the model manager depending on what you wish to do
}
