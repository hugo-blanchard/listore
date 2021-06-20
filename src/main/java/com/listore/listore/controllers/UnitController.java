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
import java.util.*;

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
	public Map<String,Object> getUnits(@RequestParam(value = "skipPage", defaultValue = "0") int skipPage) {
		try {
			List<Map<String, Object>> unitMaps = new ArrayList<>();
			
			unitService.getUnits(skipPage).forEach(unit -> {
				unitMaps.add(unit.toMap(0));
			});
			
			return Map.of("units", unitMaps);
		} catch (Exception error) {
			return Map.of("error", error);
		}
	}
	
	@GetMapping(path = "{unitId}")
	public Map<String, Object> getUnit(
			@PathVariable(name = "unitId") Long id,
			@RequestParam(value = "deep", defaultValue = "false") boolean deep
	) {
		try {
			return unitService.getUnit(id).toMap(deep ? 1 : 0);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
	
	@PostMapping
	public Map<String, Object> addUnit(@Valid @RequestBody UnitRequestModel unitRequestModel) {
		try {
			final Utype utype = utypeService.getUtype(unitRequestModel.utypeId);
			final Person person = personService.getPerson(unitRequestModel.personId);
			
			final List<Unit> unitList = unitService.saveUnits(utype, unitRequestModel.amount);
			
			// TODO : potential issue here, since at that point the units have already been saved, if saving the action fails, it won't stop anything from working, but the action will be missing, this might need to be handled
			final Action action = actionService.addAction(new Action(person, unitList, Enums.ActionType.IN));
			
			List<Long> unitIdList = new ArrayList<>();
			unitList.forEach(unit -> unitIdList.add(unit.getId()));
			
			return Map.of("action", action, "savedUnitsIds", unitIdList);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
}
