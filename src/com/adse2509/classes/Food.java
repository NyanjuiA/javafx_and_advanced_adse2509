package com.adse2509.classes;

import java.time.LocalDate;

/**
 * Represents food with a name and a expiration date.
 *
 * <p>This class stores basic information about a food, including its
 * name and expiration date. It provides constructors, accessor and
 * mutator methods, and a formatted string representation of the food.</p>
 *
 * @author Nyanjui
 */
public class Food
{
    /** The name of the food. */
    protected String name;
    
    /** The food's expiry date. */
    protected LocalDate expirationDate;

 /**
     * Constructs a {@code Food} object with default values.
     *
     * <p>The name is initialised to an empty string and the expiration
     * date is initialised to the current date.</p>
     */
    public Food()
    {
        this.name = "";
        this.expirationDate = LocalDate.now();
    }

    /**
     * Constructs a {@code Food} object with the specified name and
     * expiration date.
     *
     * @param name the name of the food
     * @param expirationDate the expiry date of the food
     */
    public Food(String name, LocalDate expirationDate)
    {
        this.name = name;
        this.expirationDate = expirationDate;
    }

    /**
     * Returns the name of the food.
     *
     * @return the food's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the food.
     *
     * @param name the new name of the food
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the expiry date of the food.
     *
     * @return the food's expiration date
     */
    public LocalDate getExpirationDate()
    {
        return expirationDate;
    }

    /**
     * Sets the expiry date of the food.
     *
     * @param expirationDate the new expiry date of the food
     */
    public void setExpirationDate(LocalDate expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    /**
     * Returns a formatted string containing the food's details.
     *
     * <p>The returned string includes the food's name and expiry date,
     * formatted for display.</p>
     *
     * @return a formatted string representation of this {@code Food}
     */
    @Override
    public String toString()
    {
        return String.format(
        """
                -----------------------------------------------------
                Name: %s
                Expiration Date: %s
                -----------------------------------------------------
        """,this.getName(), this.getExpirationDate()
        );
    }
    
    
    
}
