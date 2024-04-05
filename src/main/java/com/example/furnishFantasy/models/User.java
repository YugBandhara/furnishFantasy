package com.example.furnishFantasy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity 
@Table(name = "user")
public class User {

@Id
@GeneratedValue(generator = "custom_userID")
@GenericGenerator(name = "custom_userID", strategy="com.example.furnishFantasy.codeGenerator.userCodeGenerator")
@Column(name = "custid")
public String custID;
@Column(name = "cust_name")
  public String custName;
@Column(name = "cust_email")
  public String custEmail;
@Column(name = "cust_password")
  public String custPassword;
public String getCustID() {
	return custID;
}
public void setCustID(String custID) {
	this.custID = custID;
}
public String getCustName() {
	return custName;
}
public void setCustName(String custName) {
	this.custName = custName;
}
public String getCustEmail() {
	return custEmail;
}
public void setCustEmail(String custEmail) {
	this.custEmail = custEmail;
}
public String getCustPassword() {
	return custPassword;
}
public void setCustPassword(String custPassword) {
	this.custPassword = custPassword;
}

}