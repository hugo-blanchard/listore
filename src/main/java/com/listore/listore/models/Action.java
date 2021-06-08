package com.listore.listore.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

import com.listore.listore.utils.Enums;

@Entity
@Table
public class Action {
	//region Fields
	//--------------------------------------------------
	@Id
	@SequenceGenerator(
			name = "action_sequence",
			sequenceName = "action_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "action_sequence"
	)
	private Long id;
	
	private LocalDateTime date;
	
	@Enumerated(EnumType.ORDINAL)
	private Enums.ActionType type;
	
	@ManyToOne
	@JoinColumn(name = "unit_id")
	private Unit unit;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	//--------------------------------------------------
	//endregion
	
	public Action() {
	
	}
	
	public Action(Person person, Unit unit, Enums.ActionType type) {
		this.person = person;
		this.unit = unit;
		this.type = type;
		this.date = LocalDateTime.now();
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public Enums.ActionType getType() {
		return type;
	}
	
	public Map<String, Object> toMap() {
		return Map.of(
				"id", id != null ? id : "",
				"date", date != null ? date : "",
				"type", type != null ? type : "",
				"unit", unit != null ? unit : "",
				"person", person != null ? person : ""
		);
	}
}
