package com.adse2509.sess10_new_and_advanced_java_features;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This method demonstrates the usage of Predicate.not method in Java.
 * The Predicate.no method provides a clean way to negate predicates, especially
 * useful when filtering collections
 *
 * @author Nyanjui
 */
public class PredicateNot
{
    /**
     * Filter out empty strings from the list using Predicate.not
     *
     * @param strings the list of string to filter
     *
     * @return a new list containing only non-empty strings
     */
    static List<String> filterNonEmptyStrings(List<String> strings)
    {
        // Define a predicate to check if a string is empty
        Predicate<String> isEmpty = String::isEmpty;

        // Use Predicate.not to negate the isEmpty predicate, keeping
        // only non-empty strings
        return strings.stream()
                .filter(Predicate.not(isEmpty)) // Equivalent to : s -> !s.isEmpty()
                .collect(Collectors.toList());

    }

    // main function begins program execution
    public static void main(String[] args)
    {
        // Sample list of strings, with some empty strings
        List<String> words = List.of("melon","","avocado", "grapes", "","banana","apple");

        // Display the original list of strings (fruits)
        System.out.println("Original list of strings(fruits)" +
                "\n" + words);

        // Filter out empty strings using Predicate.not
        List<String> nonEmptyWords = filterNonEmptyStrings(words);

        // Display the o list of strings (fruits) after filtering out empty strings
        System.out.println("List of strings(fruits) after filtering out empty strings" +
                "\n" + nonEmptyWords);
    }
}
