package com.example.springbootDemo.api;


import com.example.springbootDemo.dao.PostRepository;
import com.example.springbootDemo.model.Message;
import com.example.springbootDemo.model.Person;
import com.example.springbootDemo.model.Post;
import com.example.springbootDemo.service.PostService;
import com.sun.istack.NotNull;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("posts")
@RestController
@CrossOrigin(origins = {"*","*"})
public class PostController {

//    @Autowired
//    private PostRepository rep;

    @Autowired
    private PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts(){
        try {
            List<Post> posts = postService.getPosts();
            if(posts == null){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(posts,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") long id){
            Post post = postService.findPost(id);
            if(post !=null){
                return new ResponseEntity<>(post,HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Post> addPost(@NonNull @RequestBody Post post){
        try{
            postService.saveOrUpdate(post);
            return new ResponseEntity<>(post,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("id")long id){

        if(postService.deletePost(id)==0 ){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("update/post/{id}")
    public ResponseEntity<HttpStatus> updatePost(@PathVariable("id")long id,@RequestBody Post post){
        Post exist = postService.findPost(id);
        if (exist != null) {
            postService.saveOrUpdate(exist);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("personPosts/{id}")
    public ResponseEntity<List<Post>> getPersonPosts(@PathVariable("id") long id) {
        List<Post> posts = postService.getPostsByPerson(id);
        if (posts != null) {
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("getMessages/{id}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable("id")long id){
        List<Message> messages = postService.getMessagesForPost(id);
        if(messages != null){
            return new ResponseEntity<>(messages,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping("addMessage/{id}")
    public ResponseEntity<Post> addMessage(@PathVariable("id")long id,@RequestBody Message message){
        Post post =postService.findPost(id);
        if(post != null){
            if(postService.AddMessageToPost(id,message)==0){
                return new ResponseEntity<>(post,HttpStatus.OK);
            }
        }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
