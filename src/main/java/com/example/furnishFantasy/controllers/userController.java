package com.example.furnishFantasy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.furnishFantasy.models.User;
import com.example.furnishFantasy.services.userService;

@Controller // This means that this class is a Controller
@CrossOrigin(origins = "*")
@RequestMapping(path="/furnishFantasy/user") 	
public class userController {
  @Autowired
  private userService userService;
  @PostMapping(path="/register",consumes = "application/json") // Map ONLY POST Requests
  public @ResponseBody ResponseEntity<Object> addNewUser (@RequestBody (required=false)User user) {

return userService.registerUser(user);
  }
  @GetMapping(path="/getUsers")
  public @ResponseBody List<User> getAllUsers() {
	return userService.getAllUsers();
  }
  @PostMapping(path="/login",consumes = "application/json") // Map ONLY POST Requests
  public @ResponseBody ResponseEntity<Object> loginUser (@RequestBody (required=false)User user) {
	  return userService.loginUser(user);
  }

}
