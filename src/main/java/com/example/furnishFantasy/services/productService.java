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
import com.example.furnishFantasy.models.Product;
import com.example.furnishFantasy.repository.productRepositoryImpl;
import com.example.furnishFantasy.repository.productRepository;
@Service
public class productService {
	 @Autowired 
	 private  productRepository prodRepo;
	 @Autowired()
	 private productRepositoryImpl productRepositoryImpl;
	public ResponseEntity<Object> addProduct(Product product, MultipartFile file){
	
		if (product == null) {
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
	            product.setpImg(encodedImage);

	            // Check if the category already exists
	            List<Product> existingProducts = productRepositoryImpl.authenticateProduct(product);
	            if (!existingProducts.isEmpty()) {
	                // Category already exists, return response
	                return ResponseHandler.generateResponse("Product already exists", HttpStatus.CONFLICT, null);
	            }

	            // Save the category
	            prodRepo.save(product);

	            // Return success response
	            return ResponseHandler.generateResponse("Product added successfully", HttpStatus.OK, null);
	        } catch (IOException e) {
	            // Handle IOException
	            e.printStackTrace();
	            return ResponseHandler.generateResponse("Error processing file", HttpStatus.INTERNAL_SERVER_ERROR, null);
	        }
	}
	public ResponseEntity<Object> getAllProducts() {

   	 List<Product> products = productRepositoryImpl.getProduct();
   	
   	 return ResponseHandler.generateResponse("All data fetched!", HttpStatus.OK,products);
	}
	  public ResponseEntity<Object> getProductByCategory(String CategoryID){
			 if (CategoryID == null) {
		            // Handle the case where the category object is null
		            return ResponseHandler.generateResponse("Category ID  is needed", HttpStatus.BAD_REQUEST, null);
		        }
			 List<Product> products = productRepositoryImpl.getProductByCategory(CategoryID);

		          
		      		  return ResponseHandler.generateResponse("Data Fetched Successfully !", HttpStatus.OK,products); 
		           
		      
			
		}

}
