package com.example.furnishFantasy.repository;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

import com.example.furnishFantasy.models.User;

@Component
//@RepositoryRestResource(excerptProjection = User.class)
public interface userRepository extends CrudRepository<User,String> {



}
