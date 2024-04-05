package com.example.furnishFantasy.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.furnishFantasy.models.Category;

public class categoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setCatID(rs.getString("categoryid"));
        category.setCatName(rs.getString("cat_name"));
        category.setCatDescription(rs.getString("cat_description"));
        category.setCatImg(rs.getString("cat_img"));
  
        // Set other properties as needed
        return category;
    }
}
