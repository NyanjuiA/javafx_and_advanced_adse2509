package com.adse2509.sess07_advanced_jdbc;

import com.adse2509.sess06_jdbc_intro.DatabaseConnection;
import com.adse2509.sess06_jdbc_intro.dao_classes.ProductDAO;
import com.adse2509.sess06_jdbc_intro.dao_classes.SaleItemDAO;
import com.adse2509.sess06_jdbc_intro.dao_classes.SalesDAO;
import com.adse2509.sess06_jdbc_intro.model_classes.Sale;
import com.adse2509.sess06_jdbc_intro.model_classes.SaleItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Demonstrates recording a complete sale (header plus one or more line
 * items) as a single, all-or-nothing JDBC transaction — a good example when building the till/checkout feature of the CLI.
 *
 * <p>Without a transaction, a failure partway through (for example, the
 * second line item referencing a ProductID that does not exist) could
 * leave a Sale row in the database with no items attached to it, or with
 * only some of its items recorded. Wrapping the whole operation in a
 * transaction means either everything is saved, or nothing is.</p>
 *
 * @author Nyanjui
 */
public class SalesTransactionService
{
    private final SalesDAO salesDAO =  new SalesDAO();
    private final ProductDAO productDAO =  new ProductDAO();
    private final SaleItemDAO saleItemDAO =  new SaleItemDAO();


    public int recordCompleteSale(Sale sale, List<SaleItem> saleItems) throws SQLException
    {
        Connection connection = null;
        try
        {
            connection = DatabaseConnection.getConnection();

            // Turn off auto-commit so that every statement below becomes
            // part of one manual transaction, rather than being saved to the
            // database immediately as each statement runs
            connection.setAutoCommit(false);

            // Insert the sale header first, so we obtain its generated ID.
            int saleId;
            try(PreparedStatement saleStatement = connection.prepareStatement(
                    """
                        INSERT INTO dbo.sales
                        (CustomerID, EmployeeID, TotalAmount,PaymentMethod)
                        values (?, ?, ?, ?)
                        """, Statement.RETURN_GENERATED_KEYS
            ))
            {
                saleId = salesDAO.insertAndReturnId(saleStatement,sale);
            }
            // Insert each line item, linked to the sale header above, and
            // reduce the corresponding product's stock quantity
            for(SaleItem saleItem : saleItems)
            {
                saleItem.setSaleId(saleId);
                try(PreparedStatement itemStatement = connection.prepareStatement(
                        """
                            INSERT INTO dbo.saleItem (SaleID, ProductID, Quantity,UnitPriceAtSale)
                            values (?, ?, ?, ?)
                            """, Statement.RETURN_GENERATED_KEYS
                ))
                {
                    SaleItemDAO.insertAndReturnId(itemStatement,saleItem);
                }

                try(PreparedStatement stockStatement = connection.prepareStatement(
                        """
                            UPDATE dbo.Products 
                            SET StockQuantity = StockQuantity - WHERE  ProductID = ?
                            """
                ))
                {
                    stockStatement.setInt(1, saleItem.getQuantity());
                    stockStatement.setInt(2, saleItem.getProductId());
                    stockStatement.executeUpdate();
                }
            }

            // Everything succeeded - Make the changes permanent
            connection.commit();
            return saleId;
        }
        catch (SQLException exception)
        {
            // Something went wrong — undo every change made since
            // setAutoCommit(false) was called, leaving the database
            // exactly as it was before this method was called.
            if (connection != null)
            {
                connection.rollback();
            }
            throw exception;
        }
        finally
        {
            if (connection != null)
            {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

    }
}
