package com.example.furnishFantasy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.furnishFantasy.models.Category;
import com.example.furnishFantasy.models.Product;
import com.example.furnishFantasy.services.productService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller // This means that this class is a Controller
@CrossOrigin(origins = "*")
@RequestMapping(path="/furnishFantasy/product") 	
public class productController {
	 @Autowired
	    private productService ProductService;
	 
	 @PostMapping(path="/addProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	 public @ResponseBody ResponseEntity<Object> addNewProduct( @RequestParam("product") String productJson, @RequestParam(name = "file", required = true) MultipartFile file) {
		 Product product = null;
		 try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            product = objectMapper.readValue(productJson, Product.class);
	            
	        } catch (JsonProcessingException e) {
	        	System.out.print(e);	
	            return new ResponseEntity<>("Invalid productJson JSON format", HttpStatus.BAD_REQUEST);
	        }
		 return ProductService.addProduct(product, file);
	 }
	  @GetMapping(path="/getProduct")
	  public @ResponseBody ResponseEntity<Object> getAllProducts() {
		return ProductService.getAllProducts();
	  }
	  @GetMapping(path="/getProductByCategory/{categoryId}")
	  public ResponseEntity<Object> getProductByCategory(@PathVariable String categoryId) {
	      return ProductService.getProductByCategory(categoryId);
	  }
}
