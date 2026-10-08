package com.adse2509.sess09_java_data_structures;

import java.util.Stack;

/**
 * This program demonstrates the usage of the Stack class in Java.
 * Stack is a class that implements a last-in, first-out (LIFO) data structure.
 * It allows adding elements to the top of the stack and removing elements
 * from the top, following the LIFO principle.
 *
 * Methods demonstrated include:
 * - push: To add an element to the top of the stack.
 * - pop: To remove and retrieve the top element of the stack.
 * - peek: To retrieve, without removing, the top element of the stack.
 * - search: To find the position of an element in the stack.
 * - empty: To check if the stack is empty.
 *
 * @author Nyanjui
 */
public class StackDemo
{
    /**
     * main method demonstrates basic operations with the {@code Stack} class,
     * including: pushing, popping, peeking, searching and checking whether the
     * stack is empty
     *
     * @param args
     */
    public static void main(String[] args)
    {
        // Create a Stack to hold Integer elements
        Stack<Integer> stack = new Stack<>();

        // Push elements into the stack
        System.out.println("Pushing elements onto the stack: ");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        // Display the current elements in the stack
        System.out.println("Stack after pushes(adding elements): " + stack);

        // Peek at the top element without removing it
        System.out.println("\nPeeking at the top element in the stack: " + stack.peek());

        // Pop (remove) elements from the stack
        System.out.println("\nPopping elements from the stack: ");
        while (!stack.isEmpty())
        {
            System.out.println("Popped: " +stack.pop());
            System.out.println("Stack after 'pop()': " +  stack);
        }

        // Check whether the stack is empty
        System.out.println("\nIs the stack empty? " + stack.isEmpty());

        // Push elements in the stack again to demonstrate search
        stack.push(20);
        stack.push(30);
        stack.push(40);
        System.out.println("Stack after re-pushing elements: " + stack);

        // Search for an element in the stack
        int searchPosition = stack.search(20);
        if(searchPosition != -1)
            System.out.println("Position of element '20' from the top is: " +  searchPosition);
        else
            System.out.println("Element '20' was not found in the stack.");

    }
}
