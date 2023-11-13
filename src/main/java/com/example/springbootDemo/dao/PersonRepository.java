package com.example.springbootDemo.dao;

import com.example.springbootDemo.model.Person;
import com.example.springbootDemo.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository <Person, Long>{


}
