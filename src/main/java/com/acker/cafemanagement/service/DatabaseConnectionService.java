package com.acker.cafemanagement.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acker.cafemanagement.entity.Customer;
import com.acker.cafemanagement.entity.DatabaseConnection;
import com.acker.cafemanagement.entity.DineTable;
import com.acker.cafemanagement.entity.FinalizedOrderEntity;
import com.acker.cafemanagement.entity.ItemOrder;
import com.acker.cafemanagement.entity.MenuCategory;
import com.acker.cafemanagement.entity.MenuItems;
import com.acker.cafemanagement.entity.OrderEntity;
import com.acker.cafemanagement.entity.OrderKitchen;
import com.acker.cafemanagement.entity.Server;
import com.acker.cafemanagement.entity.ServerObject;
import com.acker.cafemanagement.entity.ServerReport;

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
	//FOR INSERT AND UPDATE QUERIES
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
	
	public void changeOrderStatus(String status,Long id) {
		String changeOrderStatusQuery="UPDATE orders SET status='"+status+"' WHERE id="+id+";";
		this.executeUpdate(changeOrderStatusQuery);
			
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
	//creates user's empty order then adds orderId to Customer object
	public Customer createInitialOrder(Customer customer) {
		String createInitialOrderQuery="INSERT INTO orders(fkcustomerid) VALUES ("+customer.getId()+");";
		this.executeUpdate(createInitialOrderQuery);
		String getOrderIdByCustomerId=("SELECT id FROM orders WHERE fkcustomerid='"+customer.getId()+"';");
		try {
			ResultSet rs = this.executeQuery(getOrderIdByCustomerId);
			while(rs.next()){
			customer.setOrderId(rs.getLong("id"));
			}
		} catch (SQLException sqlex) {
			logger.error("SQL Error {}",sqlex);
		}
		return customer;

		
	}
	public void setTableStatusByTableNumber(String status,Long tableNumber) {
		String setTableStatus="UPDATE tables SET availability='"+status+"' WHERE id="+tableNumber+";";
		this.executeUpdate(setTableStatus);
		
	}
	
	//TODO:this function creates order even if the tablenumber was not valid!
	//Create a user and set their table Occupied then create empty order
	public Customer putCustomer(Customer customer) {
		String putCustomerQuery="INSERT INTO customers(name,fktablenumber) VALUES('"+customer.name+"',"+customer.fktablenumber+");";
		this.executeUpdate(putCustomerQuery);
		this.setTableStatusByTableNumber("Occupied",customer.fktablenumber);
		Customer insertedCustomer = new Customer();
	
		insertedCustomer = this.getCustomerByTableNumber(customer.fktablenumber);
		return this.createInitialOrder(insertedCustomer);

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
	
	//returns list of orders marked as "Waiting"
	public List<OrderEntity> getGivenOrders() {
		String getGivenOrdersQuery = "SELECT id,fkcustomerid,customernote,totalprice,orderstatus FROM orders WHERE orderstatus='waiting';";
		List<OrderEntity> listOrders = new ArrayList<OrderEntity>();
		try {
			ResultSet rs = this.executeQuery(getGivenOrdersQuery);
			while(rs.next()) {
				OrderEntity orderEntity = new OrderEntity();
				orderEntity.setId(rs.getLong("id"));
				orderEntity.setFkCustomerId(rs.getLong("fkcustomerid"));
				orderEntity.setCustomerNote(rs.getString("customernote"));
				orderEntity.setTotalPrice(rs.getInt("totalprice"));
				orderEntity.setOrderStatus(rs.getString("orderstatus"));
				listOrders.add(orderEntity);
			}
			
		} catch (SQLException sqlex) {
			logger.error("SQL Error {}",sqlex);
		}
		return listOrders;
	}
	//returns Customers' Ordered MenuItems, using associtation table between menuitem and orders, the ItemOrders table.
	public List<OrderKitchen> selectOrder(String id) {
		String selectOrderQuery = "SELECT fkorderid,itemname,quantity FROM menuitems INNER JOIN itemorders on menuitems.id = itemorders.fkmenuitemid WHERE itemorders.fkorderid ='"+id+"';";
		String setPreparingStateQuery = "UPDATE orders SET orderstatus='preparing' WHERE id='"+id+"';";
		List<OrderKitchen> listOrderKitchen = new ArrayList<OrderKitchen>();
		try {
			ResultSet rs = this.executeQuery(selectOrderQuery);
			while(rs.next()) {
				OrderKitchen orderKitchen = new OrderKitchen();
				orderKitchen.setFkOrderId(rs.getLong("fkorderid"));
				orderKitchen.setItemName(rs.getString("itemname"));
				orderKitchen.setQuantity(rs.getInt("quantity"));
				listOrderKitchen.add(orderKitchen);
			}
		} catch  (SQLException sqlex) {
			logger.error("SQL Error {}",sqlex);
		}
		this.executeUpdate(setPreparingStateQuery);
		return listOrderKitchen;
	}
	public int addItems(List<ItemOrder> listItemOrder) {
		int result=-1;
		for(Iterator<ItemOrder> it = listItemOrder.iterator();it.hasNext();) {
			ItemOrder itemOrder = new ItemOrder();
			itemOrder = it.next();
			String addItemsQuery="INSERT INTO itemorders(fkorderid,fkmenuitemid,quantity,orderowner) VALUES('"+itemOrder.fkOrderId+"','"+itemOrder.fkMenuItemId+"','"+itemOrder.quantity+"','"+itemOrder.orderOwner+"');";
			result = this.executeUpdate(addItemsQuery);
		}
			return result;
	}

	//returns state of customers' order 
	//states new,waiting,preparing,readytoserve
	public String myOrderStatus(String id) {
		String myOrderStatusQuery="SELECT orderstatus FROM orders WHERE id='"+id+"';";
		String status="";
		try {
			ResultSet rs = this.executeQuery(myOrderStatusQuery);
			while(rs.next()) {
				String orderStatus=rs.getString("orderstatus");
				return orderStatus;
			}
		} catch (SQLException sqlex) {
			logger.error("SQL Error {}",sqlex);
		}
		return status;
		
	}
	public String readyOrder(String orderId) {
		String readyOrderQuery="UPDATE orders SET orderstatus='readytoserve' WHERE id='"+orderId+"';";
		this.executeUpdate(readyOrderQuery);
		return "Order "+orderId+" set Ready To Serve";
		
	}
	
	//TODO: repeating code, look getGivenOrders,
	public List<OrderEntity> isOrderReady() {
		String isOrderReadyQuery="SELECT id,fkcustomerid,customerNote,totalPrice,orderStatus FROM orders WHERE orderstatus='readytoserve';";
		List<OrderEntity> listOrders = new ArrayList<OrderEntity>();
		try {
			ResultSet rs = this.executeQuery(isOrderReadyQuery);
			while(rs.next()) {
				OrderEntity orderEntity = new OrderEntity();
				orderEntity.setId(rs.getLong("id"));
				orderEntity.setFkCustomerId(rs.getLong("fkcustomerid"));
				orderEntity.setCustomerNote(rs.getString("customernote"));
				listOrders.add(orderEntity);
			}
		} catch (SQLException sqlex) {
			logger.error("SQL Error {}",sqlex);
		}
		return listOrders;
		
	}
	
	//served order's status will be set to "served" then added to server's record
	public void orderServed(ServerObject serverObject) {
		String setOrderServedQuery = "UPDATE orders SET orderstatus='served' WHERE id='"+serverObject.getOrderId()+"';";
		String recordServedOrderQuery = "INSERT INTO servedorders(fkserverid,fkorderid) VALUES('"+serverObject.getServerId()+"','"+serverObject.getOrderId()+"');";
		this.executeUpdate(setOrderServedQuery);
		this.executeUpdate(recordServedOrderQuery);
		
	}
	public void makeOrder(FinalizedOrderEntity finalizeOrder) {
		String makeOrderQuery="UPDATE orders SET customernote='"+finalizeOrder.getCustomerNote()+"',orderstatus='waiting' WHERE id='"+finalizeOrder.orderId+"';";
		this.executeUpdate(makeOrderQuery);
		
	}
	public List<Server> getServers() {
		String getServersQuery = "SELECT*FROM servers;";
		List<Server> listServer = new ArrayList<Server>();
		try {
			ResultSet rs = this.executeQuery(getServersQuery);
			while(rs.next()) {
				Server server = new Server();
				server.setId(rs.getLong("id"));
				server.setFullname(rs.getString("fullname"));
				listServer.add(server);
			}
		} catch (SQLException sqlex) {
			logger.error("SQLException {}",sqlex);
		}
	return listServer;
	}
	public List<ServerReport> getServerReport(String serverId) {
		//first get all orders
		String getAllOrdersFromServerQuery="SELECT fkserverid,fkorderid,fkcustomerid,customernote,totalprice,orderstatus,orderdate FROM servedorders INNER JOIN orders ON servedorders.fkorderid=orders.id WHERE servedorders.fkserverid='"+serverId+"';";
		List<ServerReport> listServerReport = new ArrayList<ServerReport>();
		try {
			ResultSet rs = this.executeQuery(getAllOrdersFromServerQuery);
			while(rs.next()) {
				ServerReport serverReport = new ServerReport();
				serverReport.setFkserverid(rs.getLong("fkserverid"));
				serverReport.setFkorderid(rs.getLong("fkorderid"));
				serverReport.setFkcustomerid(rs.getLong("fkcustomerid"));
				serverReport.setCustomernote(rs.getString("customernote"));
				serverReport.setTotalprice(rs.getInt("totalprice"));
				serverReport.setOrderstatus(rs.getString("orderstatus"));
				serverReport.setOrderdate(rs.getDate("orderdate"));
				listServerReport.add(serverReport);
			}
		} catch (SQLException sqlex) {
			logger.error("SQLException {}",sqlex);
		}
		return listServerReport;
	}
	public void putServer(Server server) {
		String putServerQuery="INSERT INTO servers(fullname) VALUES('"+server.getFullname()+"');";
		this.executeUpdate(putServerQuery);
	}
	
	
	
	//eoc
}
