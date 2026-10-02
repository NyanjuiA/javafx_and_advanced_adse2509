package com.adse2509.sess06_jdbc_intro.model_classes;

/**
 *
 * @author Cui
 */
public class Person
{
    // Fields
    protected String fullName;
    protected String phone;
    
    // Constructor
    public Person(String fullName, String phone)
    {
        this.fullName = fullName;
        this.phone = phone;
    }
    
    // Getters & setters
    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public String toString()
    {
        return "Person{" + "fullName=" + fullName + ", phone=" + phone + '}';
    }
    
    
}
