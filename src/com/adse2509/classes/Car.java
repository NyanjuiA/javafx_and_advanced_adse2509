package com.adse2509.classes;

import java.io.Serializable;

/**
 * Represents a car with a make, model and year of manufacture.
 * 
 * <p>The {@code Car} class implements {@link Serializable}, allowing
 * instances of the class to be converted into a byte stream for purposes
 * such as persistence or transmission between Java applications.</p>
 * 
 * <p>The class provides accessor and mutator methods for each property and 
 * overrides the {@code toString()} to provide a formatted textual representation
 * of the car's details. 
 * 
 * @author Nyanjui
 */
public class Car implements Serializable
{
    /**
     * The serial version unique identifier for this serialisable class.
     *
     * <p>This value is used by Java's serialisation mechanism to verify that
     * the sender and receiver of a serialised object have compatible class
     * definitions.</p>
     */
    private static final long serialVersionUID = 1L;
    private String make;
    private String model;
    private int year;
    
    // Constructor
    /**
     *  Constructs a {@code Car} with the specified make, model and year.
     * 
     * @param make
     * @param model
     * @param year 
     */
    public Car(String make, String model, int year)
    {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    /**
     * Returns the year in which the car was manufactured.
     *
     * @return the year of manufacture
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Sets the year in which the car was manufactured.
     *
     * @param year the year of manufacture
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * Returns a formatted textual representation of the car's details.
     *
     * @return a string containing the make, model and year of the car
     */
    @Override
    public String toString()
    {
        return String.format(
        """
        Car Details
        ----------------------------------------------------------------
        Make :      %s
        Model:      %s
        Year :      %d
        ----------------------------------------------------------------
        """,this.getMake(), this.getModel(),this.getYear()
        );
    }
    
    
    
}
