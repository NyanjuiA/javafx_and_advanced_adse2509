package com.adse2509.sess10_new_and_advanced_java_features;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Demonstrates the use of the {@link Predicate} functional interface
 * and predicate negation to filter even and odd numbers, as well as
 * leap years and non-leap years.
 *
 * <p>The program creates a list of integers and uses predicates to
 * determine whether each number is even or odd. It then uses the
 * {@link Predicate#not(Predicate)} method to create a predicate that
 * represents the logical negation of another predicate.</p>
 *
 * <p>The program also demonstrates the use of the
 * {@link Predicate#negate()} method to obtain the opposite result
 * of a predicate when classifying years as leap years or non-leap
 * years.</p>
 *
 * <p>Java streams and the {@link java.util.stream.Stream#filter(Predicate)}
 * method are used to filter the lists, while
 * {@link Collectors#toList()} collects the results into lists.</p>
 *
 * <p><strong>Note:</strong> The leap-year predicate in this example
 * checks only whether a year is divisible by four. The complete
 * Gregorian leap-year rule also requires century years to be
 * divisible by 400.</p>
 *
 * @author Nyanjui
 * @version 1.0
 * @since 1.0
 */
public class PredicateNotOddEvenLeap
{
    /**
     * Executes the program to demonstrate predicate negation and
     * filtering with Java streams.
     *
     * <p>The method performs the following operations:</p>
     * <ol>
     *   <li>Creates a list of integers from 1 to 10.</li>
     *   <li>Defines a predicate to identify even numbers.</li>
     *   <li>Uses {@link Predicate#not(Predicate)} to identify odd
     *       numbers by negating the even-number predicate.</li>
     *   <li>Filters the original list to produce separate lists
     *       of even and odd numbers.</li>
     *   <li>Creates a list of years from 2020 to 2029.</li>
     *   <li>Defines a predicate to identify years divisible by four
     *       and uses {@link Predicate#negate()} to identify years
     *       that are not divisible by four.</li>
     *   <li>Prints the resulting lists to the standard output.</li>
     * </ol>
     *
     * @param args command-line arguments supplied when the program
     *             is executed;
     */
    public static void main(String[] args)
    {
        // List of the first ten integers
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Create a predicate to identify even numbers
        Predicate<Integer> findEven = n -> n % 2 == 0;

        // Create a predicate that negates the even-number predicate.
        Predicate<Integer> findOdd = Predicate.not(findEven); // same as: findOdd= n -> n % 2 == 1;

        // Filter even numbers
        List<Integer> evenNums = numList.stream().
                filter(findEven).
                collect(Collectors.toList());

        // Filter odd numbers
        List<Integer> oddNums = numList.stream().
                filter(findOdd).
                collect(Collectors.toList());
        // Display the original list of numbers, the even and odd numbers list
        System.out.println("Original list of numbers: " + numList);
        System.out.println("List of even numbers: " + evenNums);
        System.out.println("List of odd numbers: " + oddNums);

        // Create a list of years
        List<Integer> yearList = Arrays.asList(
                2020, 2021, 2022, 2023, 2024,
                2025, 2026, 2027, 2028, 2029
        );

        // Create a predicate to identify leap years
        Predicate<Integer> isLeap = year ->
                year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);

        // Create a predicate for non-leap years
        Predicate<Integer> isNotLeap = isLeap.negate();

        // Filter the list of years to obtain leap years
        List<Integer> leapYears = yearList.stream().
                filter(isLeap).
                collect(Collectors.toList());

        // Filter the list of years to obtain non leap years
        List<Integer> nonLeapYears = yearList.stream().
                filter(isNotLeap).
                collect(Collectors.toList());
        // Display the original list of years, leap, and non leap years
        System.out.println("Original list of years:" + yearList +
                "\nLeap years: " + leapYears +
                "\nNon-Leap years: " + nonLeapYears );
    }
}
