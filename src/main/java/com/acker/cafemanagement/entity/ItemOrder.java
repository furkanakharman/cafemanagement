package com.acker.cafemanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * I wish I have used Hibernate or any ORM because this is getting confusing :(
 * Maybe my design sucks, I have made many mistakes but just realizing them.
 * "Akılsız başın cezasını Spaghetti Code çekermiş..."
***/

@ApiModel
public class ItemOrder {
	private Long id;
	public Long fkOrderId;
	public Long fkMenuItemId;
	public int quantity;
	public String orderOwner;
	@ApiModelProperty(position = 1,required=false,value="John")
	public String getOrderOwner() {
		return orderOwner;
	}
	public void setOrderOwner(String orderOwner) {
		this.orderOwner = orderOwner;
	}
	@ApiModelProperty(position = 2,required=true,value="1")
	public Long getFkOrderId() {
		return fkOrderId;
	}
	public void setFkOrderId(Long fkOrderId) {
		this.fkOrderId = fkOrderId;
	}
	@ApiModelProperty(position = 3,required=true,value="3")
	public Long getFkMenuItemId() {
		return fkMenuItemId;
	}
	public void setFkMenuItemId(Long fkMenuItemId) {
		this.fkMenuItemId = fkMenuItemId;
	}
	@ApiModelProperty(position = 4,required=true,value="1")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@ApiModelProperty(position = 5,required=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	

}
