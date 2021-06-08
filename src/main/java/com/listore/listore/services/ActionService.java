package com.listore.listore.services;

import com.listore.listore.models.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.listore.listore.repositories.ActionRepository;

import java.util.List;

@Service
public class ActionService {
	@Autowired
	private ActionRepository actionRepository;
	
	public List<Action> getActions() {
		return actionRepository.findAll();
	}
	
	public Action getAction(Long id) {
		return actionRepository.findById(id).orElseThrow(() -> new IllegalStateException(
				"Action with id " + id + " does not exist"
		));
	}
	
	public void addAction(Action action) {
		actionRepository.save(action);
	}
}
