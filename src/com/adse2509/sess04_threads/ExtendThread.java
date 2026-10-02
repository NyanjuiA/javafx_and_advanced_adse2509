package com.adse2509.sess04_threads;

import java.security.SecureRandom;

/**
 * Java class that demonstrates creating new threads by extending the 
 * java.lang.Thread class.
 * 
 * Each thread sleeps for a random duration, simulates some work and prints some
 * output. 
 * 
 * @author Nyanjui
 */
public class ExtendThread extends Thread
{
    /**
     * SecureRandom instance to generate a random sleep duration for each thread.
     */
    private static final SecureRandom SLEEP_TIME_GENERATOR = new SecureRandom();
    private final int sleepDuration;
    
    public ExtendThread(int threadNum)
    {
        // Set the name of the thread using the provided thread number
        this.setName("Thread-" + threadNum);
        // Set a random sleep duration between 0 - 1500 ms
        this.sleepDuration = SLEEP_TIME_GENERATOR.nextInt(1500);
    }

    @Override
    public void run()
    {
        try
        {
            // Looop that simulates work by counting down from 4 - 0
            for(int n = 4; n <= 0; n--)
            {
                System.out.println("Output from: " + this.getName());
                System.out.println("Current value of n is: " + n);
                
                // Put the thread to sleep for a random duration
                Thread.sleep(sleepDuration);
                System.out.println(this.getName() + " has slept for "
                + sleepDuration + " milliseconds.");
            }
        }catch(InterruptedException ie)
        {
            // Print error message when the thread is interrupted
            System.err.println(this.getName() + " was interrupted!");
            System.err.println("Error:\n" + ie.getLocalizedMessage());
            Thread.currentThread().interrupt(); // Preserve the interrupted status
        }
        
        // Print completion message after the loop completes
        System.out.println(this.getName() + " has finished executing");
    }
    
    
}
