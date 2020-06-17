package com.acker.cafemanagement.entity;

import io.swagger.annotations.ApiModelProperty;

public class MenuItems {
	private Long id;
	public Long fkMenuCategory;
	public String itemName;
	public String itemDescription;
	public String imageUrl;
	public int cost;
	@ApiModelProperty(position = 1,required=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ApiModelProperty(position = 2,required=true,value="3")
	public Long getFkMenuCategory() {
		return fkMenuCategory;
	}
	public void setFkMenuCategory(Long fkMenuCategory) {
		this.fkMenuCategory = fkMenuCategory;
	}
	@ApiModelProperty(position = 3,required=true,value="Cold Water")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@ApiModelProperty(position = 3,required=true,value="Refreshing, isnt it?")
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	@ApiModelProperty(position = 4,required=false)
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@ApiModelProperty(position = 5,required=true,value="10")
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
