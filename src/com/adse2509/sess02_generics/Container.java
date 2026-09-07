package com.adse2509.sess02_generics;

/**
 * Generic base class {@code Container} that can hold different types.
 * 
 * @param <T> the type of the item/contents held by this class.
 * @author Nyanjui
 */
public class Container<T>
{
    /** The item store in the container */
    protected T item;
    
    /**
     * Constructs a new {@code Container} with the specified item.
     * 
     * @param varT the item to store in the container
     */
    public Container(T varT) { this.item = varT; }
    
    /** 
     * Returns the item stored in the container.
     * 
     * @return the stored item of type {@code T}
     */
    public T getVarT(){ return this.item; }
    
    /**
     * Displays information about the stored item.
     *
     * <p>The item's {@code toString()} method is used to produce its
     * string representation, which is printed to the standard output
     * in the format:</p>
     *
     * <pre>
     * Item: value
     * </pre>
     */
    public void displayItemInfo()
    {
        System.out.println("Item: " + item.toString());
    }
}
