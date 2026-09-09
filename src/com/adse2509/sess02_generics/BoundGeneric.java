package com.adse2509.sess02_generics;

/**
 * Represents a generic container for an array of numeric values.
 * 
 * <p>
 * The type parameter must extend {@link Number}, allowing the array to
 * contain numeric types such as {@link Integer}, {@link Double},
 * {@link Long}, and other subclasses of the {@link Number} class.
 * </p>
 * 
 * @param <T> the numeric type stored in the array
 * @author Cui
 */
public class BoundGeneric<T extends Number>
{
    private T[] nums;

    public BoundGeneric()
    {
    }
    
    public BoundGeneric(T[] nums)
    {
        this.nums = nums;
    }

    public T[] getNums()
    {
        return nums;
    }

    public void setNums(T[] nums)
    {
        this.nums = nums;
    }
    
    public double getSum() throws ArithmeticException
    {
        double sum = 0.0d;
        
        for (T num : nums)
            sum += num.doubleValue();
        
        return sum;
    }
    
    // TODO: Write a getAverage() or getMean() method that returns
    // the arithmetic mean of the numbers in the array.
    
}
