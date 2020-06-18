package com.acker.cafemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.acker.cafemanagement.entity.Customer;

@Controller
@RequestMapping("/tl")
public class IndexController {
	
	@RequestMapping(value="/mainmenu",method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	@RequestMapping(value="/customer",method = RequestMethod.GET)
	public String customer() {
		
		return "customer";
	}
	@RequestMapping(value="/customer",method = RequestMethod.POST)
	public String createCustomer(String name,String tablenumber) {
		System.out.println("customer : "+name+tablenumber);
		Customer customer = new Customer();
		customer.setName(name);
		customer.setFktablenumber(Long.valueOf(tablenumber));
		return "customer";
	}
	@RequestMapping("/server")
	public String server() {
		return "server";
	}
	@RequestMapping("/kitchen")
	public String kitchen() {
		return "kitchen";
	}
	@RequestMapping("/manager")
	public String manager() {
		return "manager";
	}
}
