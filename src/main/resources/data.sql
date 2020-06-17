--Menu Categories has inserted
INSERT INTO menuCategory(categoryName,categoryDescription) VALUES ('Entrees',' small course that precedes the main course');
INSERT INTO menuCategory(categoryName,categoryDescription) VALUES ('Main Courses','the biggest dish on the menu');
INSERT INTO menuCategory(categoryName,categoryDescription) VALUES ('Drinks','Explore sweet to salty drinks that go well with your meal.');
--Menu Items has inserted
INSERT INTO menuItems(fkMenuCategory,itemName,itemDescription,imageUrl,cost) VALUES(1,'Vegetarian Salad','a salad for vegetarians',' ',15);
INSERT INTO menuItems(fkMenuCategory,itemName,itemDescription,imageUrl,cost) VALUES(1,'Vegetarian Soup','a soup for vegetarians',' ',22);
INSERT INTO menuItems(fkMenuCategory,itemName,itemDescription,imageUrl,cost) VALUES(2,'Peanut Slaw with Soba Noodles','Gluten free vegan noodle dish',' ',30);
INSERT INTO menuItems(fkMenuCategory,itemName,itemDescription,imageUrl,cost) VALUES(2,'Pinto Posole','Gluten free vegan rice dish',' ',32);
INSERT INTO menuItems(fkMenuCategory,itemName,itemDescription,imageUrl,cost) VALUES(3,'Cool Pineapple Juice','Pineapple juice with a touch of mint',' ',11);
INSERT INTO menuItems(fkMenuCategory,itemName,itemDescription,imageUrl,cost) VALUES(3,'Almond Breeze','Non dairy milk that goes well with rice',' ',9);

--Couldn't find a way to Loop in H2 DB, 11 tables has inserted
INSERT INTO tables() VALUES();
INSERT INTO tables() VALUES();
INSERT INTO tables() VALUES();
INSERT INTO tables() VALUES();
INSERT INTO tables() VALUES();
INSERT INTO tables() VALUES();
INSERT INTO tables() VALUES();
INSERT INTO tables() VALUES();
INSERT INTO tables() VALUES();
INSERT INTO tables() VALUES();
INSERT INTO tables() VALUES();
--Inserting Customers and their orders
INSERT INTO customers(name,fktablenumber) VALUES ('Deniz',5);
INSERT INTO customers(name,fktablenumber) VALUES ('Ayse',6);
INSERT INTO customers(name,fktablenumber) VALUES ('Taha',8);
INSERT INTO customers(name,fktablenumber) VALUES ('Oguz',4);

UPDATE tables SET availability='occupied' WHERE tablenumber='5';
UPDATE tables SET availability='occupied' WHERE tablenumber='6';
UPDATE tables SET availability='occupied' WHERE tablenumber='8';
UPDATE tables SET availability='occupied' WHERE tablenumber='4';

INSERT INTO orders(fkCustomerId,customerNote,totalPrice,orderstatus) VALUES(1,'tuzsuz olsun',125,'waiting');
INSERT INTO orders(fkCustomerId,customerNote,totalPrice,orderstatus) VALUES(2,'soguk olsun',60,'waiting');
INSERT INTO orders(fkCustomerId,customerNote,totalPrice,orderstatus) VALUES(3,'ilik olsun',40,'waiting');
INSERT INTO orders(fkCustomerId,customerNote,totalPrice,orderstatus) VALUES(4,'icecek yemekle gelsin',70,'waiting');

INSERT INTO itemorders(fkorderid,fkmenuitemid,quantity,orderowner) VALUES(1,2,1,'Deniz');
INSERT INTO itemorders(fkorderid,fkmenuitemid,quantity,orderowner) VALUES(1,4,1,'Ece');
INSERT INTO itemorders(fkorderid,fkmenuitemid,quantity,orderowner) VALUES(2,5,1,'Ayse');
INSERT INTO itemorders(fkorderid,fkmenuitemid,quantity,orderowner) VALUES(2,1,1,'Mehmet');
INSERT INTO itemorders(fkorderid,fkmenuitemid,quantity) VALUES(3,3,1);
INSERT INTO itemorders(fkorderid,fkmenuitemid,quantity) VALUES(3,5,1);
INSERT INTO itemorders(fkorderid,fkmenuitemid,quantity) VALUES(4,2,1);
INSERT INTO itemorders(fkorderid,fkmenuitemid,quantity) VALUES(4,6,1);
--Inserting Servers
INSERT INTO servers(fullname) VALUES('Furkan');
INSERT INTO servers(fullname) VALUES('Elif');
INSERT INTO servers(fullname) VALUES('Can');

COMMIT;