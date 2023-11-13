package com.example.springbootDemo.service;

import com.example.springbootDemo.dao.MessageJpaRepository;
import com.example.springbootDemo.dao.PersonJpaRepository;
import com.example.springbootDemo.dao.PostRepository;
import com.example.springbootDemo.model.Message;
import com.example.springbootDemo.model.Person;
import com.example.springbootDemo.model.Post;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MessageJpaRepository messageJpaRepository;

    @Autowired
    private PersonJpaRepository personJpaRepository;

    public List<Post> getPosts(){
        try{
            List<Post> posts = new ArrayList<>();
            postRepository.findAll().forEach(posts::add);
            if(posts.isEmpty()){
                return null;
            }
            return posts;
        }
        catch (Exception e){
            throw new NoSuchElementException();
        }
    }

    public Post saveOrUpdate(Post post){
        try{
            return postRepository.save(post);
        }
        catch (Exception e){
            throw new InvalidDnDOperationException();
        }
    }

    public Post findPost(long id){
        try{
            Optional<Post> post = postRepository.findById(id);
            if(post.isPresent()){
                Post exist = post.get();
                return exist;
            }
            return null;
        }
        catch (Exception e){
            throw new InvalidDnDOperationException();
        }
    }

    public int deletePost(long id){
        try{
            Optional<Post> post = postRepository.findById(id);
            if(post.isPresent()){
                postRepository.deleteById(id);
                return 0;
            }
            return 1;
        }
        catch (Exception e){
            throw new ObjectNotFoundException("not" , " available");
        }
    }
    public List<Post> getPostsByPerson(long id){
        try{
            List<Post> newPosts = new ArrayList<>();
            List<Post> wholePosts = new ArrayList<>();

            postRepository.findAll().forEach(wholePosts::add);
            for(Post p : wholePosts){
                if(p.getPersonID()==id){
                    newPosts.add(p);
                }
            }
            return newPosts;
        }
        catch (Exception e){
            throw new InvalidDnDOperationException();
        }
    }
    public int AddMessageToPost(long id,Message message){
        try{
            Optional<Post> post = postRepository.findById(id);
            if(post.isPresent()){
//            Post toUpdate = post.get();

                Message newMessage = new Message(message.getText(),post.get().getId(),message.getPersonID());
                Optional<Person> person = personJpaRepository.findById(newMessage.getPersonID());
                if(person.isPresent()){
                    newMessage.setAuthor(person.get().getUsername());
                    messageJpaRepository.save(newMessage);
                    return 0;
                }
              return 1;
            }
            else {
                return 1;
            }

        }
        catch (Exception e){
            throw new IndexOutOfBoundsException();
        }

    }
    public List<Message> getMessagesForPost(long id){
        try{
            List<Message> postMessages = new ArrayList<>();
            List<Message> allMessages = new ArrayList<>();
//            Optional<Post> post = postRepository.findById(id);
//            Post existingPost = post.get();
            messageJpaRepository.findAll().forEach(allMessages::add);
            for (Message message :
                    allMessages) {
                if(message.getPostID()==id){
                    postMessages.add(message);
                }
            }

            return postMessages;
        }
        catch (Exception e){
            throw new IndexOutOfBoundsException();
        }
    }

}
