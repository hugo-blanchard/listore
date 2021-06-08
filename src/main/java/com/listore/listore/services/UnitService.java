package com.listore.listore.services;

import com.listore.listore.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.listore.listore.repositories.UnitRepository;

import java.util.List;

@Service
public class UnitService {
	@Autowired
	private UnitRepository unitRepository;
	
	public List<Unit> getUnits() {
		return unitRepository.findAll();
	}
	
	public Unit getUnit(Long id) {
		return unitRepository.findById(id).orElseThrow(
				() -> new IllegalStateException("Unit with id " + id + " does not exist")
		);
	}
	
	public Unit addUnit(Unit unit) {
		return unitRepository.save(unit);
	}
}
