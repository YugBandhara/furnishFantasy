package com.example.furnishFantasy.controllers;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.example.furnishFantasy.models.Category;
import com.example.furnishFantasy.services.categoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller // This means that this class is a Controller
@CrossOrigin(origins = "*")
@RequestMapping(path="/furnishFantasy/category") 	
public class categoryController {
	 @Autowired
	    private categoryService CategoryService;
	 
	 @PostMapping(path="/addCategory", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	 public @ResponseBody ResponseEntity<Object> addNewCategory( @RequestParam("category") String categoryJson, @RequestParam(name = "file", required = true) MultipartFile file) {
		 Category category = null;
		 try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            category = objectMapper.readValue(categoryJson, Category.class);
	        } catch (JsonProcessingException e) {
	            return new ResponseEntity<>("Invalid category JSON format", HttpStatus.BAD_REQUEST);
	        }
		 return CategoryService.addCategory(category, file);
	 }
	  @GetMapping(path="/getCategory")
	  public @ResponseBody ResponseEntity<Object> getAllCategories() {
		return CategoryService.getAllCategory();
	  }
	  @PostMapping(path="/deleteCategory",consumes = "application/json") // Map ONLY POST Requests
	  public @ResponseBody ResponseEntity<Object> deleteCategory (@RequestBody (required=false)List<String> categoryIds) {
		  return CategoryService.deleteCategory(categoryIds);
	  }
	  @PutMapping(path="/updateCategory/{categoryId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	  public ResponseEntity<Object> updateCategory(@PathVariable String categoryId, @RequestParam("category") String categoryJson, @RequestParam("file") MultipartFile file) {
	      Category category;
	      try {
	          ObjectMapper objectMapper = new ObjectMapper();
	          category = objectMapper.readValue(categoryJson, Category.class);
	      } catch (JsonProcessingException e) {
	          return new ResponseEntity<>("Invalid category JSON format", HttpStatus.BAD_REQUEST);
	      }

	      // Call the service method to update the category, ensure categoryService is an autowired instance
	      return CategoryService.updateCategory(categoryId, category, file);
	  }

}
