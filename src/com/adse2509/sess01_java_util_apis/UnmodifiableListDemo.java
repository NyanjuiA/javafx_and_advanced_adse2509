package com.adse2509.sess01_java_util_apis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Java program to demonstrate an Unmodifiable implementation  of the List interface.
 *
 * @author Nyanjui
 */
public class UnmodifiableListDemo
{
    public static void main(String[] args)
    {
        // Create a mutable list of fruits
        List<String> mutableList = new ArrayList<>();
        mutableList.add("Avocado");
        mutableList.add("Durian");
        mutableList.add("Cherry");
        mutableList.add("Apple");

        // Create an unmodifiable view of the fruitList
        List<String> unmodifiableList = Collections.unmodifiableList(mutableList);

        // Try reading the elements(fruits) from the unmodifiable list
        System.out.println("The first fruit/element in the unmodifiable list is: " +
                unmodifiableList.get(0));

        // Try to add a new item (fruit : banana) to the unmodifiable list
        try
        {
            unmodifiableList.add("Banana"); // Will throw an exception
        }
        catch (UnsupportedOperationException uoe)
        {
            System.err.println("Modification attempt failed due to:\n" +
                    uoe.getLocalizedMessage());
        }
    }
}