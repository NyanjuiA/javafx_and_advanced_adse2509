package com.adse2509.sess03_files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

/**
 * Java program that demonstrates how to read file contents using methods
 * of the java.io.BufferedReader class using a try...with resources block.
 * 
 * 
 * @author Nyanjui
 */
public class BufferedReader_TryWithResources
{
    // URL to hold the relative path to the file 'read_files.txt'
    protected URL url2File = this.getClass().
            getResource("../../files/read_files.txt");
    public static void main(String[] args)
    {
        // Use a try...with resources
        try(BufferedReader br = new BufferedReader(new FileReader(new File(
            new BufferedReader_TryWithResources().url2File.getPath()))))
        {
            // Display the contents of the file
            System.out.println("The contents of the 'read_files.txt' file are:\n");
            
            String st;
            int n = 1;
            while((st = br.readLine()) != null)
            {
                System.out.printf("Line %d\n %s\n",n, st);
                n++; // Increment the line counter
            }
        }
        catch(FileNotFoundException fne)
        {
            System.err.println(
                    """
                    Sorry the file was not found. 
                    Please check for typos and ensure you have permission to 
                    access the file, then try again.
                    """
            );
        }
        catch(Exception e)
        {
            System.err.println(
                    """
                    Sorry .
                    """ + e.getLocalizedMessage()
            );
        }
        
    }
    
}
