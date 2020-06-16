package com.acker.cafemanagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acker.cafemanagement.entity.ItemOrder;
import com.acker.cafemanagement.entity.MenuItems;
import com.acker.cafemanagement.entity.OrderEntity;
import com.acker.cafemanagement.entity.OrderKitchen;
import com.acker.cafemanagement.entity.ServerObject;

@Service
public class OrderService {
	final Logger logger = LoggerFactory.getLogger(MenuService.class);
	@Autowired
	DatabaseConnectionService dbConnectionService;
	
	public void orderServed(ServerObject serverObject) {
		dbConnectionService.orderServed(serverObject);
	}

	public List<OrderEntity> isOrderReady() {
		return dbConnectionService.isOrderReady();
				
	}

	public String readyOrder(String orderId) {
		return dbConnectionService.readyOrder(orderId);
		
		
	}

	public List<OrderKitchen> selectOrder(String id) {
		return dbConnectionService.selectOrder(id);
		
	}

	public List<OrderEntity> getGivenOrders() {
		return dbConnectionService.getGivenOrders();
		
	}

	public String myOrderStatus(String id) {
		return dbConnectionService.myOrderStatus(id);
		
	}

	public void makeOrder() {
		// TODO Auto-generated method stub
		
	}

	public String addItems(List<ItemOrder> jsonMenuItems) {
		int result = dbConnectionService.addItems(jsonMenuItems);
		if (result>=0) {
			logger.info("Post Operation was Successful");
			return "Operation was Successful";			
		}
		else {
			logger.info("Post Operation was UnSuccessful");
			return "Operation was Unsuccessful";			
		}
		
	}

}
