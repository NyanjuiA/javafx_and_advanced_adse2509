package com.adse2509.sess08_design_patterns_and_other_features;

import java.util.concurrent.locks.StampedLock;

/**
 * Java program that uses the Java's StampedLock class to manage a shared
 * resource, in this case, a balance amount. The StampedLock allows both read
 * and write operations with different levels of locking : - Write lock: used
 * for exclusive modifications. - Read lock: used for shared access without
 * modification. - Optimistic read lock: used for non-blocking reads, allowing
 * readers to proceed unless a write occurs.
 *
 * This class demonstrates locking mechanisms through deposit, withdraw and
 * balance checking methods.
 *
 * @author a.nyanjui
 */
public class StampedLockDemo
{
    /**
     * The {@code StampedLock} used to control concurrent access to
     * the account balance.
     *
     * <p>Write locks are used when modifying the balance, while read
     * locks are used when reading the balance. An optimistic read is
     * also demonstrated in {@link #checkBalanceOptimisticRead()}.</p>
     */
    private final StampedLock stampedLock = new StampedLock();
    private double balance;

    /**
     * Creates a new {@code StampedLockDemo} with the specified
     * initial account balance.
     *
     * @param balance the initial account balance
     */
    public StampedLockDemo(double balance)
    {
        this.balance = balance;
    }

    /**
     * Deposits the specified amount into the account.
     *
     * <p>A write lock is acquired before the balance is modified.
     * The lock is released in a {@code finally} block to ensure that
     * it is released even if an exception occurs while updating the
     * balance.</p>
     *
     * @param amount the amount of money to deposit
     */
    public void deposit(double amount)
    {
        System.out.println("\nAbout to deposit Kes. : " + amount);
        long stamp = stampedLock.writeLock(); // Acquire an exclusive write lock
        System.out.println("Applied write lock");

        try
        {
            balance += amount; // Safely update the balance
            System.out.println("Available balance: " + balance);
        }finally
        {
            stampedLock.unlock(stamp); // Release the exclusive write lock
            System.out.println("Unlock write lock");
        }
    }

    /**
     * Withdraws the specified amount from the account.
     *
     * <p>A write lock is acquired before the balance is modified.
     * The lock is released in a {@code finally} block to ensure that
     * it is always released after the operation.</p>
     *
     * @param amount the amount of money to withdraw
     */
    public void withdraw(double amount)
    {
        System.out.println("\nAbout to withdraw Kes. : " + amount);
        long stamp = stampedLock.writeLock(); // Acquire a write lock
        System.out.println("Applied write lock");

        try
        {
            balance -= amount; // Safely update the balance
            System.out.println("Available balance: " + balance);
        }finally
        {
            stampedLock.unlockWrite(stamp);// Release the write lock
            System.out.println("Unlocked write lock");
        }
    }

    public double checkBalance()
    {
        System.out.println("\nAbout to check balance");

        // Acquire a full read lock.
        long stamp = stampedLock.readLock();

        System.out.println("Applied a read lock.");
        try
        {
            System.out.println("Available balance: " + this.balance);
            return this.balance;
        }
        finally
        {
            // Release the read lock
            stampedLock.unlockRead(stamp);

            System.out.println("Unlocked read lock");
        }
    }

    /**
     * Checks and returns the current account balance using an
     * optimistic read.
     *
     * <p>An optimistic read does not block a writer. The method first
     * obtains an optimistic read stamp and reads the balance. The stamp
     * is then validated to determine whether a write operation occurred
     * while the value was being read.</p>
     *
     * <p>If the optimistic read is invalid because a write occurred
     * during the read, the method falls back to acquiring a full read
     * lock and reads the balance again safely.</p>
     *
     * @return the current account balance
     */
    public double checkBalanceOptimisticRead()
    {
        System.out.println(
                "\nAbout to check the balance with optimistic read lock");

        // Attempt a non-blocking optimistic read.
        long stamp = stampedLock.tryOptimisticRead();

        System.out.println(
                "Applied non-blocking optimistic read lock");

        double currentBalance = this.balance;

        /*
         * Validate whether a write occurred during the optimistic read.
         */
        if (!stampedLock.validate(stamp))
        {
            System.out.println(
                    "Stamp has changed. Applying full read lock");

            // Fall back to a full read lock when the optimistic read fails.
            stamp = stampedLock.readLock();

            try
            {
                currentBalance = this.balance;
            }
            finally
            {
                // Release the read lock.
                stampedLock.unlockRead(stamp);

                System.out.println("Unlock read lock");
            }
        }

        System.out.println(
                "\nAvailable balance is Kes.: " + currentBalance);

        return currentBalance;
    }

    /**
     * Entry point of the program.
     *
     * <p>The method creates a {@code StampedLockDemo} object with an
     * initial balance of KES 4,000.00. It then performs a withdrawal,
     * a deposit, a normal balance check using a read lock, and a
     * balance check using an optimistic read.</p>
     *
     * @param args command-line arguments supplied when the program
     *             is executed
     */
    public static void main(String[] args)
    {
        // Initialise StampedLockDemo with an initial balance of KES 4,000.00.
        StampedLockDemo stampedLockDemo =
                new StampedLockDemo(4000.00);

        // Perform a series of transactions.
        stampedLockDemo.withdraw(1000.0);
        stampedLockDemo.deposit(5000.0);
        stampedLockDemo.checkBalance();
        stampedLockDemo.checkBalanceOptimisticRead();
    }
}
