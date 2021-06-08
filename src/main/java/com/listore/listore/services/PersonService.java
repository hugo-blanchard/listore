package com.listore.listore.services;

import com.listore.listore.models.Person;
import com.listore.listore.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> getPersons() {
		return personRepository.findAll();
	}
	
	public Person getPerson(Long id) {
		return personRepository.findById(id).orElseThrow(
				() -> new IllegalStateException("Person with id " + id + " does not exist")
		);
	}
	
	public Person getPersonInstance(Long id) {
		return personRepository.findById(id).orElseThrow(
				() -> new IllegalStateException("Person with id " + id + " does not exist")
		);
	}
	
	public Person addPerson(Person person) {
		return personRepository.save(person);
	}
}
