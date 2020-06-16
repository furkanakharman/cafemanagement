package com.acker.cafemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acker.cafemanagement.entity.MenuItems;
import com.acker.cafemanagement.entity.OrderEntity;
import com.acker.cafemanagement.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
		
	@Autowired
	OrderService orderService;
	
	//List of MenuItems will be added to the db
	@PutMapping("/customer/additems")
	public String addItems(@RequestBody List<MenuItems> jsonMenuItems) {
		//add to itemorders table and link to the main order
		orderService.addItems(jsonMenuItems);
		return null;
	}
	
	//Customer finalizes their order, customer note will be sent in this step.
	@PostMapping("/customer")
	public @ResponseBody OrderEntity makeOrder() {
		orderService.makeOrder();
		return null;
	}
	//Customer checks their order status
	@GetMapping("/customer/{id}")
	public String myOrderStatus(@PathVariable String id) {
		orderService.myOrderStatus(id);
		return null;
	}
	
	//kitchen will receive Orders in "waiting" status
	@GetMapping("/kitchen")
	public @ResponseBody List<OrderEntity> getGivenOrders(){
		orderService.getGivenOrders();
		
		return null;
	}
	//kitchen will receive individual items in the order and set selected order as "preparing"
	@GetMapping("/kitchen/{id}")
	public @ResponseBody List<MenuItems> selectOrder(@PathVariable String id){
		orderService.selectOrder(id);
		return null;
	}
	//kitchen will mark order as "Ready to Serve" then both servers and customers will be alerted
	@PostMapping("/kitchen/{id}")
	public String readyOrder(@PathVariable String id) {
		orderService.readyOrder(id);
		return null;
	}
	//list all orders with status "ready to serve"
	@GetMapping("/server")
	public @ResponseBody List<OrderEntity> isOrderReady(){
		orderService.isOrderReady();
		return null;
	}
	//select ready to serve order and set its status to Served, then add orderid and serverid to Served orders
	@PostMapping("/server/{id}")
	public String orderServed(@PathVariable String id) {
		orderService.orderServed(id);
		return null;
	}
	
	
		
	//end of class
}
