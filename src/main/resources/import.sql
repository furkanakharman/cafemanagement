
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
--Commit changes
COMMIT;
