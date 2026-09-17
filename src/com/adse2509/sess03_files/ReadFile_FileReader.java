package com.adse2509.sess03_files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

/**
 * Java program that demonstrates how to read file contents using methods
 * of the java.io.FileReader class.
 * 
 * 
 * @author Nyanjui
 */
public class ReadFile_FileReader
{

   // URL to hold the relative path to the file 'read_files.txt'
    protected URL url2File = this.getClass().
            getResource("../../files/read_files.txt");
    public static void main(String[] args)
    {
        // Use a try...with resources to automatically close all open handles
        // after use
        try(FileReader fReader = new FileReader(new File(
        new ReadFile_FileReader().url2File.getPath())))
        {
            int n;
            while((n = fReader.read()) != -1)
            {
                // Display the contents of the file
                System.out.print((char)n);
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
