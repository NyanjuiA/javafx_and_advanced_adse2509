package com.adse2509.sess05_multithreading_and_concurrency;


/**
 * A thread-safe counter that can be incremented and queried.
 * 
 * <p>
 * The {@link #increment()} method is synchronised to ensure that 
 * only one thread can modify the counter at a time.
 * </p>
 * 
 * @author Nyanjui
 */
class Counter
{
    /**
     * Stores the current value of the counter
     */
    private int count = 0;
    
    /**
     * Increments the counter by one.
     * 
     * <p>
     * This method is synchronised to that multiple threads cannot increment 
     * the counter simultaneously.
     * </p>
     */
    public synchronized void increment() {count ++;}
    
    /**
     * Returns the current value of the counter
     * 
     * @return the current counter value
     */
    public int getCount() { return count;}
}

/**
 * Java program to demonstrate thread locking and synchronisation.
 * 
 * @author Nyanjui
 */
public class SynchronisationDemo
{
    public static void main(String[] args)
    {
        // Declare and instantiate a Counter object/instance
        Counter counter = new Counter();
        
        // Local counters for each thread
        final int[] thread1Count = {0};
        final int[] thread2Count = {0};
        
        // Create the first thread that will access the increment() 
        // method concurrently
        Thread t1 = new Thread(() -> 
        {
                for(int n = 0; n < 1000; n++)
                {
                    counter.increment();
                    if (n % 50 == 0)
                    {
                        System.out.println("Current value of counter in thread1 is: " + 
                            counter.getCount() + "\nCurrent value of local"
                                    + " counter is: " + n);
                    }
                    thread1Count[0]++;
                }
                System.out.println("Thread 1 local count: " + thread1Count[0]);
        });
        Thread t2 = new Thread(() -> 
        {
                for(int n = 0; n < 1000; n++)
                {
                    counter.increment();
                    if (n % 50 == 0)
                    {
                        System.out.println("Current value of counter in thread2 is: " + 
                            counter.getCount() + "\nCurrent value of local"
                                    + " counter is: " + n);
                    }
                    thread2Count[0]++;
                }
                System.out.println("Thread 2 local count: " + thread2Count[0]);
        });
        
        // Start both threads
        t1.start();
        t2.start();
        
        // Wait for both threads to finish
        try
        {
            t1.join();
            t2.join();
        }catch(InterruptedException ie)
        {
            System.err.println(ie.getLocalizedMessage() + " occured!");
        }
        
        // Print the final count (expected to be 2000 if synchronisation 
        // worked correctly)
        System.out.println("Final counter value after synchronisation: "
           + counter.getCount());
    }
    
}
