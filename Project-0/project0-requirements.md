# Somekind of Shop

## Description

   The Somekind of Shop app is a console-based application that facilitates the purchasing of some kind of item, the choice is up to you. 
	An employee can add these items to an inventory and manage offers for those those items while a customer can view the available items and make offers.
	
## Purpose

   We want to see that you can meet deadlines and that you can code. You are expected to complete the following requirements and give a 5 minute presentation of your project to our QC team.

## Requirements
1. Functionality should reflect the below user stories.(payments)
2. Data is stored in a database.(checked)
3. Data Access is performed through the use of JDBC in a data layer consisting of Data Access Objects.(checked)
4. All input is received using the java.util.Scanner class.(checked)
5. All public service layer methods must have at least one JUnit test.(checked)
6. Log4j is implemented to log events to a file.(checked)


## User Stories
Total Points: 25 Points

* As a user, I can login. (done)
	* 2 points
* As an employee, I can add an item to the shop.(done)
	* 3 points
* As a customer, I can view the available items. (done)
	* 1 point
* As a customer, I can make an offer for an item.(done)
	* 3 points
* As an employee, I can accept or reject a pending offer for an item.(done)
	* 2 points
* As the system, I update an item to an owned state when an offer is accepted.(done)
	* 2 points
* As the system, I reject all other pending offers for an item when an offer is accepted.(done)
	* 3 points
* As a user, I can register for a customer account.(done)
	* 3 points
* As an employee, I can remove an item from the shop.(done)
	* 2 points
* As a customer, I can view the items that I own.(done)
	* 1 point
* As a customer, I can view my remaining payments for an item.(done)
	* 1 point
* As an employee, I can view all payments.(done)
	* 1 point
* As the system, I can calculate the weekly payment.(done)
	* 1 point

## Bonus

* As a user, I can make a payment. (done)
* As a manager, I can make employee accounts. (done)
* As an employee, I can edit existing items. (done)
* As a manager, I can fire employees. (done)
* As a manager, I can view sales history of all offers.(log off all offers and its final status) (done)

## Extra bonus

Be creative and incorporate other topics into your project, they do not have to have been covered by our curriculum. Some suggestions:
* File I/O(?)
* Threading(counting time)
* Mocking(mojito for unit test)
* Password hashing(Salt)
