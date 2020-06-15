package com.acker.cafemanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acker.cafemanagement.entity.MenuItems;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	/*//create an initial order 
	@PutMapping("/")
	public String putOrder() {
		
		return null;
	}
	*/
	
	@PutMapping("/additems")
	public String addItems(@RequestBody List<MenuItems> jsonMenuItems) {
		//add to itemorders table and link to the main order
		return null;
	}
	
	@PostMapping("/")
	public String makeOrder() {
		return null;
	}
	
	//end of class
}
