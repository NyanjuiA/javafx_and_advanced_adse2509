package com.adse2509.sess03_files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

/**
 * Java program that prompts the user for a string, writes it to a file, then
 * reads & displays the contents of the file using methods of the java.nio
 * classes.
 * 
 * 
 * @author Nyanjui
 */
public class NIOWrite2File
{

    // Create a path reference to the file to be written to ('nio_readwrite.txt')
    private static final Path readWriteFile = Paths.get("src/com/files/"
            + "nio_readwrite.txt");
    public static void main(String[] args)
    {
        // Use a try...with resources to autoclose resources
        try(Scanner sc = new Scanner(System.in).useDelimiter("\n"))
        {
            // Prompt the user for a string to be written to the 'nio_readwrite.txt' file
            System.out.println("Please enter a message/some text to be written "
                    + "to the file ->");
            String appendString = System.lineSeparator() + sc.nextLine();
            
            // Append the contents of the file
            Files.write(readWriteFile, appendString.getBytes(), 
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
                    );
            System.out.println("Contents successfully written to file.");
            
            // Read and display the contents from the 'nio_readwrite.txt' file
            List<String> fileContents = Files.readAllLines(readWriteFile);
            fileContents.forEach((fileContent) ->
            {
                System.out.println(fileContent);
            });
        }catch(IOException ioe)
        {
            System.err.println(
                    """
                    The file was not found.
                    Please confirm it exists and that you have sufficient permission
                    to access it and try again. 
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
