package com.listore.listore.controllers;

import com.listore.listore.models.Action;
import com.listore.listore.models.Person;
import com.listore.listore.models.Unit;
import com.listore.listore.models.Utype;
import com.listore.listore.models.requestModels.UnitRequestModel;
import com.listore.listore.services.ActionService;
import com.listore.listore.services.PersonService;
import com.listore.listore.services.UnitService;
import com.listore.listore.services.UtypeService;
import com.listore.listore.utils.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "listore/api/unit")
public class UnitController extends BaseController {
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private UtypeService utypeService;
	
	@Autowired
	private ActionService actionService;
	
	@Autowired
	private PersonService personService;
	
	@GetMapping
	public List<Map<String,Object>> getUnits() {
		List<Unit> units = unitService.getUnits();
		List<Map<String, Object>> unitMaps = new LinkedList<>();
		
		for (Unit unit : units) {
			HashMap<String,Object> unitMap = new HashMap<>(unit.toMap());
			
			// fields to remove
			unitMap.remove("actions");
			
			unitMaps.add(unitMap);
		}
		
		return unitMaps;
	}
	
	@GetMapping(path = "{unitId}")
	public Map<String, Object> getUnit(@PathVariable(name = "unitId") Long id) {
		return unitService.getUnit(id).toMap();
	}
	
	@PostMapping
	public Map<String, Object> addUnit(@Valid @RequestBody UnitRequestModel unitRequestModel) {
		// find the corresponding DB entries from the request body
		final Utype utype = utypeService.getUtypeInstance(unitRequestModel.utypeId);
		final Person person = personService.getPersonInstance(unitRequestModel.personId);
		
		// save the unit
		final Unit unit = new Unit(utype);
		final Unit savedUnit = unitService.addUnit(unit);
		
		// save the related action and delete the previously saved unit if it fails
		try {
			final Action action = new Action(person, savedUnit, Enums.ActionType.IN);
			actionService.addAction(action);
		} catch (Exception exception) {
			// delete the previously saved unit
		}

		return savedUnit.toMap();
	}
}
