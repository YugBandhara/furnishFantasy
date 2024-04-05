package com.example.furnishFantasy.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.furnishFantasy.mapper.categoryMapper;
import com.example.furnishFantasy.mapper.productMapper;
import com.example.furnishFantasy.models.Category;
import com.example.furnishFantasy.models.Product;
@Repository
public class productRepositoryImpl {
	@Autowired
//	@Qualifier(value="mysqlJdbcTemplate")
	JdbcTemplate jdbcTemplate;
	public List<Product> authenticateProduct(Product product) {
		 if (product == null || product.getName() == null) {
	            return Collections.emptyList(); // Return an empty list if the category or its name is null
	        }
			 String query = "SELECT p_name,categoryid FROM furnishFantasy.product WHERE p_name = ? AND categoryid=?";
			 List<Product> products = jdbcTemplate.query(query, new productMapper(), product.getName(),product.getCategoryId());
			 if (!products.isEmpty()) {
			        // If there's at least one product with the same name and category ID, it's considered a duplicate
				 return Collections.emptyList();
			    }   
			 return products;
		 
	}
public List<Product> getProduct(){
		
		String query = "SELECT * FROM furnishFantasy.product";
		List<Product> products =jdbcTemplate.query(query, new productMapper());
		return products;
	}
public List<Product> getProductByCategory(String CategoryID){
	
	String query = "SELECT * FROM furnishFantasy.product WHERE categoryid=?";
	List<Product> products =jdbcTemplate.query(query, new productMapper(),CategoryID);
	return products;
}
	
}
