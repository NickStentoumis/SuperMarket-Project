Super Market Support Service
=================================

This application aims to automate the various processes and functions of a super market.

This version has been developed with the Quarkus framework.

It leverages JPA and Hibernate as a JPA Provider to support database access. It provides as REST services by leveraging the JAX-RS API. It also uses the MapStruct tool to generate JSON representations of REST services from field logic objects.

Basic Functions.
-------------------

The Application manages the available products of a super market where customers can place orders and employees can check and execute them based on the availability of products.  

The employee - administrator, can check the stock on the shelves and add products where shortages occur. He also has the ability to change product prices and control pending orders. He can also add and remove products from the store's stock. 

For each pending order, the system displays an optimal picking routing plan within the store in order to gather products efficiently.

As customers purchase products, they are removed from their respective shelves. In addition, a reward system is implemented for the customers of the super market, where depending on their purchases they accumulate points which are translated into a discount on subsequent purchases. 


Software Requirements.
---------------------

### Shelf feedback

* The staff has the possibility to check the stock on each shelf and add products to it if a shortage is detected. 

* Each order removes the products contained in it from the corresponding shelves.

* Each product is placed on exactly one shelf/rack level and each shelf accommodates various products.

* Supermarket shelves are identified by a unique identifier and are characterized by location information such as floor, aisle, level.

* Staff knows at all times which products are placed on each shelf and their availability.

### Orders

* Customers can place an order online, which they will pay for and receive in the store.

* When submitting the order, the time interval (to the nearest hour, e.g. 1-2pm) within which the order will be picked up is also indicated.

* Store employees will be able to view pending orders to be fulfilled for the next hour.

* For each pending order, the system displays an optimal routing plan within the store in order to assemble the products efficiently.

* An order can be pending (not yet processed), in progress (an employee has started processing it), ready for delivery (the customer can pick up) and completed (delivered and paid by the customer).

## Entity and Relationship Analysis

The classes customer and employee inherit from the person their common attributes in addition to their own distinct attributes. 

### Employee

* Each employee performs several orders each order is assigned to a particular employee. 

* The clerk can control which orders are to be executed in the next hour.

* The clerk checks and updates their store products.

* The clerk checks the shelves, can change product features and fulfills orders. 

### Customer

* Each customer submits zero or more orders. 

* The "Payment Methods" class contains the payment methods available to the customer and is an attribute for the pickup class. 

* When the order is submitted, a discount is calculated for the customer based on their available points.

### Order

* Orders contain products which are contained in the shelves

* The Order Status class contains the different stages of the order and is an attribute of the Order class

* The Order has a direct correlation with the receipt which is characterized by the date and method of payment. 

* There are two available payment methods, cash and card.

* For each order, an optimal way for the employee to harvest the products is calculated.

### Shelf

* A shelf contains several shelves but a shelf is contained on a specific shelf.

* Shelves contain products. 

* A shelf contains many products but each product is contained on a particular shelf. 

### Product

* Each product consists of a number of pieces

* Products are contained on shelves

 ## Assumptions

* Each entity such as Employee, Customer, etc. is characterized by a unique identifier, which is not represented in the field model. 

* Payment and receipt of the order are assumed to be done simultaneously when the order is received by the store. 

* Each shelf level has a specific capacity, which is identical for each product category. This means that each product is considered to occupy the same shelf space, regardless of its size. Size is not a characteristic of the products

* Only two payment methods are supported, cash or card, which are part of the 'Payment methods' class

* Each pickup is mono-associated with an order. Multiple orders cannot be received.


Domain model
--------------

Below is the class diagram with the entities, their attributes and the relationships between them.

![Field Model](/DomainModel/UML/DomainModel.png)

More about the domain model [here](/DomainModel/DomainModel.md)(/DomainModel/DomainModel.md).

The diagram was created via [Visual Studio Code](https://code.visualstudio.com/), using the [UMLet](https://www.umlet.com/) plugin