package com.example.springbootDemo.api;

import com.example.springbootDemo.dao.PostRepository;
import com.example.springbootDemo.model.Person;
import com.example.springbootDemo.model.Post;
import com.example.springbootDemo.service.PersonService;
import com.example.springbootDemo.service.PostService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

//    PostRepository postRepository;
    static PostService postService;
    @BeforeAll
    static void SetUpPost()
    {
//        postRepository = mock(PostRepository.class);
            postService = mock(PostService.class);

        List<Person> people = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        Person person = new Person(1,"Atanas","atanas@gmail.com","1234");
        people.add(person);
        people.add(new Person(2,"Georgi","georgi@gmail.com","1234"));

        Post post = new Post("title","content");
        post.setPersonID(person.getId());
        post.setAuthor(person.getUsername());
        posts.add(post);
        post.setId(1);
        person.addPost(post);

        Post post2 = new Post("title N 2","not any more");
        post2.setPersonID(person.getId());
        post2.setAuthor(person.getUsername());
        post2.setId(2);
        posts.add(post2);

        person.addPost(post2);

        when(postService.getPosts()).thenReturn(posts);
        when(postService.getPostsByPerson(person.getId())).thenReturn(person.getPosts());
        when(postService.findPost(post.getId())).thenReturn(post);
    }

    @Test
    void testGetAllPostsCount(){
        //arrange (from constructor)

        //act
         List<Post> posts = postService.getPosts();
        //assert
        assertEquals( "title",posts.get(0).getTitle());
    }

    @Test
    void testGetPostTitlesForPerson1(){
        List<Post> posts = postService.getPostsByPerson(1);
        assertEquals("title",posts.get(0).getTitle());
        assertEquals("title N 2",posts.get(1).getTitle());
    }
//    private MockMvc mvc;
//
////    @MockBean
////    private PostService postService;
//    @MockBean
//    private PostRepository postRepository;

    
    @Test
    void getPostWithId2Test() {
        List<Post> posts = postService.getPosts();
        Post post = postService.findPost(2);
        assertEquals("title N 2",posts.get(1).getTitle());

    }
}