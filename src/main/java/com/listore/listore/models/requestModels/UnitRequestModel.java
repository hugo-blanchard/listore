package com.listore.listore.models.requestModels;

import javax.validation.constraints.NotNull;

public class UnitRequestModel {
	@NotNull
	public Long utypeId;
	
	@NotNull
	public Long personId;
	
	public UnitRequestModel() {}
	
	public UnitRequestModel(Long utypeId, Long personId) {
		this.utypeId = utypeId;
		this.personId = personId;
	}
}
