package com.listore.listore.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
@Table
public class Utype {
	//region Fields
	//--------------------------------------------------
	@Id
	@SequenceGenerator(
			name = "utype_sequence",
			sequenceName = "Utype_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "utype_sequence"
	)
	private Long id;
	
	@NotNull
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
	private List<Unit> units;
	//--------------------------------------------------
	//endregion
	
	public Utype() {
	
	}
	
	public Utype(String name) {
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
				"units", units != null ? units : ""
		);
	}
}
