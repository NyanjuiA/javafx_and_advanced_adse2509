package com.adse2509.sess02_generics;

/**
 * Represents a generic container for an array of numeric values.
 *
 * <p>
 * The type parameter must extend {@link Number}, allowing the array to contain
 * numeric types such as {@link Integer}, {@link Double},
 * {@link Long}, and other subclasses of the {@link Number} class.
 * </p>
 *
 * @param <T> the numeric type stored in the array
 * @author Cui
 */
public class BoundGeneric<T extends Number>
{

    /**
     * The array of numeric values.
     */
    protected T[] nums;

    /**
     * Constructs an empty {@code BoundGeneric} object.
     */
    public BoundGeneric()
    {
    }

    /**
     * Constructs a {@code BoundGeneric} object containing the specified array
     * of numbers.
     *
     * @param nums the array of numeric values
     */
    public BoundGeneric(T[] nums)
    {
        this.nums = nums;
    }

    /**
     * Returns the array of numeric values.
     *
     * @return the array of numbers
     */
    public T[] getNums()
    {
        return nums;
    }

    /**
     * Sets the array of numeric values.
     *
     * @param nums the new array of numbers
     */
    public void setNums(T[] nums)
    {
        this.nums = nums;
    }

    /**
     * Calculates and returns the sum of all numbers in the array.
     * <p>
     * Each number is converted to a {@code double} before being added to the
     * total.
     * </p>
     *
     * @return the sum of all numbers as a {@code double}
     * @throws ArithmeticException if an arithmetic error occurs
     */
    public double getSum() throws ArithmeticException
    {
        double sum = 0.0d;

        for (T num : nums)
        {
            sum += num.doubleValue();
        }

        return sum;
    }

    // TODO: Write a getAverage() or getMean() method that returns
    // the arithmetic mean of the numbers in the array.
}
