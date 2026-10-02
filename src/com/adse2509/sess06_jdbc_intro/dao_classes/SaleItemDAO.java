package com.adse2509.sess06_jdbc_intro.dao_classes;

import com.adse2509.sess06_jdbc_intro.DatabaseConnection;
import com.adse2509.sess06_jdbc_intro.model_classes.SaleItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs CRUD operations against the (@code SaleItems} table (order lines).
 */
public class SaleItemDAO
{
    public int create(SaleItem saleItem) throws SQLException
    {
        String sql = "INSERT INTO dbo.SaleItems (SaleID, ProductID, Quantity, UnitPriceAtSale) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS))
        {
            return insertAndReturnId(statement, saleItem);
        }
    }

    /**
     * Shared insert logic, also used by {@link SalesTransactionService} so
     * that line items can be created on a connection already participating
     * in a wider transaction.
     */
    public static int insertAndReturnId(PreparedStatement statement, SaleItem saleItem) throws SQLException
    {
        statement.setInt(1, saleItem.getSaleId());
        statement.setInt(2, saleItem.getProductId());
        statement.setInt(3, saleItem.getQuantity());
        statement.setBigDecimal(4, saleItem.getUnitPriceAtSale());
        statement.executeUpdate();

        try (ResultSet generatedKeys = statement.getGeneratedKeys())
        {
            generatedKeys.next();
            return generatedKeys.getInt(1);
        }
    }

    /**
     * Retrieves every line item belonging to a given sale — useful for
     * printing a full receipt.
     */
    public List<SaleItem> getBySaleId(int saleId) throws SQLException
    {
        String sql = "SELECT SaleItemID, SaleID, ProductID, Quantity, UnitPriceAtSale, LineTotal "
                + "FROM dbo.SaleItems WHERE SaleID = ?";
        List<SaleItem> saleItems = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, saleId);

            try (ResultSet resultSet = statement.executeQuery())
            {
                while (resultSet.next())
                {
                    saleItems.add(mapRow(resultSet));
                }
            }
        }
        return saleItems;
    }

    /**
     * Updates the quantity of an existing line item. UnitPriceAtSale is
     * intentionally left unchanged here, as it represents the price that
     * was charged at the time of sale and should not normally be edited
     * after the fact.
     */
    public boolean updateQuantity(int saleItemId, int newQuantity) throws SQLException
    {
        String sql = "UPDATE dbo.SaleItems SET Quantity = ? WHERE SaleItemID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, newQuantity);
            statement.setInt(2, saleItemId);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(int saleItemId) throws SQLException
    {
        String sql = "DELETE FROM dbo.SaleItems WHERE SaleItemID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, saleItemId);
            return statement.executeUpdate() > 0;
        }
    }

    private SaleItem mapRow(ResultSet resultSet) throws SQLException
    {
        return new SaleItem(
                resultSet.getInt("SaleItemID"),
                resultSet.getInt("SaleID"),
                resultSet.getInt("ProductID"),
                resultSet.getInt("Quantity"),
                resultSet.getBigDecimal("UnitPriceAtSale"),
                resultSet.getBigDecimal("LineTotal"));
    }

}
