package com.listore.listore.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
@Table
public class Unit {
	//region Fields
	//--------------------------------------------------
	@Id
	@SequenceGenerator(
			name = "unit_sequence",
			sequenceName = "unit_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "unit_sequence"
	)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "utype_id")
	private Utype type;
	
	@OneToMany(mappedBy = "unit")
	private List<Action> actions;
	//--------------------------------------------------
	//endregion
	
	public Unit() {
	
	}
	
	public Unit(Utype utype) {
		this.type = utype;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setType(Utype type) {
		this.type = type;
	}
	
	public Map<String, Object> toMap() {
		return Map.of(
				"id", id != null ? id : "",
				"type", type != null ? type : "",
				"actions", actions != null ? actions : ""
		);
	}
}
