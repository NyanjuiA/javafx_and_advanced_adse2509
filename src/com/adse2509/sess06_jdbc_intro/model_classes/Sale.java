package com.adse2509.sess06_jdbc_intro.model_classes;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author Cui
 */
public class Sale
{
    private int saleId;
    private Timestamp saleDate;
    private Integer customerId; // Boxed int, as this field is nullable
    private int employeeId;
    private BigDecimal totalAmount;
    private String paymentMethod;

    public Sale(int saleId, Timestamp saleDate, Integer customerId, int employeeId, BigDecimal totalAmount, String paymentMethod)
    {
        this.saleId = saleId;
        this.saleDate = saleDate;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
    }

    public int getSaleId()
    {
        return saleId;
    }

    public void setSaleId(int saleId)
    {
        this.saleId = saleId;
    }

    public Timestamp getSaleDate()
    {
        return saleDate;
    }

    public void setSaleDate(Timestamp saleDate)
    {
        this.saleDate = saleDate;
    }

    public Integer getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Integer customerId)
    {
        this.customerId = customerId;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }

    public BigDecimal getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString()
    {
        return "Sale{" + "saleId=" + saleId + ", saleDate=" + saleDate + ", customerId=" + customerId + ", employeeId=" + employeeId + ", totalAmount=" + totalAmount + ", paymentMethod=" + paymentMethod + '}';
    }
    
    
    
    
}
