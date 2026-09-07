package com.adse2509.sess02_generics;

import com.adse2509.classes.Drink;

/**
 * A specialised container for string objects that are subclasses
 * of {@code Drink}.
 * 
 * <p>This class extends  {@code Container} by restricting the generic type
 * to {@code Drink} objects. It also provides functionality to display 
 * drink specific information, such as the drink's name and volume.</p>
 * 
 * @param <T> The type of {@code Drink} stored in the container.
 * @author Cui
 */
public class DrinkContainer<T extends Drink> extends Container<T>
{

    /**
     * Constructs a new {@code DrinkContainer} with the specified drink.
     * 
     * @param varT the drink to be stored in the container
     */
    public DrinkContainer(T varT)
    {
        super(varT);
    }
    
    /**
     * Displays the name and volume of the stored drink.
     * 
     * <p>The output is displayed in the following format:</p>
     * 
     * <pre>DrinkName comes in a volume of X litres.</pre>
     */
    public void displayVolume()
    {
        System.out.println(item.getName() + " comes in a volume of "
                + item.getVolume() + " litres.");
    }
    
}
