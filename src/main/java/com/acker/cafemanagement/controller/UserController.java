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
import com.acker.cafemanagement.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/tables")
	public List<DineTable> getDineTables() {
		return userService.getDineTables();
	}
	@GetMapping(value="/customers")
	public @ResponseBody List<Customer> getCustomers() {
		
		return null;
	}
	
	@PutMapping(value="/customers",consumes="application/json")
	public @ResponseBody Customer putCustomer(@RequestBody Customer jsonString) {
		return userService.putCustomer(jsonString);
	}
	
	//list of working servers
	@GetMapping(value="/servers")
	public @ResponseBody List<Server> getServers(){
		
		return null;
	}
	@GetMapping(value="/servers/{id}")
	public String getServerReport(@PathVariable String id) {
		return null;
	}
	//For new server hires
	@PutMapping(value="/servers")
	public String putServer(@RequestBody Server jsonString){
		return null;
	}
	@DeleteMapping(value="/servers/{id}")
	public String deleteServer(@PathVariable String id) {
		//delete server with given id
		return null;
	}
	
	//end of the class
}
