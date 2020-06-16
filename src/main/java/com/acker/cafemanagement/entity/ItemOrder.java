package com.acker.cafemanagement.entity;

import javax.persistence.Entity;

/***
 * I wish I have used Hibernate or any ORM because this is getting confusing :(
 * Maybe my design sucks, I have made many mistakes but just realizing them.
 * "Akılsız başın cezasını Spaghetti Code çekermiş...
***/
@Entity
public class ItemOrder {
	public Long fkOrderId;
	public Long fkMenuItemId;
	public int quantity;
	public Long getFkOrderId() {
		return fkOrderId;
	}
	public void setFkOrderId(Long fkOrderId) {
		this.fkOrderId = fkOrderId;
	}
	public Long getFkMenuItemId() {
		return fkMenuItemId;
	}
	public void setFkMenuItemId(Long fkMenuItemId) {
		this.fkMenuItemId = fkMenuItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

}
