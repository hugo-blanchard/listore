package com.listore.listore.controllers;

import com.listore.listore.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "listore/api/action")
public class ActionController {
	@Autowired
	private ActionService actionService;
	
	@GetMapping
	public Map<String, Object> getActions(@RequestParam(value = "skipPage", defaultValue = "0") int skipPage) {
		try {
			List<Map<String, Object>> actionMaps = new ArrayList<>();
			
			actionService.getActions(skipPage).forEach(action -> {
				actionMaps.add(action.toMap(0));
			});
			
			return Map.of("actions", actionMaps);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
	
	@GetMapping(path = "{actionId}")
	public Map<String, Object> getAction(
			@PathVariable(name = "actionId") Long id,
			@RequestParam(value = "deep", defaultValue = "false") boolean deep
	) {
		try {
			return actionService.getAction(id).toMap(deep ? 1 : 0);
		} catch (Exception error) {
			return Map.of("error", error.getMessage());
		}
	}
}
