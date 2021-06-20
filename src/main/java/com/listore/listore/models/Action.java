package com.listore.listore.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
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
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "unit_action_join",
			joinColumns = @JoinColumn(name = "action_id"),
			inverseJoinColumns = @JoinColumn(name = "unit_id")
	)
	private List<Unit> units;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	//--------------------------------------------------
	//endregion
	
	public Action() {
	
	}
	
	public Action(Person person, List<Unit> units, Enums.ActionType type) {
		this.person = person;
		this.units = units;
		this.type = type;
		this.date = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public Enums.ActionType getType() {
		return type;
	}
	
	public Map<String, Object> toMap(int depth) {
		return depth > 0
				? Map.of(
						"id", id != null ? id : "",
						"date", date != null ? date : "",
						"type", type != null ? type : "",
						"units", units != null ? units : "",
						"person", person != null ? person : ""
				)
				: Map.of(
						"id", id != null ? id : "",
						"date", date != null ? date : "",
						"type", type != null ? type : ""
				);
	}
}
