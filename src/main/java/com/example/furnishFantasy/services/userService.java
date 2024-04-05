package com.example.furnishFantasy.services;

import com.example.furnishFantasy.repository.userRepositoryImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.furnishFantasy.handlers.ResponseHandler;
import com.example.furnishFantasy.models.User;
import com.example.furnishFantasy.repository.userRepository; 
@Service
public class userService {
	 @Autowired 
	 private  userRepository userRepo;
	 @Autowired()
	 private userRepositoryImpl userRepositoryImpl;
     public List<User> getAllUsers() {

    	 List<User> user = userRepositoryImpl.getUser();
    	
    	 return (List<User>) user;
	}
     public ResponseEntity<Object> registerUser(User user) {
    	 List<User> checkExistUser = userRepositoryImpl.authenticateUser(user);
    	 if(checkExistUser.size()==0) {
    		 userRepo.save(user);	
        	 return ResponseHandler.generateResponse("Successfully user Registered!", HttpStatus.OK,null);
    		}
    	 
    	 else {
        	 return ResponseHandler.generateResponse("Aleready exist!",HttpStatus.NOT_ACCEPTABLE,null);
    	 }
    	
	}
    public ResponseEntity<Object> loginUser(User user) {
    	 List<User> validUser = userRepositoryImpl.authenticateUser(user);
    	 if(validUser.size()==0) {
    		 return ResponseHandler.generateResponse("Enter correct username or passsword!", HttpStatus.UNAUTHORIZED,validUser);
    	 }
    	 else {
    		 return ResponseHandler.generateResponse("User Login Successfully!", HttpStatus.OK,validUser);
    	 }
    	
    			 
    }
}
