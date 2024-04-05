package com.example.furnishFantasy.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.example.furnishFantasy.models.User;


public class userMapper implements RowMapper<User> {


	@Override
	public com.example.furnishFantasy.models.User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		 User user = new User();
//	        user.setCustID(rs.getString("custid"));
	        user.setCustName(rs.getString("cust_name"));
	        user.setCustEmail(rs.getString("cust_email"));
//	        user.setCustPassword(rs.getString("cust_password"));
	        return user;
	}
}