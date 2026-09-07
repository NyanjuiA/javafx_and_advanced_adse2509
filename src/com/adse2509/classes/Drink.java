package com.adse2509.classes;

/**
 * Represents a drink with a name and a volume.
 *
 * <p>This class stores basic information about a drink, including its
 * name and volume in litres. It provides constructors, accessor and
 * mutator methods, and a formatted string representation of the drink.</p>
 *
 * @author Nyanjui
 */
public class Drink
{
    /** The name of the drink. */
    protected String name;
    
    /** The volume of the drink in litres. */
    protected float volume;

    /**
     * Constructs a {@code Drink} with default values.
     * 
     * <p>The name is initialised to an empty string and the volume is 
     * initialised to {@code 0.0f} litres.</p>
     */
    public Drink()
    {
        this.name = "";
        this.volume = 0.0f;
    }
    
    /**
     * Constructs a {@code Drink} with the specified name and volume.
     * 
     * @param name the drink's name
     * @param volume the drink's volume in litres.
     */
    public Drink(String name, float volume)
    {
        this.name = name;
        this.volume = volume;
    }
    
    /**
     * Returns the name of the drink.
     * 
     * @return the drink's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the drink.
     * 
     * @param name the new name of the drink.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the volume of the drink.
     * 
     * @return the drink's volume in litres.
     */
    public float getVolume()
    {
        return volume;
    }

    /**
     * Sets the volume of the drink.
     * 
     * @param volume the new volume of the drink in litres.
     */
    public void setVolume(float volume)
    {
        this.volume = volume;
    }

    /**
     * Returns a formatted string containing the drink's details.
     *
     * <p>The returned string includes the drink's name and volume,
     * formatted for display.</p>
     *
     * @return a formatted string representation of this {@code Drink}
     */
    @Override
    public String toString()
    {
        return String.format(
        """
        Drink Details
        -----------------------------------------------------
        Name: %s
        Volume in Litres: %.2f
        -----------------------------------------------------
        """,this.getName(), this.getVolume()
        );
    }    
    
    
}
