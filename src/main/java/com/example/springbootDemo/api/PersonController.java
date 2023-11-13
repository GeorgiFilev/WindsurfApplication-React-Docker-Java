package com.example.springbootDemo.api;

import ch.qos.logback.core.net.server.Client;
import com.example.springbootDemo.dao.PersonRepository;
import com.example.springbootDemo.dao.PostRepository;
import com.example.springbootDemo.model.Person;
//import com.example.springbootDemo.model.Post;
//import com.example.springbootDemo.service.PersonService;
import com.example.springbootDemo.model.Post;
import com.example.springbootDemo.model.Region;
import com.example.springbootDemo.service.PersonService;
import com.example.springbootDemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("people")
@RestController
@Controller
@CrossOrigin(origins = {"*","*"})
public class PersonController {
//    @Autowired
//    private PersonRepository rep;
//    @Autowired
//    private PostRepository postrep;
    @Autowired
    private PersonService personService;
    @Autowired
    private PostService postService;

//    @PostMapping(path = "/add")
//    public ResponseEntity<Person> addPerson(@NonNull @RequestBody Person person){             // public ResponseEntity
//
//           if( personService.saveOrUpdatePerson(person) != null){
//               return new ResponseEntity<>(person, HttpStatus.CREATED);
//           }
//
//
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
////        try{
////            rep.save(person);
////            return new ResponseEntity<>(person, HttpStatus.CREATED);
////        }
////        catch (Exception e){
////                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
//    }


    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPerson(){

            List<Person> persons = personService.getAllPeople();
        if(persons != null){
            return new ResponseEntity<>(persons,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
        public ResponseEntity<Person> getPerson(@PathVariable("id") long id){
            Person person = personService.findById(id);
            if (person != null) {
                return new ResponseEntity<>(person, HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person, @RequestBody Region region){
            Person someone = personService.findById(id);
            if(someone != null){
                personService.saveOrUpdatePerson(someone,person,region);
                return new ResponseEntity<>(HttpStatus.OK);
            }
//            Optional<Person> exist = rep.findById(id);
//            if (exist.isPresent()) {
//                Person update = exist.get();
//                update.setName(person.getName());
//                update.setEmail(person.getEmail());
//                update.setPassword(person.getPassword());
//                rep.save(update);
//                return new ResponseEntity<>(update, HttpStatus.OK);
//            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id){
        if(personService.deletePerson(id) == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
//    @PutMapping("/update/admin/{id}")
//    public ResponseEntity<Person> makeAdmin(@PathVariable("id") long id){
//
//            Person person = personService.findById(id);
//            if(person != null){
//                personService.makeAdmin(person);
//                return new ResponseEntity<>(person,HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
    @PostMapping(path = "/addPost/{id}")
    public ResponseEntity<Post> addPost(@PathVariable("id") long id,@NonNull @RequestBody Post post){             // public ResponseEntity
            Person person = personService.findById(id);
            if(person != null){
                Post p = new Post(post.getTitle(),post.getContent());
                p.setPersonID(id);
                p.setAuthor(person.getUsername());
                postService.saveOrUpdate(p);
//            Optional<Person> person = rep.findById(id);
//            if (person.isPresent()){
//                Person newPerson = person.get();
//                Post p = new Post(post.getTitle(),post.getContent());
//                p.setPersonID(id);
//                p.setAuthor(newPerson.getName());
//                postrep.save(p);
                return new ResponseEntity<>(p, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping("personPosts/{id}")
//    public ResponseEntity<List<Post>> getPersonPosts(@PathVariable("id") long id){
//        try{
//            List<Post> newList = new ArrayList<>();
//            List<Post> posts = new ArrayList<>();
////            Optional<Person> person = rep.findById(id);
////            List<Post> posts = person.get().getPosts();
//            postrep.findAll().forEach(posts::add);
//            for (Post p :
//                    posts) {
//                if (p.getPerson().getId() == id) {
//                    newList.add(p);
//                }
//            }
//
//            return new ResponseEntity<>(newList, HttpStatus.OK);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    private final PersonService personService;
//
//    @Autowired
//    public PersonController(PersonService personService) {
//        this.personService = personService;
//    }
//
//    @PostMapping
//    public void addPerson(@NonNull @RequestBody Person person){
//        personService.addPerson(person);
//    }
//    @GetMapping
//    public List<Person> getAllPeople(){
//        return personService.getAllPeople();
//    }
//
//    @GetMapping(path ="{id}")
//    public String getPersonByID(@PathVariable("id") long id){
//        return personService.getPersonByID(id);
//    }
//    @DeleteMapping(path = "{id}")
//    public void deletePersonByID(@PathVariable("id") long id){
//        personService.deletePerson(id);
//    }
//    @PutMapping(path = "{id}")
//    public void updatePerson(@PathVariable("id")long id,@Validated @NonNull @RequestBody Person person){
//        personService.updatePerson(id,person);
//    }

//    @PostMapping(path ="{id}/posts")
//    public void addPost(@PathVariable("id") long personID,@NonNull @RequestBody Post post){
//        personService.createPost(personID,post);
//       System.out.println(post);
//   }
}
