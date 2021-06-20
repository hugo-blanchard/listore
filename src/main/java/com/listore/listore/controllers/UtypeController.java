package com.listore.listore.controllers;

import com.listore.listore.models.Utype;
import com.listore.listore.services.UtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "listore/api/utype")
public class UtypeController {
	@Autowired
	private UtypeService utypeService;
	
	@GetMapping
	public Map<String, Object> getUtypes(
			@RequestParam(value = "skipPage", defaultValue = "0") int skipPage,
			@RequestParam(value = "deep", defaultValue = "false") boolean deep
	) {
		try {
			List<Map<String, Object>> utypeMaps = new ArrayList<>();
			
			utypeService.getUtypes(skipPage).forEach(utype -> {
				utypeMaps.add(utype.toMap(deep ? 1 : 0));
			});
			
			return Map.of("unitTypes", utypeMaps);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
	
	@GetMapping(path = "{utypeId}")
	public Map<String, Object> getUtype(
			@PathVariable(name = "utypeId") long id,
			@RequestParam(value = "deep", defaultValue = "false") boolean deep
	) {
		try {
			return utypeService.getUtype(id).toMap(deep ? 2 : 1);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
	
	@PostMapping
	public Map<String, Object> addUtype(@Valid @RequestBody Utype utype) {
		try {
			return utypeService.addUtype(utype).toMap(0);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
	
	@PutMapping(path = "{utypeId}")
	public Map<String, Object> updateUtype(
			@PathVariable(name = "utypeId") Long id,
			@Valid @RequestBody Utype utype
	) {
		try {
			return utypeService.updateUtype(id, utype).toMap(0);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
}
