package com.example.springbootDemo.dao;

import com.example.springbootDemo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageJpaRepository extends JpaRepository<Message,Long> {

}
