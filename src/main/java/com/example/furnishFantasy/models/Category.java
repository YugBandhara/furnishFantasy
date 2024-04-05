package com.example.furnishFantasy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Category")
public class Category {
	
@Id
@GeneratedValue(generator = "custom_categoryID")
@GenericGenerator(name = "custom_categoryID", strategy = "com.example.furnishFantasy.codeGenerator.categoryCodeGenerator")
@Column(name = "categoryid")
public String categoryId;
@Column(name = "cat_name")
public String categoryName;
@Column(name = "cat_description")
public String categoryDescription;
	@Lob
    @Column(name = "cat_img",columnDefinition = "MEDIUMBLOB")
	public String catImg;
	public String getCatID() {
		return categoryId;
	}
	public void setCatID(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCatName() {
		return categoryName;
	}
	public void setCatName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCatDescription() {
		return categoryDescription;
	}
	public void setCatDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public String getCatImg() {
		return catImg;
	}
	public void setCatImg(String catImg) {
		this.catImg = catImg;
	}
}
