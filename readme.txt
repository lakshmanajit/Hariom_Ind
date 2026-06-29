# Hariom_Ind - Quality Control Management System

## Project Overview

		 Hariom_Ind is a Spring Boot based Quality Control Management System developed to manage manufacturing quality inspection processes.
		 The application provides master management, lot batch management, QC result tracking, dashboard statistics, 
		 and report generation in Excel and PDF formats.

---

## Technology Stack

### Backend

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* Spring Security
* Hibernate
* Maven

### Database

* MySQL

### Reporting

* Apache POI (Excel Report)
* iText PDF (PDF Report)

### Authentication

* JWT Authentication
* Role Based Access Control

---

## Modules Implemented

### Authentication Module

* User Login
* JWT Token Generation
* Secure APIs

### Master Modules

* Product Master
* Stage Master
* Defect Master
* Inspection Master

Each module supports:

* Add
* Update
* Delete
* Get By Id
* Get All
* Pagination

---

### Lot Batch Module

Features:

* Add Lot Batch
* Update Lot Batch
* Delete Lot Batch
* View All Lot Batches
* Product Mapping

---

### QC Result Module

Features:

* Add QC Result
* Update QC Result
* Delete QC Result
* View All QC Results

Business Validations:

* PASS result does not require defect details.
* FAIL result requires defect and defect quantity.
* Duplicate QC Result is restricted for the same Lot Batch and Inspection.

---

### Dashboard Module

Dashboard Summary includes:

* Total Products
* Total Stages
* Total Defects
* Total Inspections
* Total Lot Batches
* Total QC Results
* PASS Count
* FAIL Count

---

### Reports Module

Implemented Reports:

* QC Result Excel Report
* QC Result PDF Report

Technologies Used:

* Apache POI
* iText PDF

---

## REST APIs

Authentication

* Login

Product

* Add Product
* Update Product
* Delete Product
* Get Product
* Get All Products

Stage

* Add Stage
* Update Stage
* Delete Stage
* Get Stage
* Get All Stages

Defect

* Add Defect
* Update Defect
* Delete Defect
* Get Defect
* Get All Defects

Inspection

* Add Inspection
* Update Inspection
* Delete Inspection
* Get Inspection
* Get All Inspections

Lot Batch

* Add Lot Batch
* Update Lot Batch
* Delete Lot Batch
* Get All Lot Batches

QC Result

* Add QC Result
* Update QC Result
* Delete QC Result
* Get All QC Results

Dashboard

* Dashboard Summary API

Reports

* Excel Report Download
* PDF Report Download

---

## Project Structure

src/main/java

* controller
* service
* repository
* entity
* DTO
* security
* util
* configuration

---

## Features

* REST APIs
* JWT Authentication
* CRUD Operations
* Pagination
* Dashboard
* Excel Export
* PDF Export
* Validation Logic
* Entity Relationships
* Exception Handling

---

## Future Enhancements

* Search APIs
* Filter APIs
* Dashboard Charts
* Email Notifications
* Approval Workflow
* Swagger Documentation
* Unit Testing
* Docker Support

---

## How to Run

1. Clone the repository.
2. Configure MySQL database.
3. Update application.properties.
4. Run Maven build.
5. Start the Spring Boot application.
6. Access APIs using Postman.

---

#--------------------------------------Author-------------------------------

			Ajeet Kumar Yadav
			
			Software Engineer
			
			Java | Spring Boot | Hibernate | REST API | MySQL

-------------------------------------------------------------------------------

## Version

Version 1.0
