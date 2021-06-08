package com.listore.listore.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Entity
@Table
public class Person {
	//region Fields
	//--------------------------------------------------
	@Id
	@SequenceGenerator(
			name = "person_sequence",
			sequenceName = "person_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "person_sequence"
	)
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@OneToMany(mappedBy = "person")
	private List<Action> actions;
	//--------------------------------------------------
	//endregion
	
	public Person() {}
	
	public Person(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, Object> toMap() {
		return Map.of(
				"id", id != null ? id : "",
				"name", name != null ? name : "",
				"actions", actions != null ? actions : ""
		);
	}
}
