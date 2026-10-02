package com.adse2509.sess06_jdbc_intro.model_classes;

/**
 *
 * @author Cui
 */
public class Customer extends Person
{
    // Fields
    private int customerId;
    private String email;
    
    // Constructor
    public Customer(int customerId, String fullName, String phone, String email)
    {
        super(fullName, phone);
        this.customerId = customerId;
        this.email = email;
    }
    
    // Getters & Setters

    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "Customer{" + "customerId=" + customerId + "fullName=" 
                + fullName + ", phone=" + phone + ", email=" + email + '}';
    }
    
    
    
    
}
