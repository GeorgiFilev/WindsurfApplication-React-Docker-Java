package com.example.springbootDemo.api;

import com.example.springbootDemo.dao.PersonRepository;
import com.example.springbootDemo.model.Person;
import com.example.springbootDemo.service.PersonService;
import org.apache.coyote.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = PersonController.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class PersonControllerRestTemplateTest {
    @Autowired
    private TestRestTemplate restTemplate;
//    @Autowired
//    private PersonController personController;
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private PersonService personService;
//    @MockBean
//    private PersonRepository personRepository;
//
//    private MockMvc mvc;

//    @Test
//    public void addPersonWithOKInput() throws Exception {
////        personController = new PersonController();
////        Person person = new Person(1,"Georgi","georgi@mail","1234");
////        RequestBuilder request = MockMvcRequestBuilders.post("/people/add");
////        MvcResult result = (MvcResult) mvc.perform(request)
////                .andExpect(content().string(""));
//        Person person = new Person(1,"Georgi","georgi@mail","1234");
//        ResponseEntity<Person> response = restTemplate.postForEntity("/people/add",person,Person.class);
//
//        assertEquals(HttpStatus.CREATED,response.getStatusCode());
//
//    }

    @Test
    void getAllPeopleTest() {
//        ResponseEntity<List<Person>> response = restTemplate.getForEntity("/people/all");

        ResponseEntity<Person[]> response = restTemplate.getForEntity("/people/all",Person[].class);
        Person[] persons = response.getBody();

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void getPersonTest() throws Exception {
        Person person = new Person(1,"Georgi","georgi@mail","1234");
        ResponseEntity<Person> response = restTemplate.postForEntity("/people/add",person,Person.class);

        ResponseEntity<Person> responseGet = restTemplate.getForEntity("/people/1",Person.class);

        assertEquals(HttpStatus.OK,responseGet.getStatusCode());
        //        RequestBuilder request = MockMvcRequestBuilders.get("/people/id");
//        MvcResult result = mvc.perform(request).andReturn();
//
//        ResponseEntity<> response = new ResponseEntity<>(result, HttpStatus.OK);
//
//        assertEquals("",result.getResponse());
    }

//    @Test
//    void updatePerson() {
////        Person person = new Person(1,"Georgi","georgi@mail","1234");
////        ResponseEntity<Person> response = restTemplate.postForEntity("/people/add",person,Person.class);
////
////        Person update = new Person(1,"Atanas","atanas@gmail.com","1234");
////        String resourceURl = "/update/admin/" + update.getId();
////       HttpEntity<Person> requestEntity = new HttpEntity<Person>()
////        ResponseEntity<Person> responseUpdate = restTemplate.put(resourceURl,);
////            .exchange(resourceURl,HttpMethod.PUT,ResponseEntity<update> ,Person.class);
//
//    }

}