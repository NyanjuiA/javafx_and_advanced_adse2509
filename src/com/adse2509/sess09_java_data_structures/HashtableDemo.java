package com.adse2509.sess09_java_data_structures;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This program demonstrates the usage of the Hashtable class in Java. Hashtable
 * is a collection class that stores key-value pairs, where both keys and values
 * are objects. The class is synchronised, making it thread-safe for concurrent
 * access, but it does not allow null keys or null values.
 *
 * Key methods demonstrated include:
 * - put: To add key-value pairs to the Hashtable.
 * - get: To retrieve the value associated with a specific key.
 * - remove: To remove a key-value pair based on the key.
 * - containsKey and containsValue: To check if a specific key or value exists.
 * - elements and keys: To iterate over values and keys in the Hashtable.
 *
 * @author a.nyanjui
 */
public class HashtableDemo
{
    /**
     * main method demonstrates basic operations of the Java.util.Hashtable
     * class including adding, retrieving, removing elements, and checking
     * for the existence of specific keys or values.
     *
     * @param args String array of command line arguments.
     */
    public static void main(String[] args)
    {
        // Create a HashTable instance to store integer keys and string values
        Hashtable<Integer,String> hashtable = new Hashtable<>();

        // Adding key-value pairs to the Hashtable
        System.out.println("Adding key-value pairs to the Hashtable...");
        hashtable.put(1,"Apple");
        hashtable.put(2,"Banana");
        hashtable.put(3,"Avocado");
        hashtable.put(4,"Mango");

        // Display the entire Hashtble
        System.out.println("Initial Hashtable: " + hashtable);

        // Retrieve a value associated with a specific key
        int keyToRetrieve = 3;
        System.out.println("\nValue associated with key " + keyToRetrieve + " : "
                + hashtable.get(keyToRetrieve));

        // Remove a key-value pair based on the key
        int keyToRemove = 2;
        System.out.println("\nRemoving key " + keyToRemove + " and its value " +
                "from the hashtable: ");
        hashtable.remove(keyToRemove) ;
        System.out.println("Hashtable after removal: " + hashtable);

        // Checking whether a specific key exists in the Hashtable
        int keyToCheck = 1;
        System.out.println("\nDoes key " + keyToCheck + " exist? "
                + hashtable.containsKey(keyToCheck));

        // Checking whether a specific value exists in the Hashtable
        String valueToCheck = "Mango";
        System.out.println("Does value '" + valueToCheck + "' exists? "
                + hashtable.containsValue(valueToCheck));

        // Iterating over the keys in the Hashtable
        System.out.println("\nIterating over the keys in the Hashtable:");
        Enumeration<Integer>keys = hashtable.keys();
        while(keys.hasMoreElements())
        {
            Integer key = keys.nextElement();
            System.out.println("Key: " + key);
        }

        // Iterating over the values in the Hashtable
        System.out.println("\nIterating over the values in the Hashtable:");
        Enumeration<String> values = hashtable.elements();
        while(values.hasMoreElements())
        {
            String value = values.nextElement();
            System.out.println("Value: " + value);
        }
    }
}
