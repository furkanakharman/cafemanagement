package com.acker.cafemanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acker.cafemanagement.entity.Customer;
import com.acker.cafemanagement.entity.DineTable;

import com.acker.cafemanagement.entity.Server;
import com.acker.cafemanagement.entity.ServerReport;
import com.acker.cafemanagement.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	//Get availabilities of tables, 
	//TODO: to you, from the backend: block people from choosing occupied tables in the frontend
	@GetMapping(value="/tables")
	public List<DineTable> getDineTables() {
		return userService.getDineTables();
	}
	//Get all customers
	@GetMapping(value="/customers")
	public @ResponseBody List<Customer> getCustomers() {
		return userService.getCustomers();		
	}
	//Register new customer
	@PutMapping(value="/customers",consumes="application/json")
	public @ResponseBody Customer putCustomer(@RequestBody Customer jsonString) {
		return userService.putCustomer(jsonString);
	}
	@DeleteMapping(value="/customers/{id}")
	public String deleteCustomer(@PathVariable String id) {
		//TODO:Implement delete customer function
		return "Feature hasn't been Implemented yet.";
	}
	//list of working servers
	@GetMapping(value="/servers")
	public @ResponseBody List<Server> getServers(){
		return userService.getServers();
		
	}
	@GetMapping(value="/servers/{serverId}")
	public @ResponseBody List<ServerReport> getServerReport(@PathVariable String serverId) {
		return userService.getServerReport(serverId);
	}
	//For new server hires
	@PutMapping(value="/servers")
	public @ResponseBody Server putServer(@RequestBody Server jsonString){
		userService.putServer(jsonString);
		return jsonString;
	}
	@DeleteMapping(value="/servers/{id}")
	public String deleteServer(@PathVariable String id) {
		//TODO:elete server with given id
		return null;
	}
	
	//end of the class
}
