package com.adse2509.sess05_multithreading_and_concurrency;

class MyThread extends Thread
{

    /**
     * Constructor to set/assign the name of the thread.
     * 
     * @param name the thread's name.
     */
    public MyThread(String name)
    {
        super(name);
    }

    @Override
    public void run()
    {
        System.out.println(getName() + " is running");
        
        // Demonstrate the sleep method
        try
        {
            System.out.println(getName() + " is sleeping for 2 seconds");
            Thread.sleep(2000);
        }
        catch(InterruptedException ie)
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

/**
 * Java class to demonstrate various methods of the {@link Thread} class.
 * 
 * @author Nyanjui
 */
public class ThreadMethodsDemo
{
    public static void main(String[] args)
    {
        // 1. Demonstrate the setName() & getName() 
        MyThread thread1 = new MyThread("Thread-1"); // Set the name using the constructor
        System.out.println("Name of 'thread1' is: " + thread1.getName()); // Obtain/get the threads's name
        thread1.setName("Renamed Thread-1"); // Modify/change the name using the mutator/setter
        System.out.println("New name of 'thread1' is: " + thread1.getName());
        
        // 2. Demonstrate setPriority() and getPriority()
        thread1.setPriority(Thread.MAX_PRIORITY); // Set thread1's priority to the highest i.e 10
        System.out.println("Priority of 'thread1' is: " + thread1.getPriority());
        
        // 3. Demonstrate isAlive()
        System.out.println("'thread1' is alive? : " + thread1.isAlive());
        
        // 4. Demonstrate start()
        System.out.println("Starting 'thread1'...");
        thread1.start();
        
        // 5. Demonstrate isDaemon() to find out of 'thread1' is a daemon thread
        System.out.println("'thread1' is a daemon thread? : " + thread1.isDaemon());
        System.out.println("'thread1' is alive? : " + thread1.isAlive());
        
        // 6. Demonstrate join()
        try
        {
            System.out.println("Waiting for 'thread1' to complete...");
            thread1.join(); // main thread waits for thread1 to complete
            System.out.println("Thread1 has completed!");
        }catch(InterruptedException ie)
        {
            System.err.println("Main thread was interrupted while waiting.");
        }
        
        // 7. Demonstrate isAlive() again
        System.out.println("'thread1' is alive after completion? : " + thread1.isAlive());
        
        // 8. Demonstrate Thread.currentThread()
        System.out.println("Current thread is: " + Thread.currentThread().getName());
        
        // 9. Demonstrate activeCount()
        System.out.println("Active thread count: " + Thread.activeCount());
    }    
    
}
