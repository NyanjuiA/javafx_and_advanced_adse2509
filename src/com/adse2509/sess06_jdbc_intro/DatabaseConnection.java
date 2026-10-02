package com.adse2509.sess06_jdbc_intro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class responsible for creating and supplying JDBC connections to
 * the ADSE2509_Supermarket database hosted on Microsoft SQL Server.
 *
 * <p>This is intentionally kept simple for teaching purposes: a single
 * static method returns a brand-new connection each time it is called.
 * In a production system you would typically use a connection pool
 * (e.g. HikariCP) rather than opening a fresh connection per request, but
 * that is beyond the scope of this module.</p>
 *
 * <p><b>Before running any code that uses this class</b>, update the
 * {@code CONNECTION_URL}, {@code USERNAME} and {@code PASSWORD} constants
 * below to match your own SQL Server instance.</p>
 *
 * @author Cui
 */
public class DatabaseConnection
{
    private static final String CONNECTION_URL = 
             "jdbc:sqlserver://Cui-Laptop\\SQL_SVR2025;"
            + "databaseName=ADSE2509_Supermarket;"
            + "encrypt=false";
    
    private static final String USERNAME = "java_sem2";
    private static final String PASSWORD = "ads3_p@s$W0rd8";

    /**
     * A static initialiser block that loads the Microsoft JDBC driver
     * once when the class is fist used. It runs a single time, the first
     * moment this class is referenced anywhere in the program.
     */
    static
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException cnfe)
        {
            throw new RuntimeException(
            """
            Microsoft JDBC Driver was not found on the classpath.
            Ensure mssql-jdbc-<version>.jar has been added to the
            project's dependencies.
            """, cnfe
            );
        }
    }
    
    /**
     * Private constructor to prevent this utility class from being instantiated,
     * all its members are static.
     */
    private DatabaseConnection(){}


    /**
     * Opens and returns a new connection to the ADSE2509_Supermarket database.
     *
     * <p>Callers are responsible for closing the returned {@link Connection}
     * once they have finished using it — ideally via a try-with-resources
     * block, which closes it automatically even if an exception occurs.</p>
     *
     * @return an open {@link Connection} to the ADSE_Supermarket database
     * @throws SQLException if the connection cannot be established, for
     *                       example because SQL Server is not running or
     *                       the credentials above are incorrect
     */
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
    }
}
