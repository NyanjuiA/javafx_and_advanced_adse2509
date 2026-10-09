package com.adse2509.sess10_new_and_advanced_java_features;

/**
 * This program demonstrates the use of sealed classes in Java to restrict
 * inheritance, improve encapsulation, and enhance security in a payment
 * system.
 *
 * - `Payment` is a sealed superclass that can only be extended by specific
 * classes.
 * - `CreditCardPay` and `PayPalPayment` are the ony permitted subclasses
 * of `Payment` class.
 * - Any attempt to create additional subclasses of `Payment` outside the
 * permitted list will result in a compilation error, ensuring a controlle and
 * secure class heirachy.
 *
 * @author Nyanjui
 */
public class SealedClassDemo
{
    public static void main(String[] args)
    {
        // Create instances of the CreditCardPayment and PayPalPayment
        Payment creditPayment = new CreditCardPayment("1234-5678-9012-3457", 10000.55);
        Payment paypalPayment = new PayPalPayment(5500.75, "user@email.co.ke");

        // Process each payment and display details
        processPayment(creditPayment);
        processPayment(paypalPayment);

    }

    /**
     * Processes a payment and prints the details.
     *
     * @param payment the payment to be processed. (can be any permitted subclass
     * of Payment)
     */
    public static void processPayment(Payment payment)
    {
        System.out.println("Processing payment of type: " + payment.getClass().getSimpleName());
        System.out.println("Amount: Kes. " + payment.getAmount());
        payment.process();
        System.out.println();
    }
}

sealed abstract class Payment permits CreditCardPayment, PayPalPayment
{
    private final double amount;

    /**
     * Returns the payment amount.
     *
     * @param amount
     */
    public Payment(double amount)
    {
        this.amount = amount;
    }

    /**
     * Returns the payment amount.
     *
     * @return the payment amount due.
     */
    public double getAmount()
    {
        return this.amount;
    }

    /**
     * Abstract method to process the payment. This must be implemented by
     * subclasses.
     */
    public abstract void process();
}

/**
 * Final class representing a credit card payment.
 *
 * - This class cannot be further extended, as it is both final and permitted by
 * Payment.
 *
 * @author a.nyanjui
 */
final class CreditCardPayment extends Payment
{
    private final String cardNumber;

    /**
     * Constructor to initialise the payment amount and card number.
     *
     * @param cardNumber the credit card number for the payment
     * @param amount the amount of the payment
     */
    public CreditCardPayment(String cardNumber, double amount)
    {
        super(amount);
        this.cardNumber = cardNumber;
    }

    /**
     * Process the credit card payment and prints the card number (last 4
     * digits only).
     */
    @Override
    public void process()
    {
        System.out.println("Processing credit card payment with card number "
                + "ending in: " + cardNumber.substring(cardNumber.length() - 4));
    }
}

/**
 * Final class representing a PayPal payment.
 *
 * - This class cannot be further extended, as it is both final and permitted by
 * Payment.
 *
 * @author a.nyanjui
 */
final class PayPalPayment extends Payment
{
    private final String email;

    /**
     * Constructor to initialise the payment amount and email address.
     *
     * @param amount the amount of the payment.
     * @param email the email associated with the PayPal account.
     */
    public PayPalPayment(double amount, String email)
    {
        super(amount);
        this.email = email;
    }

    /**
     * Processes the PayPal payment and prints the associated email.
     */
    @Override
    public void process()
    {
        System.out.println("Processing PayPal payment for account: " + email);
    }
}
