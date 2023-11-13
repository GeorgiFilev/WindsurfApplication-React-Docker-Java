//package com.example.springbootDemo.dao;
//
//import com.example.springbootDemo.model.Person;
//import com.example.springbootDemo.model.Post;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//public interface PersonDao {
//    int insertPerson(long id, Person person);
//    default int insertPerson(Person person){
//        UUID id = UUID.randomUUID();
//        return  insertPerson(id,person);
//    }
//    Person selectPersonByID(UUID id);
//    List<Person> selectAllPeople();
//    int deletePersonByID(UUID id);
//    int updatePerson(UUID id,Person person);
//    int createPost(UUID personID, Post post);
//    Post selectPost(UUID personID,UUID postID);
//}
