package com.example.springbootDemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//@Embeddable
@Entity(name = "post")
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "post_title")
    private String title;
    @Column(name = "post_content")
    private String content;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "person_id")
//    private Person person;

    @Column(name = "person_id")
    private long personID;
    @Column(name = "author")
    private String author;

    @OneToMany
    @JoinColumn(name="post_id", referencedColumnName = "post_id")
    private List<Message> messages;

    public Post(/*@JsonProperty("postID") long id,*/@JsonProperty("title") String title,@JsonProperty("content") String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.messages = new ArrayList<>();
    }

    public Post() {

    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public long getPersonID(){
        return this.personID;
    }
    public void setPersonID(long personID){
        this.personID= personID;
//        if(!person.getPosts().contains(this)){
//            person.getPosts().add(this);
//        }
    }
    @Override
    public String toString(){
        return String.format(
                "Post[id=%d, title='%s',content='%s',author='%s']",
                id,title,content,author);
    }
    public void AddMessage(Message message)
    {
        this.messages.add(message);
    }
}
