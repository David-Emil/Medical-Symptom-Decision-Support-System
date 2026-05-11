# Medical-Symptom-Decision-Support-System
A desktop-based Medical Symptom Decision Support System (DSS) developed using Java and MySQL. The system helps analyze patient symptoms and assists in predicting possible diseases using a database-driven decision engine.

The project demonstrates healthcare data management, decision support concepts, database design, and Java desktop application development.

---

## Features

### Medical Diagnosis Features
- Symptom-based disease prediction
- Patient diagnosis history tracking
- Disease and symptom management
- Decision support engine for diagnosis
- Healthcare database integration

### Admin Features
- Add and manage diseases
- Add and manage symptoms
- Link diseases with symptoms
- View diagnosis history
- Patient data handling

### Technical Features
- Modular Java architecture
- DAO Design Pattern implementation
- MySQL database integration
- Swing-based graphical user interface
- ERD and database mapping diagrams included

---

## Technologies Used

### Programming Language
- Java

### Database
- MySQL

### GUI
- Java Swing

### Tools & Technologies
- Maven
- JDBC
- DAO Pattern
- Decision Support Logic
- SQL

---

## Project Structure

```bash
Medical-Symptom-DSS/
│
├── src/main/java/com/medical/
│   ├── dao/          # Database Access Objects
│   ├── model/        # Data Models
│   ├── service/      # Decision Engine Logic
│   ├── util/         # Database Utilities
│   └── view/         # Swing GUI Panels
│
├── Diagrams/         # ERD & Mapping Diagrams
│
├── Creation & Insertion V01.sql
│
└── pom.xml

System Architecture

The system follows a layered architecture:

Presentation Layer (Swing GUI)
Service Layer (Decision Engine)
Data Access Layer (DAO Classes)
Database Layer (MySQL)

This architecture improves maintainability, scalability, and code organization.

Installation & Setup
Prerequisites
Java JDK 17+
MySQL Server
Maven
IDE (NetBeans / IntelliJ / Eclipse)
Database Setup
Create a MySQL database.
Import the SQL script:
Creation & Insertion V01.sql
Update database credentials inside:
src/main/java/com/medical/util/DBConnection.java
Run the Project
Open the project in your IDE.
Install Maven dependencies.
Run the main application.
Included Diagrams

The project includes:

Entity Relationship Diagram (ERD)
Database Mapping Diagram
Draw.io editable diagram files

These diagrams help explain the system database structure and relationships.

Decision Support Concept

The system works by:

Receiving selected symptoms from the user.
Matching symptoms against stored disease data.
Calculating possible disease matches.
Displaying the most relevant diagnosis results.

This simulates a simplified clinical decision support system used in healthcare environments.

Future Improvements
Machine Learning integration for diagnosis prediction
Web-based version using Spring Boot
Authentication system for doctors/admins
Advanced patient reporting
Cloud database hosting
REST API integration
Medical recommendation engine
Educational Objectives

This project demonstrates:

Decision Support Systems (DSS)
Healthcare Informatics
Database Design
Java Desktop Development
Software Architecture Concepts
DAO Pattern Usage
SQL & JDBC Integration
Author

Developed as a Medical Decision Support System academic/software engineering project.

License

This project is intended for educational and learning purposes.
---

# Extra GitHub Tips

Before uploading to GitHub, make sure to:

- Remove `node_modules` folder
- Remove `.vs` folder
- Add a proper `.gitignore`
- Hide sensitive data like connection strings or API keys
- Push backend and frontend together in one repository

Recommended `.gitignore` examples:

```gitignore
node_modules/
.vs/
bin/
obj/
.env
---
