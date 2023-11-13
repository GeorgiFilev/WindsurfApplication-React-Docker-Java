package com.example.springbootDemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "Message")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "post_id")
    private long postID;

    @Column(name = "text")
    private String text;

    @Column(name = "person_id")
    private long personID;

    @Column(name="author")
    private String author;

    public Message(@JsonProperty("text")String text,@JsonProperty("post_id")long postID,@JsonProperty("person_id") long personID){
        this.text = text;
        this.postID=postID;
        this.personID=personID;
    }

    public Message() {

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public void setPersonID(long personID) {
        this.personID = personID;
    }

    public long getId() {
        return id;
    }

    public long getPostID() {
        return postID;
    }

    public String getText() {
        return text;
    }

    public long getPersonID() {
        return personID;
    }

    public String toString(){
        return this.author + ": " +this.text;
    }
}
