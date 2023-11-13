package com.example.springbootDemo.api;

import com.example.springbootDemo.model.Person;
import com.example.springbootDemo.model.Post;
import com.example.springbootDemo.service.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    static PersonService personService;

    @BeforeAll
    static void SetPersonData(){
        personService = mock(PersonService.class);

        List<Person> people = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        Person person = new Person(1,"Atanas","atanas@gmail.com","1234");
        people.add(person);
        people.add(new Person(2,"Georgi","georgi@gmail.com","1234"));
        Post post = new Post("title","content");
        post.setPersonID(person.getId());
        post.setAuthor(person.getUsername());
        posts.add(post);
        person.addPost(post);


        Post post2 = new Post("title N 2","not any more");
        post2.setPersonID(person.getId());
        post2.setAuthor(person.getUsername());
        posts.add(post);
        person.addPost(post2);

        when(personService.getAllPeople()).thenReturn(people);
        when(personService.findById(person.getId())).thenReturn(person);
        when(personService.deletePerson(person.getId())).thenReturn(0);
//        when(personService.makeAdmin(person))
//                .thenReturn(person);
    }


    @Test
    void addPerson() {
        //Arrange
        Person person = new Person(3,"Stefan","stefan@gamil.com","1234");
        //Act
        List<Person> people = personService.getAllPeople();
        people.add(person);
        //Assert
        assertEquals( "Person[id=3 Name:Stefan email:stefan@gamil.com password:1234]",people.get(2).toString());
        assertEquals("Stefan",people.get(2).getUsername());
    }

    @Test
    void getAllPerson() {
        List<Person> people = personService.getAllPeople();

        assertEquals(  "Atanas",people.get(0).getUsername());
        assertEquals( "Georgi",people.get(1).getUsername());

    }

    @Test
    void getPersonWithId1() {
       Person atanas = personService.findById(1);

       assertEquals("Atanas",atanas.getUsername());
       assertEquals("1234",atanas.getPassword());
    }

//    @Test
//    void updatePersonWithId1() {
//        Person atanas = personService.findById(1);
//        atanas.setPassword("atanas1234");
//        personService.saveOrUpdatePerson(atanas,atanas);
//        List<Person> people = personService.getAllPeople();
//        people.get((int) atanas.getId()).setPassword(atanas.getPassword());
//        assertEquals("atanas1234",people.get(1).getPassword());
//    }

    @Test
    void deletePerson() {
        List<Person> people = personService.getAllPeople();
        personService.deletePerson(1);
        people.remove(personService.findById(1).getId());
        assertNotEquals(1,people.get(1).getId());

    }

//    @Test
//    void makeAdminPersonWithId1() {
//       Person person= personService.findById(1);
//        person.setIsAdmin(1);
//        assertEquals(1,person.getIsAdmin());
//    }

    @Test
    void addPostToAtanas() {
        List<Person> people = personService.getAllPeople();
        Person person = personService.findById(1);
        person.addPost(new Post("titlE","content"));

        assertEquals("titlE",person.getPosts().get(2).getTitle());
    }
}