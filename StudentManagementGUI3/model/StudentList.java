package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Student objects.
 * @author Allan Henriksen
 * @version 1.0
 */
public class StudentList implements Serializable
{
	private ArrayList<Student> students;

	/**
	 * No-argument constructor initializing the StudentList.
	 */
	public StudentList()
	{
		students = new ArrayList<Student>();
	}

	/**
	 * Adds a Student to the list.
	 * @param student the student to add to the list
	 */
	public void add(Student student)
	{
		students.add(student);
	}

	/**
	 * Replaces the Student object at index with student.
	 * @param student the student to replace with
	 * @param index the position in the list that will be replaced
	 */
	public void set(Student student, int index)
	{
		students.set(index, student);
	}

	/**
	 * Gets a Student object from position index from the list.
	 * @param index the position in the list of the Student object
	 * @return the Student object at position index if one exists, else null
	 */
	public Student get(int index)
	{
		if(index<students.size())
		{
			return students.get(index);
		}
		else
		{
			return null;
		}
	}

	/**
	 * Gets a Student object with the given first name and last name from the list.
	 * @param firstName the first name of the Student object
	 * @param lastName the last name of the Student object
	 * @return the Student object with the given first name and last name if one exists, else null
	 */
	public Student get(String firstName, String lastName)
	{
		for(int i = 0; i<students.size(); i++)
		{
			Student temp = students.get(i);
			
			if(temp.getFirstName().equals(firstName) && temp.getLastName().equals(lastName))
			{
				return temp;
			}
		}
		
		return null;
	}

	/**
	 * Gets the index of a Student object with the given first name and last name.
	 * @param firstName the first name of the Student object
	 * @param lastName the last name of the Student object
	 * @return the index of the Student object with the given first name and last name if one exists, else -1
	 */
	public int getIndex(String firstName, String lastName)
	{
		for(int i = 0; i<students.size(); i++)
		{
			Student temp = students.get(i);
			
			if(temp.getFirstName().equals(firstName) && temp.getLastName().equals(lastName))
			{
				return i;
			}
		}	
		return -1;
	}

	/**
	 * Gets how many Student objects are in the list.
	 * @return the number of Student objects in the list
	 */
	public int size()
	{
		return students.size();
	}

	/**
	 * Gets a String representation of the StudentList.
	 * @return a String containing information about all Student objects in the list - each Student object followed by a new line character
	 */
	public String toString()
	{
		String returnStr = "";
	
		for(int i = 0; i<students.size(); i++)
		{
			Student temp = students.get(i);
		
			returnStr += temp +"\n";
		}	
		return returnStr;
	}
}
