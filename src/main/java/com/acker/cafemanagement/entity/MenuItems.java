package com.acker.cafemanagement.entity;

public class MenuItems {
	private Long id;
	public Long fkMenuCategory;
	public String itemName;
	public String itemDescription;
	public String imageUrl;
	public int cost;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFkMenuCategory() {
		return fkMenuCategory;
	}
	public void setFkMenuCategory(Long fkMenuCategory) {
		this.fkMenuCategory = fkMenuCategory;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
