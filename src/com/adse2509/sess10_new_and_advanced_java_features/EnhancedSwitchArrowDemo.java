package com.adse2509.sess10_new_and_advanced_java_features;

import java.util.Scanner;

/**
 * This program demonstrates the enhanced features of the switch-case construct
 * in Java. The enhanced features include:
 * 1. Multiple values per case.
 * 2. Using `yield` to return a value from the switch expression.
 * 3. Using the switch statement as an expression to assign values.
 * 4. Thin arrow syntax (`->`) for a cleaner code structure.
 * 5. Changed scope within each case.
 *
 * @author a.nyanjui
 */
public class EnhancedSwitchArrowDemo
{
    public static void main(String[] args)
    {
        // Scanner object to get input from the user
        Scanner sc = new Scanner(System.in);

        // Prompt the user for a day of the week in numeric form ( 1 -> Sunday, 7 -> Saturday)
        System.out.println("Please enter a number for the day of the week "
                + "1 -> Sunday through to 7 -> Saturday");
        int day = sc.nextInt();

        // Demonstrate multiple values per case, yield, expression, thin arrow and scope
        String dayType = getDayType(day);

        // Display the result
        System.out.println("The type of day for day " + day + " is: " + dayType);
    }

    /**
     * Method to get the type of day based on the day of the week. It demonstrates
     * various enhancements to the switch...case construct/statement.
     *
     * @param day integer value representing the day of the week (1 -> Sunday...
     *            7 -> Saturday
     *
     * @return String representing the type of day("Weekday" or "Weekend")
     */
    private static String getDayType(int day)
    {
        // Use switch as an expression to determine the type of day
        String dayType = switch(day)
        {
            // Support multiple values per case for weekdays (2-6) and
            // weekend (1 - 7)
            case 2,3,4,5,6 ->
            {
                //Scope example: declare a variable within the case block
                String description = "It's a weekday";
                System.out.println(description);
                // Use the yield keyword to return a value from the case block
                yield "Weekday";
            }
            case 1,7 ->
            {
                // Scope example: Declare a variable for weekend cases
                String description = "It's the weekend!";
                System.out.println(description);
                yield "Weekend";
            }
            // Default case to handle all other unexpected cases/values
            default ->
            {
                System.out.println("Sorry, you gave us an invalid day.\nPlease "
                        + "enter a number from 1 - 7");
                yield "Unknown";
            }
        };
        return dayType;
    }
}
