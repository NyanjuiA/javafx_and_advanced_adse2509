package com.adse2509.sess04_threads;

/**
 * Java program that demonstrates creating threads using classes that
 * implement the {@link Runnable} interface and the {@link Thread} class.
 * 
 * @author Nyanjui
 */
public class ThreadDemo
{

    public static void main(String[] args)
    {
        // Demonstrate thread execution by creating and starting multiple threads
        for(int n = 1; n < 5; n++)
        {
            Thread t = new Thread(new ImplementRunnable(n));
            ExtendThread thread = new ExtendThread(n);
            t.start();
            thread.start();
        }
    }
    
}
