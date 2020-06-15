package com.acker.cafemanagement.entity;

public class Customer {
	private Long id;
	public String name;
	public Long fktablenumber;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getFktablenumber() {
		return fktablenumber;
	}
	public void setFktablenumber(Long fktablenumber) {
		this.fktablenumber = fktablenumber;
	}

	
}
