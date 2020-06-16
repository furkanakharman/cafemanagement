package com.acker.cafemanagement.entity;

import javax.persistence.Entity;

//Uninspired naming, i know.

public class OrderKitchen {
		public Long fkOrderId;
		public String itemName;
		public int quantity;
		public Long getFkOrderId() {
			return fkOrderId;
		}
		public void setFkOrderId(Long fkOrderId) {
			this.fkOrderId = fkOrderId;
		}
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
}
