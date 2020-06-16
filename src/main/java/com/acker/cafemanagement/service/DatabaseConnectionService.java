package com.acker.cafemanagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acker.cafemanagement.entity.Customer;
import com.acker.cafemanagement.entity.DatabaseConnection;
import com.acker.cafemanagement.entity.DineTable;
import com.acker.cafemanagement.entity.MenuCategory;
import com.acker.cafemanagement.entity.MenuItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DatabaseConnectionService {
	
	final Logger logger = LoggerFactory.getLogger(DatabaseConnectionService.class);
	
	private Connection getConnection() {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		logger.info("Connecting to database");
		return databaseConnection.getConnection();				
	}
	//For INSERT AND UPDATE QUERIES
	private int executeUpdate(String sqlQuery) {
		int result=-1;
		PreparedStatement statement;
		Connection conn = this.getConnection();
		try {
			statement = conn.prepareStatement(sqlQuery);
			result=statement.executeUpdate();
			logger.info("Query Executed! : {} ",sqlQuery);
			return result;
		} catch (SQLException sqlex) {
			logger.error("SQL Error {}",sqlex);
		}
		return result;		
	}
	//FOR SELECT QUERIES
	private ResultSet executeQuery(String sqlQuery) {
		ResultSet rs=null;
		PreparedStatement statement;
		Connection conn = this.getConnection();
		try {
			statement = conn.prepareStatement(sqlQuery);
			rs=statement.executeQuery();
			logger.info("Query Executed! : {} ",sqlQuery);
			return rs;
		} catch (SQLException sqlex) {
			logger.error("SQL Error {}",sqlex);
		}
		return rs;
	}
	
	public List<MenuCategory> getMenuCategories() {
		String getMenuCategoriesQuery="SELECT id,categoryname,categorydescription FROM menucategory;";
		List<MenuCategory> listMenuCategories = new ArrayList<MenuCategory>();
		try {
		ResultSet rs = this.executeQuery(getMenuCategoriesQuery);
		while(rs.next()) { 
		MenuCategory menuCategory = new MenuCategory();
		menuCategory.setId(rs.getLong("id"));
		menuCategory.setCategoryName(rs.getString("categoryname"));
		menuCategory.setCategoryDescription(rs.getString("categorydescription"));
		listMenuCategories.add(menuCategory);
		
		}
		}catch(Exception sqlex) {
			logger.error("SQL Error {}",sqlex);
		}
		return listMenuCategories;
	}
	
	public int postMenuCategory(MenuCategory menuCategory) {
		String postMenuCategoryQuery="INSERT INTO menucategory(categoryname,categorydescription) VALUES('"+menuCategory.categoryName+"','" +menuCategory.categoryDescription+"');";
			return this.executeUpdate(postMenuCategoryQuery);
		
	}
	
	public List<MenuItems> getMenuItems(int categoryId){
		String getMenuItemsQuery="SELECT id,itemname,itemdescription,imageurl,cost FROM menuitems WHERE fkmenucategory="+categoryId+";";
		List<MenuItems> listMenuItems = new ArrayList<MenuItems>();
		
		try {
			ResultSet rs = this.executeQuery(getMenuItemsQuery);
		while(rs.next()) { 
		MenuItems menuItems = new MenuItems();
		menuItems.setId(rs.getLong("id"));
		menuItems.setItemName(rs.getString("itemname"));
		menuItems.setItemDescription(rs.getString("itemDescription"));
		menuItems.setImageUrl(rs.getString("imageUrl"));
		menuItems.setCost(rs.getInt("cost"));
		listMenuItems.add(menuItems);
		
		}
		}catch(SQLException sqlex) {
			logger.error("SQL Error {}",sqlex);
		}
		return listMenuItems;
		
	}
	
	public int postMenuItems(MenuItems menuItems) {
		String postMenuCategoryQuery="INSERT INTO menuitems(fkmenucategory,itemname,itemdescription,imageurl,cost) VALUES('"+menuItems.fkMenuCategory+"', '"+menuItems.itemName+"','" +menuItems.itemDescription+"', '"+menuItems.imageUrl+"','"+menuItems.cost+"');";
			return this.executeUpdate(postMenuCategoryQuery);			
	}
	
	//this query gets executed before insertion (possible query caching) therefore return no data, if we used hibernate
	public Customer getCustomerByTableNumber(Long TableNumber) {
		String getCustomerByTableNumberQuery="SELECT id,name,fktablenumber FROM customers WHERE fktablenumber='"+TableNumber+"';";		
		Customer customer=new Customer();
		try {
			ResultSet rs = this.executeQuery(getCustomerByTableNumberQuery);
			while(rs.next()) {
			customer.setId(rs.getLong("id"));
			customer.setFktablenumber(rs.getLong("fktablenumber"));
			customer.setName(rs.getString("name"));
			}
			
		} catch (SQLException e) {
		logger.error("SQL Error in getcustomerByTableNumber {}",e);
		}
		return customer;
	}
	//creates user's empty order to fill in later
	public int createInitialOrder(Long id) {
		String createInitialOrderQuery="INSERT INTO orders(fkcustomerid) VALUES ("+id+");";
		return this.executeUpdate(createInitialOrderQuery);
	}
	public void setTableStatusByTableNumber(String status,Long tableNumber) {
		String setTableStatus="UPDATE tables SET availability='"+status+"' WHERE id="+tableNumber+";";
		this.executeUpdate(setTableStatus);
		
	}
	//Create a user and set their table Occupied then create empty order
	public Customer putCustomer(Customer customer) {
		String putCustomerQuery="INSERT INTO customers(name,fktablenumber) VALUES('"+customer.name+"',"+customer.fktablenumber+");";
		this.executeUpdate(putCustomerQuery);
		this.setTableStatusByTableNumber("Occupied",customer.fktablenumber);
		Customer insertedCustormer = new Customer();
	
		insertedCustormer = this.getCustomerByTableNumber(customer.fktablenumber);
		this.createInitialOrder(insertedCustormer.getId());
		return insertedCustormer;
	}
	
	public List<DineTable> getDineTables() {
		String getDineTablesQuery="SELECT id,tablenumber,availability FROM tables;";
		List<DineTable> listDineTables = new ArrayList<DineTable>();
		try {
			ResultSet rs = this.executeQuery(getDineTablesQuery);
		while(rs.next()) { 
		DineTable dineTable = new DineTable();
		dineTable.setId(rs.getLong("id"));
		dineTable.setTableNumber(rs.getInt("tablenumber"));
		dineTable.setAvailability(rs.getString("availability"));
		listDineTables.add(dineTable);
		}
		}catch(SQLException sqlex) {
			logger.error("SQL Error {}",sqlex);
		}
		return listDineTables;
		
	}
	public List<Customer> getCustomers() {
		String getCustomersQuery="SELECT id,fktablenumber,name FROM customers;";
		List<Customer> listCustomers = new ArrayList<Customer>();
		try {
			ResultSet rs=this.executeQuery(getCustomersQuery);
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getLong("id"));
				customer.setFktablenumber(rs.getLong("fktablenumber"));
				customer.setName(rs.getString("name"));
				listCustomers.add(customer);
			}
		} catch (SQLException sqlex) {
			logger.error("SQL Error {}",sqlex);
		}
		return listCustomers;
	}
	
	
	
	//eoc
}
