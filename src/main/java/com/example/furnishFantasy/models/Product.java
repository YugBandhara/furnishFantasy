package com.example.furnishFantasy.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(generator = "custom_productID")
	@GenericGenerator(name = "custom_productID", strategy = "com.example.furnishFantasy.codeGenerator.productCodeGenerator")
	@Column(name = "p_id") 
	public String p_id;
	@Column(name = "p_name")
	    public String name;
	@Column(name = "p_description")
	    public String description;
	@Column(name = "p_qty")
	    public int quantity;
	@Column(name = "p_discount")
	    public BigDecimal discount;
	@Column(name = "p_original_price")
	    public BigDecimal originalPrice;
	@Column(name = "p_caption")
	    public String caption;
	@Column(name = "categoryid")
	    public String categoryId;
	@Lob
    @Column(name = "p_img",columnDefinition = "MEDIUMBLOB")
	public String pImg;
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getpImg() {
		return pImg;
	}
	public void setpImg(String pImg) {
		this.pImg = pImg;
	}
	    

}
