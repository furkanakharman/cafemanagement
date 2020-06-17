---Tables for availability check
CREATE TABLE tables(id IDENTITY PRIMARY KEY,tablenumber INT AUTO_INCREMENT,availability VARCHAR(30) DEFAULT 'Available');
---Customers table
CREATE TABLE customers(id IDENTITY PRIMARY KEY, name VARCHAR(30),fkTableNumber LONG,FOREIGN KEY (fktablenumber) REFERENCES tables(id),registerDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP());
---Orders table
CREATE TABLE orders(id IDENTITY PRIMARY KEY,fkCustomerId LONG,FOREIGN KEY (fkCustomerId) REFERENCES customers(id),customerNote VARCHAR(150),totalPrice INT,orderStatus VARCHAR(30) DEFAULT 'New',orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP());
--- Menu Categories and individual Menu Items
CREATE TABLE menuCategory(id IDENTITY PRIMARY KEY,categoryName VARCHAR(50),categoryDescription VARCHAR(200));
CREATE TABLE menuItems(id IDENTITY PRIMARY KEY,fkMenuCategory LONG,FOREIGN KEY (fkMenuCategory) REFERENCES MenuCategory(id),itemName VARCHAR(40),itemDescription VARCHAR(200),imageUrl VARCHAR(250),cost INT);
---
CREATE TABLE itemOrders(id IDENTITY PRIMARY KEY,fkOrderId LONG,FOREIGN KEY (fkOrderId) REFERENCES orders(id),fkMenuItemId LONG,FOREIGN KEY (fkMenuItemId) REFERENCES menuItems(id),quantity INT,totalPrice INT,orderowner VARCHAR(30));
---Servers and their served orders
CREATE TABLE servers(id IDENTITY PRIMARY KEY, fullName VARCHAR(25));
CREATE TABLE servedOrders(id IDENTITY PRIMARY KEY,fkServerId LONG,FOREIGN KEY (fkServerId) REFERENCES servers(id),fkOrderId LONG,FOREIGN KEY (fkOrderId) REFERENCES orders(id));
---
COMMIT;