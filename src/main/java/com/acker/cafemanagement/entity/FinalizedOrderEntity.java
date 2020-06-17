package com.acker.cafemanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


//TODO: add customernote to customer entity, then delete this entity.
@ApiModel
public class FinalizedOrderEntity {
		private Long id;
		public Long customerId;
		public Long orderId;
		public String customerNote;
		@ApiModelProperty(position = 1,required=true,value="1")
		public Long getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}
		@ApiModelProperty(position = 2,required=true,value="1")
		public Long getOrderId() {
			return orderId;
		}
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
		@ApiModelProperty(position = 3,required=false,value="No salt, thanks")
		public String getCustomerNote() {
			return customerNote;
		}
		public void setCustomerNote(String customerNote) {
			this.customerNote = customerNote;
		}
		@ApiModelProperty(position = 4,required=false)
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
}
