package com.listore.listore.models.requestModels;

import javax.validation.constraints.NotNull;

public class UnitRequestModel {
	@NotNull
	public long utypeId;
	
	@NotNull
	public long personId;
	
	public int amount;
	
	public UnitRequestModel() {}
	
	public UnitRequestModel(long utypeId, long personId, int amount) {
		this.utypeId = utypeId;
		this.personId = personId;
		this.amount = amount;
	}
}
