package com.adse2509.classes;

import java.time.LocalDate;

/**
 * Represents a fruit as a type of food.
 * 
 * <p>
 * A fruit has a name and expiration date inherited from the {@link Food} class,
 * as well as a taste and weight measured in grams.
 * </p>
 */
public class Fruit extends Food 
{
	/** The taste of the fruit. */
	protected String taste; 
	
	/** The weight of the fruit in grams. */
	protected float weight;
	
	/**
	 * Constructs a new fruit with the specified name, expiration date, taste, 
	 * and weight in grams.
	 * 
	 * @param name the name of the fruit.
	 * @param expirationDate
	 * @param taste
	 * @param weight the fruit's weight in grams.
	 */
	public Fruit(String name, LocalDate expirationDate,String taste, float weight) 
	{
		super(name, expirationDate);
		this.taste = taste;
		this.weight = weight;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	/**
	 * Returns a formatted string containing the details of this fruit,
	 * including its inherited food details, taste, and weight.
	 *
	 * @return a formatted string containing the fruit details
	 */
	@Override
	public String toString() {
		return String.format(
				"""
				Fruit Details
				-----------------------------------------------------
				%s
				Taste: %s
				Weight in grams: %.2f
				-----------------------------------------------------
				""",super.toString(), this.getTaste(),this.getWeight()
				);
	}
	
	
	
	
}
