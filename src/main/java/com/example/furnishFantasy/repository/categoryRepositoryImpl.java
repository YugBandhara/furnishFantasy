package com.example.furnishFantasy.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import com.example.furnishFantasy.mapper.categoryMapper;
import com.example.furnishFantasy.mapper.userMapper;
import com.example.furnishFantasy.models.Category;
import com.example.furnishFantasy.models.User;


@Repository
public class categoryRepositoryImpl {
	@Autowired
//	@Qualifier(value="mysqlJdbcTemplate")
	JdbcTemplate jdbcTemplate;
	public List<Category> authenticateCategory(Category category) {
		 if (category == null || category.getCatName() == null) {
	            return Collections.emptyList(); // Return an empty list if the category or its name is null
	        }
		
			 String query = "SELECT cat_name,cat_description FROM furnishFantasy.category WHERE cat_name = ?";
			 List<Category> categories = jdbcTemplate.query(query, new categoryMapper(), category.getCatName());
		        return categories;
		 
		
	}
public List<Category> getCategory(){
		
		String query = "SELECT categoryid,cat_name,cat_description,cat_img FROM furnishFantasy.category";
		List<Category> category =jdbcTemplate.query(query, new categoryMapper());
		return category;
	}
public boolean deleteCategory(List<String> categoryIds) {
	String query = "DELETE FROM furnishFantasy.category WHERE categoryid = ?";
	 for (String categoryId : categoryIds) {
         int rowsAffected = jdbcTemplate.execute(query, (PreparedStatementCallback<Integer>) ps -> {
             ps.setString(1, categoryId);
             return ps.executeUpdate();
         });

         if (rowsAffected != 1) {
             // Validation failed for one or more categories
             return false;
         }
     }

     // All categories were deleted successfully
     return true;
	
}
@SuppressWarnings("deprecation")
public boolean updateCategory(String categoryId, Category category) {
    // Check if the category exists
    String checkQuery = "SELECT COUNT(*) FROM furnishFantasy.category WHERE categoryid = ?";
Integer count = null;
    
    try {
        count = jdbcTemplate.queryForObject(checkQuery, new Object[]{categoryId}, Integer.class);
    } catch (EmptyResultDataAccessException e) {
        // This means the query did not find any rows matching the criteria
        return false;
    }

    if (count != null && count > 0) {
        // If category exists, proceed to update
        String updateQuery = "UPDATE furnishFantasy.category SET cat_name = ?, cat_description = ? WHERE categoryid = ?";
        int rowsAffected = jdbcTemplate.update(updateQuery, category.getCatName(), category.getCatDescription(), categoryId);

        return rowsAffected > 0; // return true if update was successful
    } else {
        // Category does not exist
        return false;
    }
}

	

}
