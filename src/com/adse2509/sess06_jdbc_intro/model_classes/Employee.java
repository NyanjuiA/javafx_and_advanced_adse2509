package com.adse2509.sess06_jdbc_intro.model_classes;

/**
 * Represents a single row in the {@code Employees} table.
 * 
 * @author Cui
 */
public class Employee extends Person
{
    // Fields
    private int employeeId;
    private String role;
    
    // Constructor
    public Employee(int employeeId, String fullName, String role, String phone)
    {
        super(fullName, phone);
        this.employeeId = employeeId;
        this.role = role;
    }
    
    // Getters & Setters
    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "Employee{" + "employeeId=" + employeeId + "fullName=" 
                + fullName + ", phone=" + phone + ", role=" + role + '}';
    }
    
    
    
}
