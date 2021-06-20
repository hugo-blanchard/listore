package com.listore.listore.services;

import com.listore.listore.repositories.UtypeRepository;
import com.listore.listore.models.Utype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtypeService {
	@Autowired
	private UtypeRepository utypeRepository;
	
	public List<Utype> getUtypes(int skipPage) {
		return utypeRepository.findAll(
				PageRequest.of(skipPage, 100, Sort.by("name"))
		).getContent();
	}
	
	public Utype getUtype(long id) {
		return utypeRepository.findById(id).orElseThrow(
				() -> new IllegalStateException("Unit type with id " + id + " does not exist")
		);
	}
	
	public Utype addUtype(Utype utype) {
		if (utypeRepository.existsByName(utype.getName())) {
			throw new IllegalStateException("A unit type with this name already exists");
		}
		return utypeRepository.save(utype);
	}
	
	public Utype updateUtype(long id, Utype newUtype) {
		if (utypeRepository.existsByName(newUtype.getName())) {
			throw new IllegalStateException("A unit type with this name already exists");
		}
		
		Utype utype = utypeRepository.findById(id).orElseThrow(
				() -> new IllegalStateException("Unit type with id " + id + " does not exist")
		);
		
		utype.setName(newUtype.getName());
		
		return utypeRepository.save(utype);
	}
}
