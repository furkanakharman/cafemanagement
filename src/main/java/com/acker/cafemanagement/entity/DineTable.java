package com.acker.cafemanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DineTable {
	private Long id;
	public int tableNumber;
	public String availability;
	@ApiModelProperty(position = 1,required=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ApiModelProperty(position = 2,required=true)
	public int getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	@ApiModelProperty(position = 3,required=false)
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
}
