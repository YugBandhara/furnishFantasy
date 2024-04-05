package com.example.furnishFantasy.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.furnishFantasy.models.Product;

public class productMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setP_id(rs.getString("p_id"));
        product.setName(rs.getString("p_name"));
        product.setDescription(rs.getString("p_description"));
        product.setDiscount(rs.getBigDecimal("p_discount"));
        product.setOriginalPrice(rs.getBigDecimal("p_original_price"));
        product.setCaption(rs.getString("p_caption"));
        product.setCategoryId(rs.getString("categoryid"));
        product.setpImg(rs.getString("p_img"));
        product.setQuantity(rs.getInt("p_qty"));
        // Set other properties as needed
        return product;
    }
}