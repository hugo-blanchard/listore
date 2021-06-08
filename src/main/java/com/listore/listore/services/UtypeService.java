package com.listore.listore.services;

import com.listore.listore.repositories.UtypeRepository;
import com.listore.listore.models.Utype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtypeService {
	@Autowired
	private UtypeRepository utypeRepository;
	
	public List<Utype> getUtypes() {
		return utypeRepository.findAll();
	}
	
	public Utype getUtype(Long id) {
		return utypeRepository.findById(id).orElseThrow(() -> new IllegalStateException(
				"Unit type with id " + id + " does not exist"
		));
	}
	
	public Utype getUtypeInstance(Long id) {
		return utypeRepository.findById(id).orElseThrow(() -> new IllegalStateException(
				"Unit type with id " + id + " does not exist"
		));
	}
	
	public Utype addUtype(Utype utype) {
		return utypeRepository.save(utype);
	}
}
