package com.adse2509.sess06_jdbc_intro.model_classes;

import java.math.BigDecimal;

/**
 * Represents a single row in the {@code SaleItems} table (an order "line").
 * 
 * <p>
 * Note that {@code lineTotal} is a computed column in the database
 * (Quantity x UnitPriceAtSale), so it is read back from SQL Server but is 
 * never sent as part of an Insert or Update statement.
 * </p>
 * @author Cui
 */
public class SaleItem
{
    private int saleItemId;
    private int saleId;
    private int productId;
    private int quantity;
    private BigDecimal UnitPriceAtSale;
    private BigDecimal lineTotal; // read-only; Computed by SQL-Server

    public SaleItem(int saleItemId, int saleId, int productId, int quantity, BigDecimal UnitPriceAtSale, BigDecimal lineTotal)
    {
        this.saleItemId = saleItemId;
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
        this.UnitPriceAtSale = UnitPriceAtSale;
        this.lineTotal = lineTotal;
    }

    public BigDecimal getLineTotal()
    {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal)
    {
        this.lineTotal = lineTotal;
    }

    public int getSaleItemId()
    {
        return saleItemId;
    }

    public void setSaleItemId(int saleItemId)
    {
        this.saleItemId = saleItemId;
    }

    public int getSaleId()
    {
        return saleId;
    }

    public void setSaleId(int saleId)
    {
        this.saleId = saleId;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPriceAtSale()
    {
        return UnitPriceAtSale;
    }

    public void setUnitPriceAtSale(BigDecimal UnitPriceAtSale)
    {
        this.UnitPriceAtSale = UnitPriceAtSale;
    }

    @Override
    public String toString()
    {
        return "SaleItem{" + "saleItemId=" + saleItemId + ", saleId=" + saleId + ", productId=" + productId + ", quantity=" + quantity + ", UnitPriceAtSale=" + UnitPriceAtSale + ", lineTotal=" + lineTotal + '}';
    }
    
    
    
}
