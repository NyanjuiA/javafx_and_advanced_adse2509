package com.adse2509.sess10_new_and_advanced_java_features;


import java.util.Scanner;

/**
 * Demonstrates the use of braces with switch cases in Java.
 * <p>
 * Each case is enclosed in its own block, which creates a separate
 * scope for variables declared within that block. This allows the
 * same variable name to be used in different case blocks without
 * causing a naming conflict.</p>
 *
 * @author a.nyanjui
 */
public class EnhancedSwitchBracesDemo
{
    /**
     * The entry point of the application.
     * <p>
     * Prompts the user to enter a Product ID and uses a switch statement
     * to display the corresponding product label value. Each switch...case
     * uses braces to create its own variable scope.
     * </p>
     * @param args command-line arguments supplied when the application starts.
     */
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter Product ID to check the Product label: \n");
        int prodID = sc.nextInt();
        switch (prodID) {
            case 101: {
                /*
                * The num variable exists just in this {} block.
                * It cannot be accessed outside the case 101 block.
                */
                int num = 200;
                System.out.println("The value of num is "+num);
                break;
            }
            case 102: {
                /*
                 * This is valid because the braces crate a separate scope.
                 * A variable with the same name can therefore be declared
                 * independently in this case block.
                 */
                int num = 300;
                System.out.println("The value of num is "+num);
                break;
            }
            default:
            {
                /*
                * Executes when the entered product ID does not match any of the defined
                * case labels.
                */
                System.out.println("Oops! No matches");
            }
        }
    }
}
