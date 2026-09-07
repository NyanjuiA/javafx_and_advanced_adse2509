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

    public Food()
    {
        this.name = "";
        this.expirationDate = LocalDate.now();
    }

    public Food(String name, LocalDate expirationDate)
    {
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public LocalDate getExpirationDate()
    {
        return expirationDate;
    }

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
