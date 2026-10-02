package com.adse2509.sess06_jdbc_intro.model_classes;

import java.math.BigDecimal;

/**
 *
 * @author Cui
 */
public class Product
{
    private int productId;
    private String productName;
    private int categoryId;
    private BigDecimal unitPrice;
    private int stockQuantity;
    
    // Constructor
    public Product(int productId, String productName, int categoryId, BigDecimal unitPrice, int stockQuantity)
    {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
    }
    
    // Accessors & Mutators
    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public int getStockQuantity()
    {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity)
    {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString()
    {
        return "Products{" + "productId=" + productId + ", productName=" + productName + ", categoryId=" + categoryId + ", unitPrice=" + unitPrice + ", stockQuantity=" + stockQuantity + '}';
    }
    
    
    
    
}
