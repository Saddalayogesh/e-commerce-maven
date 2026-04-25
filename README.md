# Java E-Commerce Console Application 

📌 Overview

This is a Java Console Based E-Commerce Application built using Core Java, OOP, Collections, File Handling, and CSV Data Loading.

The project demonstrates real-world backend architecture using:

- Layered Architecture
- Factory Design Pattern
- MVC Structure
- CRUD Operations
- Exception Handling
- Java Streams API
- CSV File Parsing

---

🚀 Features

👤 Customer Module

- Customer Registration
- Customer Login
- View Account Details
- Update Customer Profile
- Delete Customer Account

🔐 Authentication Module

- Signup
- Login
- Email Validation
- Duplicate Customer Check

📦 Product Module

- Add Product
- View Product by ID
- View All Products
- Update Product
- Delete Product


---

## 🧱 Project Structure


src/main/java/com/java/ecommerce
│
├── controller

│   ├── AuthController.java

│   ├── CustomerController.java

│   └── ProductController.java

│
├── service

│   ├── AuthService.java

│   ├── CustomerService.java

│   ├── ProductService.java

│   └── Impl Classes
│
├── repository
│   ├── CustomerRepository.java

│   └── ProductRepository.java
│
├── model
│   ├── Customer.java

│   ├── Product.java

│   └── Address.java
│
├── enums

│   ├── Gender.java

│   ├── Status.java
│   └── Membership.java

│
├── factory

│   └── AppFactory.java
│
├── util

│   ├── CsvParser.java

│   └── InputUtil.java

│
└── ui

    ├── AuthUI.java
    
    ├── CustomerUI.java
    
    ├── ProductUI.java
    
    └── DashboardUI.java

---

💾 CSV Files Used

Place inside:

src/main/resources/

Files:

- products.csv
- customers.csv

---

🛠 Technologies Used

- Java 17+
- Core Java
- Collections Framework
- Streams API
- CSV Parsing
- IntelliJ IDEA 

---

▶️ Run Project

Compile

javac Main.java

Run

java Main

---

🧠 Concepts Covered

- OOP Principles
- Encapsulation
- Inheritance
- Abstraction
- Polymorphism
- Interfaces
- Exception Handling
- Collections
- Streams API
- File Handling
- Clean Code Structure

---

📷 Sample Menu

1. Signup
2. Login
3. View Products
4. Add Product
5. Update Product
6. Delete Product
7. Exit

---

🎯 Future Improvements

- JDBC + MySQL Integration
- Spring Boot REST API
- JWT Authentication
- GUI Version
- Cart & Orders Module
- Payment Gateway

---

👨‍💻 Author

Developed by Yogesh

---
