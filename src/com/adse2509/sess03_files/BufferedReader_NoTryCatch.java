package com.adse2509.sess03_files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * Java program that demonstrates how to read file contents using methods
 * of the java.io.BufferedReader class without a try...catch block.
 * 
 * 
 * @author Nyanjui
 */
public class BufferedReader_NoTryCatch
{
    // URL to hold the relative path to the file 'read_files.txt'
    protected URL url2File = this.getClass().
            getResource("../../files/read_files.txt");
    
    public static void main(String[] args) throws IOException
    {
        // Declare and instantiate a File object
        File readFile = new File(new BufferedReader_NoTryCatch().
                url2File.getPath());
        
        // Declare and instantiate a java.io.BufferedReader object
        BufferedReader br = new BufferedReader(new FileReader(readFile));
        
        // Display the contents of the file
        System.out.println("The contents of the: " + readFile.getName()
        + " are:");
        
        String st;
        int n = 1;
        while((st = br.readLine()) != null)
        {
            System.out.printf("Line %d \n %s \n",n,st);
            n++; // increment the line counter
        }
        
        br.close();
    }
    
}
