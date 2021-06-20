package com.listore.listore.controllers;

import com.listore.listore.models.Person;
import com.listore.listore.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "listore/api/person")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@GetMapping
	public Map<String,Object> getPersons(@RequestParam(value = "skipPage", defaultValue = "0") int skipPage) {
		try {
			List<Map<String, Object>> personMaps = new ArrayList<>();
			
			personService.getPersons(skipPage).forEach(person -> {
				personMaps.add(person.toMap(0));
			});
			
			return Map.of("persons", personMaps);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
	
	@GetMapping(path = "{personId}")
	public Map<String, Object> getPerson(
			@PathVariable(name = "personId") Long id,
			@RequestParam(value = "deep", defaultValue = "false") boolean deep
	) {
		try {
			return personService.getPerson(id).toMap(deep ? 1 : 0);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
	
	@PostMapping
	public Map<String, Object> registerPerson(@Valid @RequestBody Person person) {
		try {
			return personService.addPerson(person).toMap(0);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
	
	@PutMapping(path = "{personId}")
	public Map<String, Object> updatePerson(
			@PathVariable(name = "personId") Long id,
			@Valid @RequestBody Person requestPerson
	) {
		try {
			return personService.updatePerson(id, requestPerson).toMap(0);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
}
