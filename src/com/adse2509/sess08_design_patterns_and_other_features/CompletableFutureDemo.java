package com.adse2509.sess08_design_patterns_and_other_features;

import java.util.concurrent.CompletableFuture;

/**
 * Java program to demonstrate the CompletableFuture class and some of its
 * methods.
 *
 * @author Nyanjui
 */
public class CompletableFutureDemo
{
    public static void main(String[] args)
    {
        // 1. supplyAsync(): Runs a task asynchronously and returns a CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->
        {
            System.out.println("Executing asynchronous task...");
            try
            {
                Thread.sleep(1000); // Simulate processing delay
            }catch(InterruptedException ie)
            {
                throw new IllegalStateException(ie);
            }
            return "Hello World!";
        });

        // 2. thenApply(): Process the result for the supplyAsync() when it's ready
        CompletableFuture<String> greetingFuture = future.thenApply(result ->
        {
            System.out.println("Applying transformation to result...");
            return result +  " - Greetings from CompletableFuture!";
        });

        // 3. join(): Waits for the computation to complete and retrieves its result
        System.out.println("Result using join(): " + greetingFuture.join());

        // 4. thenAccept(): Consumes the result without returning any additional result
        greetingFuture.thenAccept(result ->
        {
            System.out.println("Consuming result with thenAccept(): " + result);
        });

        // 5. whenComplete(): Handles both the result and any exception tha might occur
        greetingFuture.whenComplete((result,exception) ->
        {
           if(exception == null)
           {
               System.out.println("whenComplete(): Task completed successfully " +
                       "with result: " + result);
           }
           else
           {
               System.out.println("whenComplete(): Task failed with exception: " +
                       exception.getLocalizedMessage());
           }
        });
        //6. getNow(): Returns the result if completed, else return a default value
        String immediateResult = greetingFuture.getNow("Default Value");
        System.out.println("Result using getNow(): " + immediateResult);
        // Ensure the main() method (main thread in this case) waits for the asynchronous tasks to complete
        try
        {
            Thread.sleep(2000);// Just to ensure asynchronous taks complete before program exits
        }catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
}
