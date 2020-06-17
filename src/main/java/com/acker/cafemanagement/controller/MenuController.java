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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/menu")
@CrossOrigin(origins = "http://localhost:4200") // for angular to interact with the controller
public class MenuController {
	final Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuService;

	//TODO: change this mapping
	@ApiOperation(value ="Does nothing but returns a string",notes="")
	@GetMapping("/")
	public String mainMenu() {
		return "mainmenu";
	}
	
	//returns json of all categories
	@ApiOperation(value = "Returns all menu categories",notes="No need to provide anything")
	@GetMapping(value= "/categories")
	public List<MenuCategory> getCategories() {
		return menuService.getCategories();
	}
	@ApiOperation(value ="Returns items in selected menu category",notes="")
	@GetMapping(value = "/categories/{categoryId}")
	public List<MenuItems> getItems(@PathVariable String categoryId) {
		return menuService.getItems(categoryId);
	
	}
	@ApiOperation(value ="Create a new menu category",notes="")
	@PutMapping(value="/categories",consumes="application/json")
	public @ResponseBody MenuCategory postCategories(@RequestBody MenuCategory jsonString) {
		menuService.postCategories(jsonString);
		return jsonString;
		
	}
	@ApiOperation(value ="Create new items for selected menu categories",notes="")
	@PutMapping(value="/categories/{categoryId}",consumes="application/json")
	public @ResponseBody MenuItems postItems(@PathVariable("categoryId") String categoryId, @RequestBody MenuItems jsonString) {
		jsonString.setFkMenuCategory(Long.valueOf(categoryId));
		menuService.postItems(jsonString);	
		return jsonString;
	}
	
	
}
