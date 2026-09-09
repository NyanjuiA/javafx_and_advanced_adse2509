package com.adse2509.sess02_generics;

import java.util.Arrays;

/**
 * Demonstrates the use of the {@link BoundGeneric} class with
 * different numeric types.
 * 
 * @author Cui
 */
public class BoundGenericDemo
{
    public static void main(String[] args)
    {
        // Demo. with an array of bytes
        Byte[] byteNums = {10, 20, 30, 40, 50};
        BoundGeneric<Byte> byteGeneric = new BoundGeneric<>(byteNums);
        
        // Display the numbers in the array and their sum
        System.out.println("Byte array:"
                + "\n" + Arrays.toString(byteGeneric.getNums()));
        System.out.println("Sum: " + byteGeneric.getSum());
        // System.out.println("Mean: " + byteGeneric.getMean());
        
        // Demo. with an array of floats
        Float[] floatNums = {1.5f, 2.5f, 3.5f, 4.5f, 5.0f};
        BoundGeneric<Float> floatGeneric = new BoundGeneric<>(floatNums);
        
        // Display the numbers in the array and their sum
        System.out.println("Float array:"
                + "\n" + Arrays.toString(floatGeneric.getNums()));
        System.out.println("Sum: " + floatGeneric.getSum());
        // System.out.println("Mean: " + floatGeneric.getMean());

        
        // Demo. with an array of longs
        Long[] longNums = {100L, 200L, 300L, 400L, 500L};
        BoundGeneric<Long> longGeneric = new BoundGeneric<>(longNums);
        
        // Display the numbers in the array and their sum
        System.out.println("Long array:"
                + "\n" + Arrays.toString(longGeneric.getNums()));
        System.out.println("Sum: " + longGeneric.getSum());
        // System.out.println("Mean: " + longGeneric.getMean());
    }
    
}
