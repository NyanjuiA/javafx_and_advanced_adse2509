package com.adse2509.sess06_jdbc_intro.dao_classes;

import com.adse2509.sess06_jdbc_intro.DatabaseConnection;
import com.adse2509.sess06_jdbc_intro.model_classes.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO
{
    public int create(Employee employee) throws SQLException
    {
        String sql = "Insert into dbo.Employees (FullName, Role, Phone) values (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, employee.getFullName());
            statement.setString(2, employee.getRole());
            statement.setString(3, employee.getPhone());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys())
            {
                generatedKeys.next();
                return generatedKeys.getInt(1);
            }
        }
    }

    public Employee getById(int employeeId) throws SQLException
    {
        String sql = "SELECT EmployeeID, FullName, Role, Phone FROM dbo.Employees WHERE EmployeeID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, employeeId);

            try (ResultSet resultSet = statement.executeQuery())
            {
                return resultSet.next() ? mapRow(resultSet) : null;
            }
        }
    }

    public List<Employee> getAll() throws SQLException
    {
        String sql = "SELECT EmployeeID, FullName, Role, Phone FROM dbo.Employees ORDER BY FullName";
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                employees.add(mapRow(resultSet));
            }
        }
        return employees;
    }

    public boolean update(Employee employee) throws SQLException
    {
        String sql = "UPDATE dbo.Employees SET FullName = ?, Role = ?, Phone = ? WHERE EmployeeID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, employee.getFullName());
            statement.setString(2, employee.getRole());
            statement.setString(3, employee.getPhone());
            statement.setInt(4, employee.getEmployeeId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(int employeeId) throws SQLException
    {
        String sql = "DELETE FROM dbo.Employees WHERE EmployeeID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, employeeId);
            return statement.executeUpdate() > 0;
        }
    }

    private Employee mapRow(ResultSet resultSet) throws SQLException
    {
        return new Employee(
                resultSet.getInt("EmployeeID"),
                resultSet.getString("FullName"),
                resultSet.getString("Role"),
                resultSet.getString("Phone"));
    }

}
