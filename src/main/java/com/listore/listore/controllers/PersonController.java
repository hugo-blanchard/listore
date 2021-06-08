package com.listore.listore.controllers;

import com.listore.listore.models.Person;
import com.listore.listore.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "listore/api/person")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@GetMapping
	public List<Map<String,Object>> getPersons() {
		List<Person> persons = personService.getPersons();
		List<Map<String, Object>> personMaps = new LinkedList<>();
		
		for (Person person : persons) {
			HashMap<String,Object> personMap = new HashMap<>(person.toMap());
			
			// fields to remove
			personMap.remove("actions");
			
			personMaps.add(personMap);
		}
		
		return personMaps;
	}
	
	@GetMapping(path = "{personId}")
	public Map<String, Object> getPerson(@PathVariable(name = "personId") Long id) {
		return personService.getPerson(id).toMap();
	}
	
	@PostMapping
	public Map<String, Object> registerPerson(@Valid @RequestBody Person person) {
		return personService.addPerson(person).toMap();
	}
}
