package com.acker.cafemanagement.entity;



public class FinalizedOrderEntity {
		private Long id;
		public Long customerId;
		public Long orderId;
		public String customerNote;
		public String orderSplit;
		public Long getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}
		public Long getOrderId() {
			return orderId;
		}
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
		public String getCustomerNote() {
			return customerNote;
		}
		public void setCustomerNote(String customerNote) {
			this.customerNote = customerNote;
		}
		public String getOrderSplit() {
			return orderSplit;
		}
		public void setOrderSplit(String orderSplit) {
			this.orderSplit = orderSplit;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
}
