package com.listore.listore.services;

import com.listore.listore.models.Person;
import com.listore.listore.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> getPersons(int skipPage) {
		return personRepository.findAll(
				PageRequest.of(skipPage, 100, Sort.by("name"))
		).getContent();
	}
	
	public Person getPerson(long id) {
		return personRepository.findById(id).orElseThrow(
				() -> new IllegalStateException("Person with id " + id + " does not exist")
		);
	}
	
	public Person addPerson(Person person) {
		if (personRepository.existsByName(person.getName())) {
			throw new IllegalStateException("A person with this name already exists");
		}
		return personRepository.save(person);
	}
	
	public Person updatePerson(long id, Person newPerson) {
		if (personRepository.existsByName(newPerson.getName())) {
			throw new IllegalStateException("A person with this name already exists");
		}
		
		Person person = personRepository.findById(id).orElseThrow(
				() -> new IllegalStateException("Person with id " + id + " does not exist")
		);
		
		person.setName(newPerson.getName());
		
		return personRepository.save(person);
	}
}
