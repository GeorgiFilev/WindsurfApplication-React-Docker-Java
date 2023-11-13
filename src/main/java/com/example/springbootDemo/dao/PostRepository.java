package com.example.springbootDemo.dao;

import com.example.springbootDemo.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Long> {
}
