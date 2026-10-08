package com.adse2509.sess09_java_data_structures;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Java program that demonstrates the usage of the Enumeration interface in
 * Java. An enumumeration is an interface that allows us to traverse through
 * legacy collections like Vector, Stack, Hashtable and Properties.
 *
 * Although it's considered a legacy interface, Enumeration is still useful for working
 * with these legacy collections.
 *
 * @author Nyanjui
 */
public class EnumerationDemo
{
    /**
     * This method initialises a Vector with a list of fruits and uses an enumeration to iterate
     * through the Vector.
     */
    public static void main(String[] args)
    {
        // Create a Vector and add some elements(fruits) to it
        Vector<String> fruits = new Vector<>();
        fruits.add("Pineapple");
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add("Grapes");
        fruits.add("Kiwi Fruit");
        fruits.add("Avocado");
        fruits.add("Cherry");
        fruits.add("Blackberry");
        fruits.add("Orange");

        // Display the initial contents of the vector
        System.out.println("Fruits in the Vector: " + fruits);

        // Get an Enumeration object to iterate over the Vector elements
        Enumeration<String> fruitEnumeration = fruits.elements();
        System.out.println("\nIterating over the Vector elements using an Enumeration:");

        // Use the Enumeration to traverse through each element in the vector
        while(fruitEnumeration.hasMoreElements())
        {
            String fruit = fruitEnumeration.nextElement(); // Retrieve the next element (fruit)
            System.out.println(" Fruit: " + fruit);
        }
    }
}
