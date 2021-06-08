package com.listore.listore.controllers;


import com.listore.listore.models.Action;
import com.listore.listore.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "listore/api/action")
public class ActionController {
	@Autowired
	private ActionService actionService;
	
	@GetMapping
	public List<Map<String, Object>> getActions() {
		List<Action> actions = actionService.getActions();
		List<Map<String, Object>> actionMaps = new LinkedList<>();
		
		for (Action action : actions) {
			HashMap<String, Object> actionMap = new HashMap<>(action.toMap());
			
			// fields to remove
			actionMap.remove("person");
			
			actionMaps.add(actionMap);
		}
		
		return actionMaps;
	}
	
	@GetMapping(path = "{actionId}")
	public Map<String, Object> getAction(@PathVariable(name = "actionId") Long id) {
		return actionService.getAction(id).toMap();
	}
}
