Domain Model in Detail
======================

The domain model is presented below

![Domain Model](/DomainModel/UML/DomainModel.png)

## Entities

The main entities/classes are the <code>Person</code>, which consists of  <code>Customers</code> and <code>Clients</code>, the  <code>Shelf</code>, the <code>Shelf Container</code> which contains the shelves, the  <code>Order</code>, the <code>Pickup</code> class and the  <code>Products</code>. There is also a class called <code>Order Status</code> which contains the diferrent stages of the order, the class <code>Payment Methods</code> which contains the available payment methods and the class<code>Customer Tier</code> with the different levels a customer might have. Last but not least there are the <code>OrderLine</code> and the <code>ProductPosition</code>.

## Correlations

* The customer-to-order correlation is 1..n by 1 because the order is placed by one customer and one customer places many orders. 

* The employee order correlation is 1 to many because the order is filled by one employee but one employee fills many orders. 

* Pickup to order correlation is 1 to 0..1 because since the order is not ready it cannot be received

* The shelves to Shelf Container is 1 to 1..n because a shelf container has 1 and more shelves but one shelf is contained in a rack.

* The shelf contains many products but one product is contained on one shelf so the correlation is one to many. 


Return to [home](/README.md).

Translated with DeepL.com (free version)