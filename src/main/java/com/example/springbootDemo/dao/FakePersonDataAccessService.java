//package com.example.springbootDemo.dao;
//
//import com.example.springbootDemo.model.Person;
//import com.example.springbootDemo.model.Post;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository("fakeDao")
//public class FakePersonDataAccessService implements PersonDao {
//    private  static List<Person> people =new ArrayList<>();
//
//    @Override
//    public int insertPerson(long id, Person person) {
//        people.add(new Person(id, person.getName(), person.getEmail(), person.getPassword()));
//        return 1;
//    }
//
//    @Override
//    public Person selectPersonByID(long id) {
//        for (Person person :
//                people) {
//            if(person.getId().equals(id)){
//                return person;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<Person> selectAllPeople() {
//        return people;
//    }
//
//    @Override
//    public int deletePersonByID(UUID id) {
//       Person person = selectPersonByID(id);
//       if(person==null){
//           return 0;
//       }
//           people.remove(person);
//       return 1;
//    }
//
//    @Override
//    public int updatePerson(UUID id, Person person) {
//        List<Post> list = new ArrayList<>();
//
//        for (int i=0;i<people.size();i++){
//            if(people.get(i).getId().equals(id)){
//                list.addAll(people.get(i).getPosts());
//                people.set(i,new Person(id,person.getName(),person.getEmail(),person.getPassword()));
//                people.get(i).setPosts(list);
//                return 1;
//            }
//        }
//        return 0;
//    }
//
//    @Override
//    public int createPost(UUID personID, Post post) {
//        if (selectPersonByID(personID) == null){
//            return 0;
//        }
//        System.out.println("nice");
//        selectPersonByID(personID).getPosts().add(new Post(UUID.randomUUID(),post.getTitle(),post.getContent()));
//        return 1;
//    }
//
//    @Override
//    public Post selectPost(UUID personID,UUID postID) {
//        for (Post post:
//                selectPersonByID(personID).getPosts()
//             ) {
//                if (post.getId()==postID){
//                    return post;
//                }
//        }
//        return null;
//    }
//}
