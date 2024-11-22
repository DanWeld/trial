package model;

import java.io.Serializable;

/**
 * A class representing a student with a first name, last name and country.
 * @author Allan Henriksen
 * @version 1.0
 */
public class Student implements Serializable
{  
	private String firstName;
	private String lastName;
	private String country;

	/**
	 * Three-argument constructor.
	 * @param firstName the student's first name
	 * @param lastName the student's last name
	 * @param country the student's country
	 */
	public Student(String firstName, String lastName, String country)
	{  
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
	}

	/**
	 * Gets the student's first name.
	 * @return the student's first name
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Sets the student's first name.
	 * @param firstName what the student's first name will be set to
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Gets the student's last name.
	 * @return the student's last name
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Sets the student's last name.
	 * @param lastName what the student's last name will be set to
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Gets the student's country.
	 * @return the student's country
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * Sets the student's country.
	 * @param country what the student's country will be set to
	 */
	public void setCountry(String country)
	{
		this.country = country;
	}

	/**
	 * Returns a string representation of the student.
	 * @return a string representation of the student in the format: "firstName lastName (country)"
	 */
	public String toString()
	{
		return firstName +" " + lastName +" (" + country +")"; 
	}

	/**
	 * Compares first name, last name and country of two students.
	 * @param obj the object to compare with
	 * @return true if the given object is equal to this student
	 */
	public boolean equals(Object obj)
	{
		if(obj == null || getClass() != obj.getClass())
		{
			return false;
		}

		Student other = (Student) obj;

		return firstName.equals(other.firstName) && lastName.equals(other.lastName) && country.equals(other.country);
	}
}
