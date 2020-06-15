package com.acker.cafemanagement.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.acker.cafemanagement.entity.MenuCategory;
import com.acker.cafemanagement.entity.MenuItems;

import com.acker.cafemanagement.service.MenuService;

@RestController
@RequestMapping("/api/v1/menu")
@CrossOrigin(origins = "http://localhost:4200") // for angular to interact with the controller
public class MenuController {
	final Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuService;

	//TODO: change this mapping
	@GetMapping("/")
	public String mainMenu() {
		return "mainmenu";
	}
	
	//returns json of all categories
	@GetMapping(value= "/categories")
	public List<MenuCategory> getCategories() {
		return menuService.getCategories();
	}
	
	@GetMapping(value = "/categories/{categoryId}")
	public List<MenuItems> getItems(@PathVariable String categoryId) {
		return menuService.getItems(categoryId);
	
	}
	
	@PutMapping(value="/categories",consumes="application/json")
	public @ResponseBody MenuCategory postCategories(@RequestBody MenuCategory jsonString) {
		menuService.postCategories(jsonString);
		return jsonString;
		
	}

	@PutMapping(value="/categories/{categoryId}",consumes="application/json")
	public @ResponseBody MenuItems postItems(@PathVariable("categoryId") String categoryId, @RequestBody MenuItems jsonString) {
		jsonString.setFkMenuCategory(Long.valueOf(categoryId));
		menuService.postItems(jsonString);	
		return jsonString;
	}
	
	
}
