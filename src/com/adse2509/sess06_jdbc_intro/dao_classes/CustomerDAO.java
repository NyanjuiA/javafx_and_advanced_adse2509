package com.adse2509.sess06_jdbc_intro.dao_classes;

import com.adse2509.sess06_jdbc_intro.DatabaseConnection;
import com.adse2509.sess06_jdbc_intro.model_classes.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs CRUD operations against the {@code Customers} table.
 *
 * @author Nyanjui
 */
public class CustomerDAO
{
    public  int create(Customer customer) throws SQLException
    {
        String sql = "INSERT INTO dbo.Customers(FullName, Phone, Email) VALUES (?, ?, ?)";

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, customer.getFullName());
            statement.setString(2, customer.getPhone());
            statement.setString(3, customer.getEmail());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys())
            {
                generatedKeys.next();
                return generatedKeys.getInt(1);
            }
        }

    }

    public Customer getById(int customerId) throws SQLException
    {
        String sql = "SELECT CustomerID, FullName, Phone, Email FROM dbo.Customers WHERE CustomerID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, customerId);

            try (ResultSet resultSet = statement.executeQuery())
            {
                return resultSet.next() ? mapRow(resultSet) : null;
            }
        }
    }

    public List<Customer> getAll() throws SQLException
    {
        String sql = "SELECT CustomerID, FullName, Phone, Email FROM dbo.Customers ORDER BY FullName";
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                customers.add(mapRow(resultSet));
            }
        }
        return customers;
    }

    public boolean update(Customer customer) throws SQLException
    {
        String sql = "UPDATE dbo.Customers SET FullName = ?, Phone = ?, Email = ? WHERE CustomerID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, customer.getFullName());
            statement.setString(2, customer.getPhone());
            statement.setString(3, customer.getEmail());
            statement.setInt(4, customer.getCustomerId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(int customerId) throws SQLException
    {
        String sql = "DELETE FROM dbo.Customers WHERE CustomerID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, customerId);
            return statement.executeUpdate() > 0;
        }
    }

    private Customer mapRow(ResultSet resultSet) throws SQLException
    {
        return new Customer(
                resultSet.getInt("CustomerID"),
                resultSet.getString("FullName"),
                resultSet.getString("Phone"),
                resultSet.getString("Email"));
    }

}
