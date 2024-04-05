package com.example.furnishFantasy.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.furnishFantasy.handlers.ResponseHandler;
import com.example.furnishFantasy.models.Category;
import com.example.furnishFantasy.models.User;
import com.example.furnishFantasy.repository.categoryRepository;
import com.example.furnishFantasy.repository.categoryRepositoryImpl;
@Service
public class categoryService {
	 @Autowired 
	 private  categoryRepository catRepro;
	 @Autowired()
	 private categoryRepositoryImpl categoryRepositoryImpl;
	public ResponseEntity<Object> addCategory(Category category, MultipartFile file){
	
		 if (category == null) {
	            // Handle the case where the category object is null
	            return ResponseHandler.generateResponse("Category object is null", HttpStatus.BAD_REQUEST, null);
	        }
	        try {
	            // Encode the image file to base64 if it's not null
	            String encodedImage = null;
	            if (file != null && !file.isEmpty()) {
	                encodedImage = Base64.getEncoder().encodeToString(file.getBytes());
	            }

	            // Set the encoded image to the category object
	            category.setCatImg(encodedImage);

	            // Check if the category already exists
	            List<Category> existingCategories = categoryRepositoryImpl.authenticateCategory(category);
	            if (!existingCategories.isEmpty()) {
	                // Category already exists, return response
	                return ResponseHandler.generateResponse("Category already exists", HttpStatus.CONFLICT, null);
	            }

	            // Save the category
	            catRepro.save(category);

	            // Return success response
	            return ResponseHandler.generateResponse("Category added successfully", HttpStatus.OK, null);
	        } catch (IOException e) {
	            // Handle IOException
	            e.printStackTrace();
	            return ResponseHandler.generateResponse("Error processing file", HttpStatus.INTERNAL_SERVER_ERROR, null);
	        }
	}
	  
	
	
	
	public ResponseEntity<Object> getAllCategory() {

	    	 List<Category> category = categoryRepositoryImpl.getCategory();
	    	
	    	 return ResponseHandler.generateResponse("All data fetched!", HttpStatus.OK,category);
		}
	  public ResponseEntity<Object> deleteCategory(List<String> categoryIds){
		  if (categoryIds == null || categoryIds.isEmpty()) {
	            // If the list of category IDs is null or empty, return bad request response
	            return ResponseHandler.generateResponse("Null Category ID !", HttpStatus.INTERNAL_SERVER_ERROR,null);
	        }
		  else {
			  
			  if(categoryRepositoryImpl.deleteCategory(categoryIds)) {
				  return ResponseHandler.generateResponse("Data Deleted Successfully !", HttpStatus.OK,null);
			  }
			  else {
				  return ResponseHandler.generateResponse("Deleted Failed !", HttpStatus.INTERNAL_SERVER_ERROR,null);
			  }
		  }
		
		  
	  }
	  public ResponseEntity<Object> updateCategory(String CategoryID, Category category,MultipartFile file){
			 if (category == null) {
		            // Handle the case where the category object is null
		            return ResponseHandler.generateResponse("Category object is null", HttpStatus.BAD_REQUEST, null);
		        }
		        try {
		            // Encode the image file to base64 if it's not null
		            String encodedImage = null;
		            if (file != null && !file.isEmpty()) {
		                encodedImage = Base64.getEncoder().encodeToString(file.getBytes());
		            }
		            category.setCatImg(encodedImage);

		            if (categoryRepositoryImpl.updateCategory(CategoryID, category)) {

		      		  return ResponseHandler.generateResponse("Data Updated Successfully !", HttpStatus.OK,null); 
		            }
		            else {
		            	return ResponseHandler.generateResponse("Data Updated Failed", HttpStatus.INTERNAL_SERVER_ERROR, null);
		            	
		            }
		        } catch (IOException e) {
		            // Handle IOException
		            e.printStackTrace();
		            return ResponseHandler.generateResponse("Error processing file", HttpStatus.INTERNAL_SERVER_ERROR, null);
		        }
			
		}
		  
		  
		 
	  

}
