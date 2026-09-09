package com.adse2509.sess02_generics;

import com.adse2509.classes.Food;

/**
 * A specialised container for storing objects that are subclasses of 
 * {@code Food}.
 *  
 * <p>This class extends {@code Container} by restricting the generic type
 * to {@code Food} objects. It also provides functionality to display food
 * specific information, such as the food's expiration date. </p>
 * 
 * @param <T> the type of {@code Food} stored in the container.
 * @author Nyanjui
 */
public class FoodContainer<T extends Food> extends Container<T>
{
	/**
	 * Constructs a new {@code FoodContainer} with the specified food item.
	 * 
	 * @param item the food item to be stored in the container.
	 */
	public FoodContainer(T item) 
	{
		super(item);
	}    
	
	/**
	 * Displays the expiration date of the stored food item.
	 * 
	 * <p>The output is displayed in the following format:</p>
	 * 
	 * <pre>
	 * Expiration date of FoodName: YYYY-MM-DD
	 * </pre>
	 */
	public void displayExpirationDate()
	{
		System.out.println("Expiration date of " + item.getName() + ": "
				+ item.getExpirationDate());
	}
}
