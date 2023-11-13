package com.example.springbootDemo.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="person_id")
    private long id;

    @Column(name= "person_name")
    @Size(max = 30)
    private String username;

    @Column(name ="person_email")
    @Size(max=50)
    @Email
    private String email;

    @Column(name ="person_password")
    @Size(max=120)
    private String password;

//    @Column(name = "person_is_Admin")
//    private int isAdmin;

    //    @ElementCollection
//    @CollectionTable(
//            name="Post",
//            joinColumns = @JoinColumn(name="person_id")
//    )
//    @OneToMany
//    @JoinTable(name="Person_Post",joinColumns = {@JoinColumn(name="person_id",referencedColumnName = "Person_Post")},
//    inverseJoinColumns ={@JoinColumn(name = "person_id",referencedColumnName = "ID",unique = true)}
//    )
//    @OneToMany(mappedBy = "person")

    @Column(name = "region")
    private Region region;

    @OneToMany
    @JoinColumn(name="person_id", referencedColumnName="person_id")
    private List<Post> posts ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Person(@JsonProperty("id") long id, @JsonProperty("name") String name, @JsonProperty("email") String email,@JsonProperty("password") String password) {
        this.id = id;
        this.username = name;
        this.email = email;
        this.password = password;
//        this.isAdmin = 0;
        this.posts = new ArrayList<>();
        this.region = Region.NONE;
    }
    public Person(String name, String email, String password) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    public Person() {

    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

//    public int getIsAdmin() {
//        return isAdmin;
//    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setRegion(Region region) {
        this.region = region;
    }

    public Region getRegion() {
        return region;
    }

    //    public void setIsAdmin(int isAdmin) {
//        this.isAdmin = isAdmin;
//    }
    @Override
    public String toString(){
        return "Person[id="+this.id+" Name:"+ username+ " email:"+email+" password:"+password+"]";
    }
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public void addPost(Post post){
        //this.posts.add((new Post(person,title,content)));
        this.posts.add(post);
//         if (post.getPerson()!=this){
//             post.setPerson(this);
//         }
    }
    public Set<Role> getRoles(){
        return roles;
    }
    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }
}
