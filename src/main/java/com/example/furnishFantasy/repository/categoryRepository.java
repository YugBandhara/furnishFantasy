package com.example.furnishFantasy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.furnishFantasy.models.Category;

@Component
public  interface categoryRepository  extends CrudRepository<Category,String> {

}
