package com.adse2509.sess06_jdbc_intro.dao_classes;

import com.adse2509.sess06_jdbc_intro.DatabaseConnection;
import com.adse2509.sess06_jdbc_intro.model_classes.Product;

//import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Performs CRUD operations against the {@code Products} table.
 *
 * @author Nyanjui
 */
public class ProductDAO
{
    public int create(Product product) throws SQLException
    {
        String sql = "Insert into dbo.Products(ProductName, CategoryID, UnitPrice, StockQuantity) " +
                "values (?, ?, ?, ?)";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getCategoryId());
            statement.setBigDecimal(3, product.getUnitPrice());
            statement.setInt(4, product.getStockQuantity());
            statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys())
            {
                generatedKeys.next();
                return generatedKeys.getInt(1);
            }
        }
    }

    public Product getById(int productId) throws SQLException
    {
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice, StockQuantity "
                + "FROM dbo.Products WHERE ProductID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery())
            {
                return resultSet.next() ? mapRow(resultSet) : null;
            }
        }
    }

    public List<Product> getAll() throws SQLException
    {
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice, StockQuantity "
                + "FROM dbo.Products ORDER BY ProductName";
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                products.add(mapRow(resultSet));
            }
        }
        return products;
    }

    /**
     * Updates a product's name, category, price and stock quantity.
     */
    public boolean update(Product product) throws SQLException
    {
        String sql = "UPDATE dbo.Products "
                + "SET ProductName = ?, CategoryID = ?, UnitPrice = ?, StockQuantity = ? "
                + "WHERE ProductID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getCategoryId());
            statement.setBigDecimal(3, product.getUnitPrice());
            statement.setInt(4, product.getStockQuantity());
            statement.setInt(5, product.getProductId());
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Convenience method used by the sales workflow to reduce stock after
     * a sale, or increase it after a delivery/restock — avoids having to
     * fetch, modify and save the whole product just to change one figure.
     *
     * @param changeInQuantity a positive number to add stock, or a negative
     *                         number to subtract it (e.g. -3 after a sale)
     */
    public boolean adjustStock(int productId, int changeInQuantity) throws SQLException
    {
        String sql = "UPDATE dbo.Products SET StockQuantity = StockQuantity + ? WHERE ProductID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, changeInQuantity);
            statement.setInt(2, productId);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(int productId) throws SQLException
    {
        String sql = "DELETE FROM dbo.Products WHERE ProductID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, productId);
            return statement.executeUpdate() > 0;
        }
    }

    private Product mapRow(ResultSet resultSet) throws SQLException
    {
        return new Product(
                resultSet.getInt("ProductID"),
                resultSet.getString("ProductName"),
                resultSet.getInt("CategoryID"),
                resultSet.getBigDecimal("UnitPrice"),
                resultSet.getInt("StockQuantity"));
    }

}
