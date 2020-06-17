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

import com.acker.cafemanagement.entity.FinalizedOrderEntity;
import com.acker.cafemanagement.entity.ItemOrder;
import com.acker.cafemanagement.entity.MenuItems;
import com.acker.cafemanagement.entity.OrderEntity;
import com.acker.cafemanagement.entity.OrderKitchen;
import com.acker.cafemanagement.entity.ServerObject;
import com.acker.cafemanagement.service.OrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
		
	@Autowired
	OrderService orderService;
	
	//Customer checks their order status
	@ApiOperation(value ="Returns order status of given customer",notes="")
	@GetMapping("/customer/{customerId}")
	public String myOrderStatus(@PathVariable String customerId) {
		return orderService.myOrderStatus(customerId);
		
	}
	
	//List of MenuItems will be added to the db
	@ApiOperation(value ="Add items to customers order",notes="Customers can add multiple item choices before finalizing the order")
	@PutMapping("/customer/additems")
	public String addItems(@RequestBody List<ItemOrder> itemOrder) {
		//add to itemorders table and link to the main order
		return orderService.addItems(itemOrder);
		
	}
	
	//Customer finalizes their order, customer note will be sent in this step.
	@ApiOperation(value ="Gives order",notes="Customer must add items first then give the order!")
	@PostMapping(value="/customer",consumes="application/json")
	public String makeOrder(@RequestBody FinalizedOrderEntity finalizedOrder) {
		orderService.makeOrder(finalizedOrder);
		return "done";
	}

	
	//kitchen will receive Orders in "waiting" status
	@ApiOperation(value ="Returns given orders",notes="Kitchen can check if are there any waiting orders")
	@GetMapping("/kitchen")
	public @ResponseBody List<OrderEntity> getGivenOrders(){
		return orderService.getGivenOrders();
	
	}
	//kitchen will receive individual items in the order and set selected order as "preparing"
	@ApiOperation(value ="Returns order details",notes="Kitchen can see what items order includes")
	@GetMapping("/kitchen/{orderId}")
	public @ResponseBody List<OrderKitchen> selectOrder(@PathVariable String orderId){
		return orderService.selectOrder(orderId);
		
	}
	//kitchen will mark order as "Ready to Serve" then both servers and customers will be alerted
	@ApiOperation(value ="Mark order as ready",notes="Kitchen marks order as ready to serve, so servers can see")
	@PostMapping("/kitchen/{orderId}")
	public String readyOrder(@PathVariable String orderId) {
		return orderService.readyOrder(orderId);
		
	}
	//list all orders with status "ready to serve"
	@ApiOperation(value ="Returns ready to serve orders",notes="Server can list prepared orders")
	@GetMapping("/server")
	public @ResponseBody List<OrderEntity> isOrderReady(){
		return orderService.isOrderReady();
		
	}
	//select ready to serve order and set its status to Served, then add orderid and serverid to Served orders
	@ApiOperation(value ="Mark order as served",notes="")
	@PostMapping(value="/server",consumes="application/json")
	public String orderServed(@RequestBody ServerObject serverObject) {
		orderService.orderServed(serverObject);
		return "done!";
		//TODO:add proper response
	}
	
	
		
	//end of class
}
