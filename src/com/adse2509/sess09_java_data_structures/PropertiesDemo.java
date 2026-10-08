package com.adse2509.sess09_java_data_structures;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Java program to demonstrate the use of Properties class in Java. The
 * properties class is used to maintain a set of key-value paris, where both the
 * key and value are strings. The program demonstrates
 *  1. Checking if the properties file exists
 *  2. If the properties file doesn't exist, creating it with default  properties
 *  3. Loading properties from the file
 *  4. Accessing the individual property values
 *  5. Modifying property values
 *  6. Saving modified properties to the file
 *
 * @author a.nyanjui
 */
public class PropertiesDemo
{
    // Set/assign the name of the properties file in "files" folder
    private static final String PROPERTIES_FILE = "../../files/config.properties";

    // main method
    public static void main(String[] args)
    {
        // Create a Properties object
        Properties properties = new Properties();

        // Check whether the properties file exists
        File propertiesFile = new File(PROPERTIES_FILE);
        if (!propertiesFile.exists())
        {
            // When the properties file doesn't exist, create it and set default properties
            System.out.println("Properties file does not exist. Creating a new one " +
                    "with default values...");
            setDefaultProperties(properties);
        }else
        {
            // When the file exists, load the properties from the file
            loadProperties(properties);
        }

        // Display the properties
        System.out.println("Properties loaded from file: ");
        displayProperties(properties);

        // Modify some properties
        System.out.println("\nModifying some properties: ");
        properties.setProperty("username", "Nyanjui");
        properties.setProperty("timeout", "30");

        // Display the modified properties
        System.out.println("\nModified properties: ");
        displayProperties(properties);

        // Save the modified properties back to the file
        saveProperties(properties);

        // Reload and display properties to verify the above changes
        System.out.println("\nReloading properties from the file to verify "
                + "changes...");
        loadProperties(properties);
        displayProperties(properties);
    }

    private static void setDefaultProperties(Properties properties)
    {
        properties.setProperty("username", "admin");
        properties.setProperty("password", "passw0rD12#");
        properties.setProperty("timeout", "60");

        // Save the default properties to the file
        saveProperties(properties);
    }

    /**
     * Load properties from a file into the Properties object. If the file
     * does exist, it will create a new file with the default properties.
     *
     * @param properties the properties object to load the properties into
     */
    private static void loadProperties(Properties properties)
    {
        try(InputStream inputStream = new FileInputStream(PROPERTIES_FILE))
        {
            // Load properties from the file
            properties.load(inputStream);
        }catch (IOException ioe)
        {
            System.err.println("Error loading properties file \n" + ioe.getLocalizedMessage());
        }
    }

    /**
     * Displays all the properties in the Properties object.
     *
     * @param properties the Properties object to display
     */
    private static void displayProperties(Properties properties)
    {
        // Display all properties in the object using a for...each loop
        properties.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    /**
     * Saves the properties back to the file. When the file exists, it will overwrite the existing
     * file. It creates the storage directory when it doesn't exist.
     *
     * @param properties
     */
    private static void saveProperties(Properties properties)
    {
        try
        {
            // Create the files directory if it doesn't exist
            File file = new File(PROPERTIES_FILE);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists())
            {
                parentDir.mkdirs();
            }

            try (FileOutputStream outputStream = new FileOutputStream(PROPERTIES_FILE))
            {
                // Save the properties to the file
                properties.store(outputStream,"Updated properties file.");
                System.out.println("\nProperties have been savedd to the file.");
            }
        }catch (IOException ioe)
        {
            System.err.println("Error saving to the properties file.");
            ioe.printStackTrace();
        }
    }
}
