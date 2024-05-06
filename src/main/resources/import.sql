delete from OrderLine;
delete from pickup;
delete from Orders;
delete from Person;
delete from productPositions;
delete from Products;
delete from shelves;
delete from shelfContainers;


insert into shelfContainers (id, floor, corridor) values (2, 1, 1);
insert into shelfContainers (id, floor, corridor) values (3, 1, 1);
insert into shelfContainers (id, floor, corridor) values (4, 1, 2);
insert into shelfContainers (id, floor, corridor) values (5, 1, 2);
insert into shelfContainers (id, floor, corridor) values (6, 1, 2);
insert into shelfContainers (id, floor, corridor) values (7, 2, 1);
insert into shelfContainers (id, floor, corridor) values (8, 2, 1);
insert into shelfContainers (id, floor, corridor) values (9, 2, 2);
insert into shelfContainers (id, floor, corridor) values (10, 2, 2);
insert into shelfContainers (id, floor, corridor) values (11, 1, 1);

insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (41, 1, 100, 100, 11);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (2, 1, 100, 100, 11);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (3, 3, 100, 100, 11);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (4, 4, 100, 100, 11);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (5, 1, 100, 100, 2);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (6, 2, 100, 100, 2);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (7, 3, 100, 100, 2);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (8, 4, 100, 100, 2);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (9, 1, 100, 100, 3);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (10, 2, 100, 100, 3);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (11, 3, 100, 100, 11);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (12, 4, 100, 100, 3);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (13, 1, 100, 100, 4);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (14, 2, 100, 100, 4);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (15, 3, 100, 100, 4);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (16, 4, 100, 100, 4);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (17, 1, 100, 100, 5);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (18, 2, 100, 100, 5);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (19, 3, 100, 100, 5);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (20, 4, 100, 100, 5);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (21, 1, 100, 100, 6);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (22, 2, 100, 100, 6);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (23, 3, 100, 100, 6);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (24, 4, 80, 100, 6);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (25, 1, 100, 100, 7);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (26, 2, 100, 100, 7);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (27, 3, 100, 100, 7);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (28, 4, 100, 100, 7);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (29, 1, 100, 100, 8);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (30, 2, 100, 100, 8);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (31, 3, 100, 100, 8);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (32, 4, 100, 100, 8);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (33, 1, 100, 100, 9);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (34, 2, 100, 100, 9);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (35, 3, 100, 100, 9);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (36, 4, 100, 100, 9);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (37, 1, 100, 100, 10);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (38, 2, 100, 100, 10);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (39, 3, 100, 100, 10);
insert into shelves (id, shelfLevel, capacity, maxCapacity, shelf_container_id) values (40, 4, 100, 100, 10);

insert into Products (id, Name, Price, Category) values (21, 'Lays', 1.5, 6);
insert into Products (id, Name, Price, Category) values (22, 'Cheetos', 1, 6);
insert into Products (id, Name, Price, Category) values (3, 'Delta Milk', 0.9, 7);
insert into Products (id, Name, Price, Category) values (4, 'Pavlidis', 1.5, 8);
insert into Products (id, Name, Price, Category) values (5, 'Molto', 0.7, 6);
insert into Products (id, Name, Price, Category) values (6, 'Chicken', 8.0, 3);
insert into Products (id, Name, Price, Category) values (7, 'Ifantis Turkey', 11.5, 3);
insert into Products (id, Name, Price, Category) values (8, 'Blue Cheese', 21.6, 9);
insert into Products (id, Name, Price, Category) values (9, 'Lettuce', 0.5, 0);
insert into Products (id, Name, Price, Category) values (10, 'Apples', 1.2, 1);
insert into Products (id, Name, Price, Category) values (11, 'Bread', 1, 6);
insert into Products (id, Name, Price, Category) values (12, 'CheesePie', 1.2, 6);
insert into Products (id, Name, Price, Category) values (13, 'Checken Nuggets', 1.2, 4);
insert into Products (id, Name, Price, Category) values (14, 'Nikas Turkey', 12.2, 3);
insert into Products (id, Name, Price, Category) values (15, 'Amita', 1.1, 10);
insert into Products (id, Name, Price, Category) values (16, 'Mustard', 1.4, 5);
insert into Products (id, Name, Price, Category) values (17, 'Mayonnaise', 1.2, 5);
insert into Products (id, Name, Price, Category) values (18, 'Oranges', 1.2, 1);
insert into Products (id, Name, Price, Category) values (19, 'Bananas', 1.7, 1);
insert into Products (id, Name, Price, Category) values (20, 'Beef', 10.2, 3);

insert into productPositions (id, quantity, shelfId, productId) values (21, 20, 41, 21);
insert into productPositions (id, quantity, shelfId, productId) values (2, 30, 41, 22);
insert into productPositions (id, quantity, shelfId, productId) values (3, 30, 41, 3);
insert into productPositions (id, quantity, shelfId, productId) values (4, 10, 22, 4);
insert into productPositions (id, quantity, shelfId, productId) values (5, 50, 3, 5);
insert into productPositions (id, quantity, shelfId, productId) values (6, 80, 7, 6);
insert into productPositions (id, quantity, shelfId, productId) values (7, 10, 4, 7);
insert into productPositions (id, quantity, shelfId, productId) values (8, 90, 6, 8);
insert into productPositions (id, quantity, shelfId, productId) values (9, 60, 9, 9);
insert into productPositions (id, quantity, shelfId, productId) values (10, 10, 41, 10);
insert into productPositions (id, quantity, shelfId, productId) values (11, 30, 25, 11);
insert into productPositions (id, quantity, shelfId, productId) values (12, 30, 35, 12);
insert into productPositions (id, quantity, shelfId, productId) values (13, 30, 38, 13);
insert into productPositions (id, quantity, shelfId, productId) values (14, 30, 40, 14);
insert into productPositions (id, quantity, shelfId, productId) values (15, 30, 12, 15);
insert into productPositions (id, quantity, shelfId, productId) values (16, 30, 29, 16);
insert into productPositions (id, quantity, shelfId, productId) values (17, 30, 27, 17);
insert into productPositions (id, quantity, shelfId, productId) values (18, 30, 31, 18);
insert into productPositions (id, quantity, shelfId, productId) values (19, 30, 39, 19);
insert into productPositions (id, quantity, shelfId, productId) values (20, 30, 24, 20);

insert into Person (person_id, firstname, lastname, email, phone_number, date_of_birth, gender, person_type, username, password, MoneySpent, points)
values (1, 'Manolis', 'Partsinevelos', 'partsinevelosm@gmail.com', '6978600994', '1990-01-01', 0, 'CUSTOMER', 'nck', '0000', 0.0, 0);

insert into Person (person_id, firstname, lastname, email, phone_number, date_of_birth, gender, person_type, username, password, MoneySpent, points)
values (2, 'Nikolaos', 'Stentoumis', 'stentoumisn@gmail.com', '6979900994', '1990-01-01', 0, 'CUSTOMER', 'nck', '0000', 0.0, 0);

insert into Person (person_id, firstname, lastname, email, phone_number, date_of_birth, gender, person_type, username, password, MoneySpent, points)
values (3, 'Xristos', 'Koutsogianopoulos', 'kchris.com', '6978440994', '1990-01-01', 0, 'CUSTOMER', 'nck', '0000', 0.0, 0);

insert into Person (person_id, firstname, lastname, email, phone_number, date_of_birth, gender, person_type, username, password, MoneySpent, points)
values (4, 'Antonis', 'Papadopoulos', 'anp@gmail.com', '6978110994', '1990-01-01', 0, 'CUSTOMER', 'nck', '0000', 0.0, 0);

insert into Person (person_id, firstname, lastname, email, phone_number, date_of_birth, gender, person_type, username, password, MoneySpent, points)
values (5, 'Panagiota', 'Antoniou', 'pana@gmail.com', '6978770994', '1990-01-01', 0, 'CUSTOMER', 'nck', '0000', 0.0, 0);

insert into Person (person_id, firstName, lastname, email, phone_number, date_of_birth, gender, username, password, Street, streetNumber, City, ZipCode, person_type, Department, Position, HiringDate)
values (6, 'Nick', 'Mitoglou', 'nmitoglou@gmail.com', '6971313221', '1990-01-01', 0, 'nck', '0000', 'Evrou', 5, 'Megara', '19100', 'EMPLOYEE', 'Sales', 'Employee', '1990-01-01');

insert into Person (person_id, firstName, lastname, email, phone_number, date_of_birth, gender, username, password, Street, streetNumber, City, ZipCode, person_type, Department, Position, HiringDate)
values (7, 'George', 'Stentoumis', 'gstm@gmail.com', '6971313441', '1990-01-01', 0, 'nck', '0000', 'Evrou', 5, 'Megara', '19100', 'EMPLOYEE', 'Sales', 'Director', '1990-01-01');

insert into Person (person_id, firstName, lastname, email, phone_number, date_of_birth, gender, username, password, Street, streetNumber, City, ZipCode, person_type, Department, Position, HiringDate)
values (8, 'Dimitris', 'Sloukas', 'dsloukas@gmail.com', '6971315531', '1990-01-01', 0, 'nck', '0000', 'Evrou', 5, 'Megara', '19100', 'EMPLOYEE', 'Sales', 'Employee', '1990-01-01');


insert into orders (id, order_date, order_start_window, order_stop_window, order_status, order_points, order_cost, customer_id, employee_id)
values (11, '1990-01-01', '1990-01-01', '1990-01-01', 0, 0, 19, 1, 6);

insert into orders (id, order_date, order_start_window, order_stop_window, order_status, order_points, order_cost, customer_id, employee_id)
values (2, '1990-01-01', '1990-01-01', '1990-01-01', 0, 0, 0.0, 2, 7);

insert into orders (id, order_date, order_start_window, order_stop_window, order_status, order_points, order_cost, customer_id, employee_id)
values (3, '1990-01-01', '1990-01-01', '1990-01-01', 0, 0, 0.0, 3, 8);

insert into orders (id, order_date, order_start_window, order_stop_window, order_status, order_points, order_cost, customer_id, employee_id)
values (4, '1990-01-01', '1990-01-01', '1990-01-01', 0, 0, 0.0, 1, 8);

insert into orders (id, order_date, order_start_window, order_stop_window, order_status, order_points, order_cost, customer_id, employee_id)
values (5, '1990-01-01', '1990-01-01', '1990-01-01', 0, 0, 0.0, 1, 6);

insert into orders (id, order_date, order_start_window, order_stop_window, order_status, order_points, order_cost, customer_id, employee_id)
values (6, '1990-01-01', '1990-01-01', '1990-01-01', 0, 0, 0.0, 5, 7);

insert into orders (id, order_date, order_start_window, order_stop_window, order_status, order_points, order_cost, customer_id, employee_id)
values (7, '1990-01-01', '1990-01-01', '1990-01-01', 0, 0, 0.0, 4, 6);

insert into orders (id, order_date, order_start_window, order_stop_window, order_status, order_points, order_cost, customer_id, employee_id)
values (8, '1990-01-01', '1990-01-01', '1990-01-01', 0, 0, 0.0, 2, 8);

insert into orders (id, order_date, order_start_window, order_stop_window, order_status, order_points, order_cost, customer_id, employee_id)
values (9, '1990-01-01', '1990-01-01', '1990-01-01', 0, 0, 0.0, 1, 7);

insert into orders (id, order_date, order_start_window, order_stop_window, order_status, order_points, order_cost, customer_id, employee_id)
values (10, '1990-01-01', '1990-01-01', '1990-01-01', 1, 0, 0.0, 4, 7);

insert into OrderLine (Id, ProductId, OrderId, quantity) values (19, 21, 11, 10);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (2, 22, 11, 4);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (3, 5, 2, 3);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (4, 7, 2, 2);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (5, 9, 3, 1);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (6, 10, 4, 4);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (7, 20, 4, 10);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (8, 17, 5, 11);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (9, 16, 5, 3);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (10, 12, 6, 14);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (11, 11, 7, 2);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (12, 13, 7, 7);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (13, 14, 8, 8);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (14, 4, 8, 3);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (15, 3, 8, 1);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (16, 6, 9, 7);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (17, 19, 10, 12);
insert into OrderLine (Id, ProductId, OrderId, quantity) values (18, 18, 10, 13);

insert into pickup (id, order_id, pickup_date, paymentMethod) values (11, 11, '1990-01-01', 0);
insert into pickup (id, order_id, pickup_date, paymentMethod) values (2, 2, '1990-01-01', 0);
insert into pickup (id, order_id, pickup_date, paymentMethod) values (3, 3, '1990-01-01', 0);
insert into pickup (id, order_id, pickup_date, paymentMethod) values (4, 4, '1990-01-01', 0);
insert into pickup (id, order_id, pickup_date, paymentMethod) values (5, 5, '1990-01-01', 0);
insert into pickup (id, order_id, pickup_date, paymentMethod) values (6, 6, '1990-01-01', 0);
insert into pickup (id, order_id, pickup_date, paymentMethod) values (7, 7, '1990-01-01', 0);
insert into pickup (id, order_id, pickup_date, paymentMethod) values (8, 8, '1990-01-01', 0);
insert into pickup (id, order_id, pickup_date, paymentMethod) values (9, 9, '1990-01-01', 0);
insert into pickup (id, order_id, pickup_date, paymentMethod) values (10, 10, '1990-01-01', 0);

