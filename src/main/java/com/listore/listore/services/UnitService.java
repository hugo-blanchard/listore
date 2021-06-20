package com.listore.listore.services;

import com.listore.listore.models.Person;
import com.listore.listore.models.Unit;
import com.listore.listore.models.Utype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.listore.listore.repositories.UnitRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {
	@Autowired
	private UnitRepository unitRepository;
	
	public List<Unit> getUnits(int skipPage) {
		return unitRepository.findAll(
				PageRequest.of(skipPage, 100)
		).getContent();
	}
	
	public Unit getUnit(Long id) {
		return unitRepository.findById(id).orElseThrow(
				() -> new IllegalStateException("Unit with id " + id + " does not exist")
		);
	}
	
	public List<Unit> saveUnits(Utype utype, int amount) {
		List<Unit> unitList = new ArrayList<>();
		
		for (int i = 0; i < amount; i++) {
			unitList.add(unitRepository.save(new Unit(utype)));
		}
		
		return unitList;
	}
}
