# API EXAMPLES
 Localhost links

Customer creates a record
```

	PUT: http://localhost:8080/api/v1/users/customers
		payload (json) : {"name": "John","fktablenumber": 5}
		
```
Customer calls menu
```

	GET: http://localhost:8080/api/v1/menu/categories/

```
Customer selects a menu category
```
	
	GET: http://localhost:8080/api/v1/menu/categories/{categoryId}

```
Customer selects items from the menu
```	
	PUT: http://localhost:8080/api/v1/orders/customer/additems
		payload (json - array) :			
						 [
 							 {
   						 "orderOwner": "John",
   						 "fkOrderId": 5,
  						 "fkMenuItemId": 2,
  						 "quantity": 1
 							 }
						]
						
```					
Finally customer writes their note then gives the order

```
	POST: http://localhost:8080/api/v1/orders/customer
		payload (json) : {"orderId": 5,"customerNote": "customNote"}
```

Kitchen lists all given orders

```

	GET: http://localhost:8080/api/v1/orders/kitchen
	
```

Kitchen selects an order and reveals the items in it
```	

	GET: http://localhost:8080/api/v1/orders/kitchen/{orderId}
	
```
When the items are ready, kitchen changes their status to ready to serve
	
```
	POST: http://localhost:8080/api/v1/orders/kitchen/{orderId}
```
Servers list all ready to serve orders
	
	```
	GET: http://localhost:8080/api/v1/orders/server
	```
Servers select an order to serve to the customer, the served order will be recorded with server name

	```
	POST: http://localhost:8080/api/v1/orders/server
		payload (json) : {"serverId": 1,"orderId": 5}
	```
	
Manager can list Servers' served orders with server id

	```
	GET: http://localhost:8080/api/v1/users/servers/{serverId}
	```
Manager can add new menu categories and items
	Adding Menu Categories
	
		
	PUT: http://localhost:8080/api/v1/menu/categories
		payload (json) : { "categoryName": "Desserts",
								"categoryDescription": "Sweeties!"
								}
	
	
   Adding menu items to a selected category
   
	
	PUT: http://localhost:8080/api/v1/menu/categories/{categoryId}
		
		payload (json) : 																																		 
  								{
  									"fkMenuCategory": 0,
 									"itemDescription": "string",
 									 "itemName": "string",
									  "imageUrl": "string",
									  "cost": 0
								} 
					
				