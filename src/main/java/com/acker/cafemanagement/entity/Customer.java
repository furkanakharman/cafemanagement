package com.acker.cafemanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Customer {
	private Long id;
	public String name;
	public Long fktablenumber;
	@ApiModelProperty(position = 1,required=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ApiModelProperty(position = 2, required=true, value="John")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ApiModelProperty(position=3,required = true)
	public Long getFktablenumber() {
		return fktablenumber;
	}
	public void setFktablenumber(Long fktablenumber) {
		this.fktablenumber = fktablenumber;
	}

	
}
