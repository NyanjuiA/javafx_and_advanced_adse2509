package com.adse2509.classes;

import java.time.LocalDate;

/**
 * The abstract class {@code Person} represents a person with common
 * attributes or properties such as names, birthdate, gender(binary) and
 * nationality. It provides a basic structure for its subclasses and includes
 * getters and setters for these attributes. 
 * The class also defines an abstract method {@code getPersonRole} that must 
 * be implemented by any concrete class.
 * 
 * <p>This class can be extended by other classes that represent more specific 
 * types of persons or people (e.g. Student, Employee) by providing additional
 * attributes or behaviours.
 * </p>
 */
public abstract class Person 
{
   // Fields
	/**
	 * The person's full name(s)
	 */
	protected String names; 
	
	/**
	 * The person's date of birth represented as a {@code LocalDate}
	 */
	protected LocalDate birthDate;
	
	/**
	 * The person's gender, represented as a character (e.g 'M' for male, and
	 * 'F' for female.)
	 */
	protected char gender;
	
	/**
	 * The person's nationality
	 */
	protected String nationality;

	/**
	 * 
	 */
	public Person() 
	{
		
	}

	/**
	 * Parameterised constructor for the {@code Person} class.
	 * @param names the person's full name
	 * @param birthDate the person's date of birth as a {@code LocalDate}
	 * @param gender the person's binary gender represented as a character
	 * @param nationality the person's nationality
	 */
	public Person(String names, LocalDate birthDate, char gender, String nationality) 
	{
		this.names = names;
		this.birthDate = birthDate;
		this.gender = gender;
		this.nationality = nationality;
	}

	/**
	 * @return the names
	 */
	public String getNames() {
		return names;
	}

	/**
	 * @param names the names to set
	 */
	public void setNames(String names) {
		this.names = names;
	}

	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the gender
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/** 
	 * Returns the role of the person (e.g. "Student", "Customer", "Employee", "Patient").
	 * Each subclass must provide its own role. 
	 * 
	 * @return the role of the person as a String.
	 */
	public abstract String getPersonRole();

	/**
	 * Returns a formatted string representation of the person's details.
	 * 
	 * @return the person's details
	 */
	@Override
	public String toString() {
		return String.format(
				"""
				Role		: %s
				Name(s)		: %s
				Birthdate	: %s
				Gender		: %s
				Nationality	: %s
				""",
				getPersonRole(),
				getNames(),
				this.birthDate,
				(getGender() == 'M' || getGender() == 'm') ? "Male" : "Female",
						this.nationality
				);
	}
	
	
	
	
}
