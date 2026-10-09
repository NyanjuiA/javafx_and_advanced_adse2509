package com.adse2509.sess10_new_and_advanced_java_features;

/**
 * This program demonstrates to use of records and textblocks in Java.
 *
 * - Records: Provide a compact syntax for data classes, automatically generating
 * essential methods like 'toString()', 'equals()', and 'hashCode()'.
 *
 * - Text Blocks: Simplifies multi-line strings, making them more readable, and
 * reduces the need for escape sequences.
 */
public class RecordsAndTextBlocksDemo
{
    /**
     * The Person record represents a simple data class with three fields: -
     * name: The name of the person - age: The age of the person - email: The
     * email address of the person
     *
     * The record automatically generates: - A constructor with all fields. -
     * Accessor methods for each field (e.g., name(), age(), email()). -
     * `equals()`, `hashCode()`, and `toString()` methods.
     */
    public record Person(String name, int age, String email)
    {
        // You can add extra fields & methods if needed
        public String greeting()
        {
            return "Hello, my name is " + name;
        }
    }

    public static void main(String[] args)
    {
        // Create an instance of the Person record
        Person person = new Person("Abigail Winfred",25,"a.winfred@example.co.ke");

        // Display the above person's details/information using a text block
        String personInfo =
                """
            Person Information:
            ----------------------------
            Name: %s 
            Age : %d
            Email: %s
            ----------------------------
                """.formatted(person.name(),person.age(),person.email());
        System.out.println(personInfo);

        // Example of using a text block to create a JSON-like string for the person
        String personJson =
                """
                    {
                        "name": "%s",
                        "age" : %d,
                        "email" : "%s"
                    }
                """.formatted(person.name(),person.age(),person.email());

        // Display the JSON-like representation of the person's details
        System.out.println("JSON representation of Person:");
        System.out.println(personJson);
    }
}
