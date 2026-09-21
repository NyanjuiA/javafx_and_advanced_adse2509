package com.adse2509.sess05_multithreading_and_concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class ArraySumTask extends RecursiveTask<Long>
{
    private static final int THRESHOLD = 1000; // Threshold for splitting tasks
    private final int[] array;
    private final int start;
    private final int end;
    
    // Constructor to initialise a task with an array and bounds (start & end)

    public ArraySumTask(int[] array, int start, int end)
    {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute()
    {
        int length = end - start;
        if(length <= THRESHOLD)
        {
            // Base case: when the task is small enough ( <= 1000) 
            // compute it directly
            long sum = 0L;
            for (int n = start; n < end; n++)
            {
                sum += array[n];
            }
            return sum;
        }
        else
        {
            // When > 1000, split the task recursively
            int middle = start + length / 2;
            ArraySumTask leftTask = new ArraySumTask(array, start, middle);
            ArraySumTask rightTask = new ArraySumTask(array, middle, end);
            
            // Fork the subtasks for parallel execution
            leftTask.fork();
            rightTask.fork();
            
            // Wait and combine the results of the subtasks
            long leftResult = leftTask.join();
            long rightResult = rightTask.join();
            
            // Return the combined result
            return leftResult + rightResult;
        }
    }
    
    
}

/**
 * Java program to demonstrate splitting a task among a number of
 * threads when it's computationally expensive.
 * 
 * @author Nyanjui
 */
public class ForkJoinDemo
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Declare and initialiase an array of numbers
        int[] array = new int[10000];
        for(int n = 0; n < array.length; n++)
        {
            array[n] = n + 1; // Fill the array with numbers (1 - 10000)
        }
        
        // Create a ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        
        // Create the main task for summing the array
        ArraySumTask mainTask = new ArraySumTask(array, 0, array.length);
        
        // Call/invoke that task and get the result
        long result = forkJoinPool.invoke(mainTask);
        
        // Display the result
        System.out.println("The sum of the numbers from 1 to " + array.length
                + " is: " + result);
    }
    
}
