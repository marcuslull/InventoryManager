# Inventory Management System
## Introduction

This document outlines the core requirements for the backend of an inventory management system, 
focusing on efficient data handling and persistence.  

The system will be used to track and manage inventory for a small to medium-sized business. 
The initial focus is on the Model, Service, and Database layers, 
with data ingestion from the Controller layer simulated using simple data structures.

## Functional Requirements

### Product Management

* Add new products:  
  * Product Name (required, string)
  * SKU (Stock Keeping Unit - required, string, unique)
  * Quantity in stock (required, integer)
  * Unit price (required, decimal)
  * Category (required, string, from a predefined list)
  * Supplier (required, string, from a predefined list)  
* Update existing products:  
  * Allow updating all product attributes.
* Delete products
* Search products
  * By name or SKU.
* View product details

### Inventory Tracking

* Track stock levels
  * Accurately track the quantity of each product.
* Update stock levels
  * Manually or through simulated order processing.
* Low stock alerts
  * Generate alerts when stock falls below a threshold.  

### Supplier Management

* Add new suppliers
  * Supplier Name (required, string)
* Update existing suppliers
* Delete suppliers

### Category Management

* Add new categories
  * Category Name (required, string)
* Update existing categories
* Delete categories

### Reporting

* Inventory reports
  * Current stock levels and low stock items.

## Non-Functional Requirements

* Database: Relational database (e.g., PostgreSQL, MySQL).  
* Data Access: Hibernate as the JPA implementation.  
* Data Integrity: Ensure data integrity through database design and constraints.  
* Efficiency: Efficient data processing and retrieval.  

## Technical Requirements

* Java:
* SQL: (DDL and DML)
* Hibernate: For ORM.
* Testing: Unit tests.