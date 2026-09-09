package com.adse2509.util;

/**
 *  Provides a utility method for displaying visual separators in console output.
 *
 *  <p>This class is intended for use where a consistent visual separation between sections
 *  of console output is required.
 *  </p>
 *
 * @author Nyanjui
 */
public final class Spacer
{
    private Spacer () {/* prevent instantiation */}
    /**
     * Prints a horizontal separator consisting of 80 hyphens (dashes), followed by
     * a blank line, to the standard output stream.
     */
    public static void separator()
    {
        System.out.println("-".repeat(80));
        System.out.println();
    }
}
