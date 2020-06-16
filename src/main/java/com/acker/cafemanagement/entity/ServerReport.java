package com.acker.cafemanagement.entity;

import java.sql.Date;

public class ServerReport {
	public Long fkserverid;
	public Long fkorderid;
	public Long fkcustomerid;
	public String customernote;
	public int totalprice;
	public String orderstatus;
	public Date orderdate;
	public Long getFkserverid() {
		return fkserverid;
	}
	public void setFkserverid(Long fkserverid) {
		this.fkserverid = fkserverid;
	}
	public Long getFkorderid() {
		return fkorderid;
	}
	public void setFkorderid(Long fkorderid) {
		this.fkorderid = fkorderid;
	}
	public Long getFkcustomerid() {
		return fkcustomerid;
	}
	public void setFkcustomerid(Long fkcustomerid) {
		this.fkcustomerid = fkcustomerid;
	}
	public String getCustomernote() {
		return customernote;
	}
	public void setCustomernote(String customernote) {
		this.customernote = customernote;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	
} 
