package com.example.springbootDemo.service;

import com.example.springbootDemo.dao.PersonJpaRepository;
import com.example.springbootDemo.dao.PersonRepository;
import com.example.springbootDemo.model.Person;
import com.example.springbootDemo.model.Post;
import com.example.springbootDemo.model.Region;
import javassist.NotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    private PersonJpaRepository personRepository;
    @Autowired
    PasswordEncoder encoder;

    public Person saveOrUpdatePerson(Person toUpdate, Person person, Region region){
        try{
//         Optional<Person> exist = personRepository.findById(person.getId());
//            if( exist.isPresent() == false){
//                return personRepository.save(person);
//            }
//            else {

                toUpdate.setUsername(person.getUsername());
                toUpdate.setPassword(encoder.encode(person.getPassword()));
                toUpdate.setEmail(person.getEmail());
                toUpdate.setRegion(region);
                return personRepository.save(toUpdate);
//            }
        }
        catch (Exception e){
            throw new InvalidDnDOperationException();
        }

        //Logic
    }
    public Person findById(long id){
        try{
            Optional<Person> person = personRepository.findById(id);
            if (person.isPresent()){
                Person exist = person.get();
                return exist;
            }
            return null;
        }
        catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }
    }
    public List<Person> getAllPeople(){
        try{
            List<Person> people = new ArrayList<>();
            personRepository.findAll().forEach(people::add);
            if(people.isEmpty()){
                return null;
            }
            return people;
        }
        catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
    }
    public int deletePerson(long id){
        try{
            Optional<Person> person = personRepository.findById(id);
            if(person.isPresent()){
                personRepository.deleteById(id);
                return 0;
            }
            else{
                return 1;
            }
        }
        catch (Exception e){
            throw new IndexOutOfBoundsException();
        }
    }
//    public Person makeAdmin(Person person){
//        person.setIsAdmin(1);
//        return person;
//    }
}
