package com.adse2509.sess06_jdbc_intro.model_classes;

/**
 *
 * @author Cui
 */
public class Category
{
    // Fields
    private int categoryId;
    private String categoryName;
    
    // Constructor

    public Category(int categoryId, String categoryName)
    {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    @Override
    public String toString()
    {
        return "Category{" + "categoryId=" + categoryId + ", categoryName=" + categoryName + '}';
    }
    
    
}
