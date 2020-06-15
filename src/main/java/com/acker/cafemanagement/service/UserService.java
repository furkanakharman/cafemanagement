package com.acker.cafemanagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acker.cafemanagement.entity.Customer;
import com.acker.cafemanagement.entity.DineTable;

@Service
public class UserService {
	final Logger logger = LoggerFactory.getLogger(MenuService.class);
	
	@Autowired
	DatabaseConnectionService dbConnectionService;


	public Customer putCustomer(Customer customer) {
		return dbConnectionService.putCustomer(customer);
		
	}


	public List<DineTable> getDineTables() {
		return dbConnectionService.getDineTables();
	}


	public List<Customer> getCustomers() {
		return dbConnectionService.getCustomers();
		
	}
	
	
	//end of the class
}
