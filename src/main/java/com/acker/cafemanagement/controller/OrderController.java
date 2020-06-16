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

import com.acker.cafemanagement.entity.ItemOrder;
import com.acker.cafemanagement.entity.MenuItems;
import com.acker.cafemanagement.entity.OrderEntity;
import com.acker.cafemanagement.entity.OrderKitchen;
import com.acker.cafemanagement.entity.ServerObject;
import com.acker.cafemanagement.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
		
	@Autowired
	OrderService orderService;
	
	//List of MenuItems will be added to the db
	@PutMapping("/customer/additems")
	public String addItems(@RequestBody List<ItemOrder> itemOrder) {
		//add to itemorders table and link to the main order
		return orderService.addItems(itemOrder);
		
	}
	
	//Customer finalizes their order, customer note will be sent in this step.
	@PostMapping("/customer")
	public @ResponseBody OrderEntity makeOrder() {
		orderService.makeOrder();
		return null;
	}
	//Customer checks their order status
	@GetMapping("/customer/{id}")
	public String myOrderStatus(@PathVariable String customerId) {
		return orderService.myOrderStatus(customerId);
		
	}
	
	//kitchen will receive Orders in "waiting" status
	@GetMapping("/kitchen")
	public @ResponseBody List<OrderEntity> getGivenOrders(){
		return orderService.getGivenOrders();
	
	}
	//kitchen will receive individual items in the order and set selected order as "preparing"
	@GetMapping("/kitchen/{id}")
	public @ResponseBody List<OrderKitchen> selectOrder(@PathVariable String orderId){
		return orderService.selectOrder(orderId);
		
	}
	//kitchen will mark order as "Ready to Serve" then both servers and customers will be alerted
	@PostMapping("/kitchen/{id}")
	public String readyOrder(@PathVariable String orderId) {
		return orderService.readyOrder(orderId);
		
	}
	//list all orders with status "ready to serve"
	@GetMapping("/server")
	public @ResponseBody List<OrderEntity> isOrderReady(){
		return orderService.isOrderReady();
		
	}
	//select ready to serve order and set its status to Served, then add orderid and serverid to Served orders
	@PostMapping("/server")
	public String orderServed(@PathVariable ServerObject serverObject) {
		orderService.orderServed(serverObject);
		return "done!";
		//TODO:add proper response
	}
	
	
		
	//end of class
}
