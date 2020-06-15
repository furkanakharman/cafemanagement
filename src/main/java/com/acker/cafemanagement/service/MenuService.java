package com.acker.cafemanagement.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acker.cafemanagement.controller.MenuController;
import com.acker.cafemanagement.entity.MenuCategory;
import com.acker.cafemanagement.entity.MenuItems;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



@Service
public class MenuService {
	final Logger logger = LoggerFactory.getLogger(MenuService.class);
	@Autowired
	DatabaseConnectionService dbConnectionService;

	public List<MenuCategory> getCategories(){
		List<MenuCategory> menuCategoryList = dbConnectionService.getMenuCategories();				
		return menuCategoryList;
	}
	
	public String postCategories(MenuCategory jsonMenuCategory) {
	//	MenuCategory menuCategory = new Gson().fromJson(jsonMenuCategory,MenuCategory.class);
		int result = dbConnectionService.postMenuCategory(jsonMenuCategory);
		if (result>=0) {
			logger.info("Post Operation was Successful");
			return "Operation was Successful";			
		}
		else {
			logger.info("Post Operation was UnSuccessful");
			return "Operation was Unsuccessful";			
		}
		
		
	}
	
	public List<MenuItems> getItems(String categoryId) {
		int catId = Integer.valueOf(categoryId); //TODO: do error handling here
		List<MenuItems> menuItemList = dbConnectionService.getMenuItems(catId);
		return menuItemList;
	}
	
	public String postItems(MenuItems jsonMenuItems) {
		int result = dbConnectionService.postMenuItems(jsonMenuItems);
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
