package com.acker.cafemanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ServerObject {
	public Long serverId;
	public Long orderId;
	@ApiModelProperty(position = 1,required=true,value="1")
	public Long getServerId() {
		return serverId;
	}
	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}
	@ApiModelProperty(position = 2,required=true,value="1")
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
}
