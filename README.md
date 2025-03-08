# Employee-Managaement-System

Overview

The Employee Management System is a web-based application built with Spring Boot, Thymeleaf, and MySQL. It provides features for managing employees, assigning managers, and viewing employees under specific managers. The system allows CRUD operations on employees, filtering, searching, and manager assignments.

Features:

1) Add, Update, Delete employees
2) Assign managers to employees
3) View employees under specific managers
4) Search employees by name (firstname/lastname)
5) Responsive web UI with Thymeleaf and Bootstrap

Technologies Used:

1)Spring Boot 3.3.0
2)Spring Data JPA (for database operations)
3)Thymeleaf (for UI rendering)
4)MySQL (as database)
5)Bootstrap 5 (for frontend styling)
6)Maven (for dependency management)

Project Structure:

/employee-management-app
├── src/main/java/codingtechniques
│   ├── model/Employee.java
│   ├── repository/EmployeeRepository.java
│   ├── service/EmployeeService.java
│   ├── service/EmployeeServiceImpl.java
│   ├── controller/EmployeeController.java
│   ├── controller/SystemInfoController.java
│   ├── EmployeeManagementAppApplication.java
├── src/main/resources/templates
│   ├── employees.html
│   ├── registration.html
│   ├── updateForm.html
│   ├── managers.html
│   ├── employees-under-manager.html
├── src/main/resources/application.properties
├── pom.xml
├── README.md

Contributor:

Khushi Chourasia - https://github.com/Khushi-Chourasia
