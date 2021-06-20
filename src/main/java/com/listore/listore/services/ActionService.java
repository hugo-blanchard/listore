package com.listore.listore.services;

import com.listore.listore.models.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.listore.listore.repositories.ActionRepository;

import java.util.List;

@Service
public class ActionService {
	@Autowired
	private ActionRepository actionRepository;
	
	public List<Action> getActions(int skipPage) {
		return actionRepository.findAll(
				PageRequest.of(skipPage, 100, Sort.by("date"))
		).getContent();
	}
	
	public Action getAction(Long id) {
		return actionRepository.findById(id).orElseThrow(() -> new IllegalStateException(
				"Action with id " + id + " does not exist"
		));
	}
	
	public Action addAction(Action action) {
		return actionRepository.save(action);
	}
}
