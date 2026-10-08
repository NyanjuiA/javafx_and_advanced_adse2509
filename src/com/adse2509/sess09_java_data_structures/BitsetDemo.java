package com.adse2509.sess09_java_data_structures;

import java.util.BitSet;

/**
 * This program demonstrates the usage of the BitSet class in Java.
 * BitSet is a class that allows the creation of a sequence of bits,
 * which can grow as needed. It is particularly efficient for manipulating
 * large sets of bits for tasks such as checking flags or storing binary states.
 *
 * Methods demonstrated include setting, clearing, flipping, and performing
 * logical operations (AND, OR) on bits.
 *
 * @author a.nyanjui
 */
public class BitsetDemo
{
    public static void main(String[] args)
    {
        // Create two Bitset instances/objects of size 8
        BitSet bitset1 = new BitSet(8);
        BitSet bitset2 = new BitSet(8);

        // Set some of the bits in bitSet1
        bitset1.set(0);
        bitset1.set(2);
        bitset1.set(4);
        bitset1.set(6);

        // Set some of the bits in bitSet2
        bitset2.set(1);
        bitset2.set(2);
        bitset2.set(3);
        bitset2.set(4);

        // Display the initial bitsets
        System.out.println("Initial bitset1: " + bitset1);
        System.out.println("-".repeat(45));
        System.out.println("Initial bitset2: " + bitset2);

        // Perform a logical AND operation between bitset1 and bitset2
        BitSet andResult = (BitSet) bitset1.clone(); // Clone bitset1 to preserve the original
        andResult.and(bitset2); // AND operation
        System.out.println("\nbitset1 AND bitset2: " + andResult);

        // Perform a logical or operation between bitset1 and bitset2
        BitSet orResult = (BitSet) bitset1.clone(); // Clone bitset1 to preserve the original
        orResult.or(bitset2); // OR operation
        System.out.println("\nbitset1 or bitset2: " + orResult);

        // Perform a logical xor operation between bitset1 and bitset2
        BitSet xorResult = (BitSet) bitset1.clone(); // Clone bitset1 to preserve the original
        orResult.xor(bitset2); // XOR operation
        System.out.println("\nbitset1 xor bitset2: " + xorResult);

        // Flip (invert) the bits in bitSet1
        BitSet flippedBitset1 = (BitSet) bitset1.clone();
        flippedBitset1.flip(0,8); // Flip all the bits from index 0 to 8
        System.out.println("\nflipped bitset1: " + flippedBitset1);

        // Clear a specific bit in bitset2
        BitSet clearedBitset2 = (BitSet) bitset2.clone();
        clearedBitset2.clear(2); // Clear the bit at index 2
        System.out.println("\nbitset2 after clearing bit 2: " + clearedBitset2);

        // Check if a specific bit is set in bitset1
        System.out.println("\nIs bit at index  4 set in bitset1? " + bitset1.get(4));
        System.out.println("Is bit at index  5 set in bitset1? " + bitset1.get(5));


    }
}
