package com.adse2509.sess06_jdbc_intro.dao_classes;

import com.adse2509.sess06_jdbc_intro.DatabaseConnection;
import com.adse2509.sess06_jdbc_intro.model_classes.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs CRUD operations against the {@code Categories} table.
 *
 * @author Nyanjui
 */
public class CategoryDAO
{

    /**
     * Inserts a new category and returns the database generated CategoryID.
     *
     * @param category the category to insert (its categoryId is ignored)
     * @return the generated CategoryId
     * @throws SQLException when the insert fails, for example due to a duplicate
     * category name.
     */
    public int create(Category category) throws SQLException
    {
        String sql = "Insert into dbo.Categories CategoryName values(?)";

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1,category.getCategoryName());
            statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys())
            {
                generatedKeys.next();
                return generatedKeys.getInt(1);
            }
        }
    }

    /**
     *  Retrieves a single category by its primary key.
     *
     * @param categoryId the categoryId to search for
     * @return the matching {@link Category} or {@code null} if noe was found
     * @throws SQLException when the search fails e.g. wrong credentials or database not running
     */
    public Category getById(int categoryId) throws SQLException
    {
        String sql = "select CategoryID, CategoryName from dbo.Categories where categoryId = ?";

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1,categoryId);

            try(ResultSet resultSet = statement.executeQuery())
            {
                if(resultSet.next())
                    return mapRow(resultSet);

                return null;
            }
        }
    }

    /**
     * Retrieves every category in the Categories table, ordered alphabetically by name
     * @return list of all categories in the categories table
     * @throws SQLException when the search fails e.g. wrong credentials or database not running
     */
    public List<Category> getAll() throws SQLException
    {
        String sql = "select CategoryID, CategoryName from dbo.Categories order by CategoryName";
        List<Category> categories = new ArrayList<>();

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery())
        {
            while(resultSet.next())
            {
                categories.add(mapRow(resultSet));
            }
        }
        return categories;
    }

    /**
     * Updates the name of an existing category.
     *
     * @param category the category to update; its categoryId identifies
     *                 the row and categoryName supplies the new value
     * @return {@code true} if a row was updated, {@code false} if no
     *         category with that ID existed
     */
    public boolean update(Category category) throws SQLException
    {
        String sql = "UPDATE dbo.Categories SET CategoryName = ? WHERE CategoryID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getCategoryId());
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Deletes a category by its primary key.
     *
     * <p>Note: this will fail with a foreign key constraint violation if
     * any products still reference this category — by design, so that
     * category deletions cannot silently orphan product data.</p>
     *
     * @return {@code true} if a row was deleted, {@code false} otherwise
     */
    public boolean delete(int categoryId) throws SQLException
    {
        String sql = "DELETE FROM dbo.Categories WHERE CategoryID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, categoryId);
            return statement.executeUpdate() > 0;
        }
    }

    private Category mapRow(ResultSet resultSet) throws SQLException
    {
        return new Category(
                resultSet.getInt("CategoryID"),
                resultSet.getString("CategoryName")
        );
    }
}
