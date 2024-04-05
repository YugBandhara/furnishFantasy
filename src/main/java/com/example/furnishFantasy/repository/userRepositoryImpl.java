package com.example.furnishFantasy.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.example.furnishFantasy.models.User;
import com.example.furnishFantasy.mapper.userMapper;




@Repository
public class userRepositoryImpl {
	@Autowired
//	@Qualifier(value="mysqlJdbcTemplate")
	JdbcTemplate jdbcTemplate;
	public List<User> getUser(){
		
		String query = "SELECT cust_name,cust_email FROM furnishFantasy.user";
		List<User> user =jdbcTemplate.query(query, new userMapper());
		return user;
	}
	public List<User> authenticateUser(User user) {
		 String query = "SELECT cust_name,cust_email FROM furnishFantasy.user WHERE cust_email = ? AND cust_password = ?";
		 List<User> users = jdbcTemplate.query(query, new userMapper(), user.getCustEmail(), user.getCustPassword());
	        return users;
	}
	

}