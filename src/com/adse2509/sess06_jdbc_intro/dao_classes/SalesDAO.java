package com.adse2509.sess06_jdbc_intro.dao_classes;

import com.adse2509.sess06_jdbc_intro.DatabaseConnection;
import com.adse2509.sess06_jdbc_intro.model_classes.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs CRUD operations against the {@code Sales} table (the order header).
 *
 * <p>
 *     For inserting a complete sale together with ist line items in a single
 *     all-or-nothing operation, see {@link SalesTransactionService}.
 * </p>
 */
public class SalesDAO
{
    public int create(Sale sale) throws SQLException
    {
        String sql = "INSERT INTO dbo.Sales (CustomerID, EmployeeID, TotalAmount, PaymentMethod) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS))
        {
            return insertAndReturnId(statement, sale);
        }
    }

    /**
     * Shared insert logic, also used by {@link SalesTransactionService} so
     * that a sale can be created using a connection that is already
     * participating in a wider transaction.
     */
    public int insertAndReturnId(PreparedStatement statement, Sale sale) throws SQLException
    {
        if (sale.getCustomerId() == null)
        {
            statement.setNull(1, java.sql.Types.INTEGER);
        }
        else
        {
            statement.setInt(1, sale.getCustomerId());
        }
        statement.setInt(2, sale.getEmployeeId());
        statement.setBigDecimal(3, sale.getTotalAmount());
        statement.setString(4, sale.getPaymentMethod());
        statement.executeUpdate();

        try (ResultSet generatedKeys = statement.getGeneratedKeys())
        {
            generatedKeys.next();
            return generatedKeys.getInt(1);
        }
    }

    public Sale getById(int saleId) throws SQLException
    {
        String sql = "SELECT SaleID, SaleDate, CustomerID, EmployeeID, TotalAmount, PaymentMethod "
                + "FROM dbo.Sales WHERE SaleID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, saleId);

            try (ResultSet resultSet = statement.executeQuery())
            {
                return resultSet.next() ? mapRow(resultSet) : null;
            }
        }
    }

    public List<Sale> getAll() throws SQLException
    {
        String sql = "SELECT SaleID, SaleDate, CustomerID, EmployeeID, TotalAmount, PaymentMethod "
                + "FROM dbo.Sales ORDER BY SaleDate DESC";
        List<Sale> sales = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                sales.add(mapRow(resultSet));
            }
        }
        return sales;
    }

    /**
     * Updates the customer, employee and total amount recorded against an
     * existing sale. The original SaleDate is left unchanged.
     */
    public boolean update(Sale sale) throws SQLException
    {
        String sql = "UPDATE dbo.Sales SET CustomerID = ?, EmployeeID = ?, TotalAmount = ?, "
                + "PaymentMethod = ? WHERE SaleID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            if (sale.getCustomerId() == null)
            {
                statement.setNull(1, java.sql.Types.INTEGER);
            }
            else
            {
                statement.setInt(1, sale.getCustomerId());
            }
            statement.setInt(2, sale.getEmployeeId());
            statement.setBigDecimal(3, sale.getTotalAmount());
            statement.setString(4, sale.getPaymentMethod());
            statement.setInt(5, sale.getSaleId());
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Deletes a sale. Because {@code SaleItems} has an ON DELETE CASCADE
     * relationship to {@code Sales}, this will also remove that sale's
     * line items automatically.
     */
    public boolean delete(int saleId) throws SQLException
    {
        String sql = "DELETE FROM dbo.Sales WHERE SaleID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, saleId);
            return statement.executeUpdate() > 0;
        }
    }

    private Sale mapRow(ResultSet resultSet) throws SQLException
    {
        return new Sale(
                resultSet.getInt("SaleID"),
                resultSet.getTimestamp("SaleDate"),
                (Integer) resultSet.getObject("CustomerID"),
                resultSet.getInt("EmployeeID"),
                resultSet.getBigDecimal("TotalAmount"),
                resultSet.getString("PaymentMethod"));
    }

}
