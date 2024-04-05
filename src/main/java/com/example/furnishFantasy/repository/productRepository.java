package com.example.furnishFantasy.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.furnishFantasy.models.Product;
@Component
public  interface productRepository  extends CrudRepository<Product,String> {

}
