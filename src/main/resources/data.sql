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

INSERT INTO customers(name,fktablenumber) VALUES ('name1',5);
INSERT INTO customers(name,fktablenumber) VALUES ('name2',6);
INSERT INTO customers(name,fktablenumber) VALUES ('name3',8);
INSERT INTO customers(name,fktablenumber) VALUES ('name4',4);

INSERT INTO orders(fkCustomerId,customerNote,totalPrice) VALUES(2,'tuzsuz olsun',125);
INSERT INTO orders(fkCustomerId,customerNote,totalPrice) VALUES(4,'soguk olsun',60);

COMMIT;