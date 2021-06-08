package com.listore.listore.controllers;

import com.listore.listore.models.Utype;
import com.listore.listore.services.UtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/utype")
public class UtypeController {
	@Autowired
	private UtypeService utypeService;
	
	@GetMapping
	public List<Map<String, Object>> getUtypes() {
		List<Utype> utypes = utypeService.getUtypes();
		List<Map<String, Object>> utypeMaps = new LinkedList<>();
		
		for (Utype utype : utypes) {
			HashMap<String, Object> utypeMap = new HashMap<>(utype.toMap());
			
			// fields to remove
			utypeMap.remove("units");
			
			utypeMaps.add(utypeMap);
		}
		
		return utypeMaps;
	}
	
	@GetMapping(path = "{utypeId}")
	public Map<String, Object> getUtype(@PathVariable(name = "utypeId") Long id) {
		return utypeService.getUtype(id).toMap();
	}
	
	@PostMapping
	public Map<String, Object> addUtype(@Valid @RequestBody Utype utype) {
		return utypeService.addUtype(utype).toMap();
	}
}
