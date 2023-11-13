package com.example.springbootDemo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void TestCreatePersonWithAppropriateValues(){
        Person person = new Person(1,"Georgi","georgi@mail","1234");
        String name = person.getUsername();

        //Assert
        assertEquals("Georgi",name);
    }

    @Test
    void TestAddPostToPerson(){
        List<Post> postList = new ArrayList<>();
        Person person = new Person(1,"Georgi","georgi@mail","1234");
        Post post = new Post("Title","Dummy content");
        post.setPersonID(person.getId());
        post.setAuthor(person.getUsername());
        String name = person.getUsername();
        person.addPost(post);
        postList.add(post);
        //Assert
        assertEquals(person.getPosts(),postList);
    }

}