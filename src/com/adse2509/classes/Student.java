package com.adse2509.classes;

import java.time.LocalDate;

/**
 * Represents a Student object.
 * 
 * @author Nyanjui
 */
public class Student extends Person 
{
	// TODO: write javadoc for the fields below
	protected String regNo;
	protected String course;
	
	
	// Constructor(s)
	/**
	 * 
	 */
	public Student() 
	{
		super();
	}


	/**
	 * @param regNo
	 * @param course
	 * @param names
	 * @param birthDate
	 * @param gender
	 * @param nationality
	 */
	public Student(String regNo, String course, String names, LocalDate birthDate, 
			char gender, String nationality) 
	{
		super(names, birthDate, gender, nationality);
		this.regNo = regNo;
		this.course = course;
	}

	// Getters & Setters
	/**
	 * @return the regNo
	 */
	public String getRegNo() {
		return regNo;
	}


	/**
	 * @param regNo the regNo to set
	 */
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}


	/**
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}


	/**
	 * @param course the course to set
	 */
	public void setCourse(String course) {
		this.course = course;
	}
	
	
	@Override
	public String getPersonRole() { return "Student"; }
	
	/**
	 * Returns a formatted string representation of the student's details.
	 * 
	 * @return the student's details
	 */
	@Override
	public String toString() // Overridden using refinement
	{
		return String.format(
				"""
				%s
				Registration No.		: %s
				Course	: %s
				""",
				super.toString(),
				this.regNo,
				this.getCourse()
				);
	}
	
	
	
	
}


