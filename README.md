# Parking Booking System

This project is a web-based application developed to address common urban parking challenges. It aims to optimize the process of searching for parking spaces, managing reservations, and handling payments transparently for both drivers and parking owners.

## 🛠 Tech Stack
- **Backend:** Java 21, Spring Boot 3.5.x
- **Database:** MySQL 8.0 (InnoDB engine)
- **Data Access:** Spring Data JDBC
- **Build Tool:** Maven
- **Libraries:** Lombok (Auto Getters/Setters)
- **Testing Tools:** Postman / REST Client

## 🚀 Project Progress - Week 4 (Core Backend)
Currently, the project has successfully implemented the core objectives of the Backend layer:
- [x] **Project Structure:** Established a 3-Tier Architecture (Controller - Service - Repository).
- [x] **Database Integration:** Successfully connected to MySQL via JDBC with precise schema mapping.
- [x] **Authentication & Authorization (RBAC):** - Email-based Registration and Login.
    - Handled the Generalization hierarchy (ISA): Automatically categorizes and inserts data into the `Driver` or `Parking_Owner` tables upon registration based on the selected role.
- [x] **Base REST APIs:** - User Management (`User`)
    - Parking Lot Management (`Parking_Lot`)
    - Parking Slot Management (`Parking_Slot`)
    - Basic Reservation Process (`Booking`)

## 📋 Installation & Setup Guide

### 1. Prerequisites
Ensure you have the following installed on your machine:
- **Java JDK 21**
- **Maven 3.9.x** or higher
- **MySQL Server 8.0**

### 2. Database Setup
1. Log in to MySQL and create the database:
   ```sql
   CREATE DATABASE parking_system;
   ```
2. Execute the **init_schema.sql** file to initialize the tables:
    ```Bash
    # Run this command in the project root directory
    mysql -u root -p parking_system < init_schema.sql
    ```
### 3. Application Configuration
 - Open the src/main/resources/application.properties file and update your MySQL credentials:
    ```Properties
    spring.datasource.url=jdbc:mysql://localhost:3306/parking_system?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    spring.datasource.username=root
    spring.datasource.password=your_mysql_password
    ```
### 4. Running the Application
- Open the Terminal in the project directory and run:
```Bash
mvn spring-boot:run
```
- The application will be hosted at: http://localhost:8080
### 🧪 Testing
The project includes a test_payloads.json file containing JSON data samples for testing via Postman.
- Auth: /api/auth/register, /api/auth/login
- Parking Lots: /api/parking-lots
- Parking Slots: /api/parking-slots
- Bookings: /api/bookings
### 👥 Team Members
- **Hoan**: Requirement gathering & Project planning.

- **Duc Anh**: UML & ERD Design.

- **Nguyen**: Database Schema design & Normalization.

- **Pham Trung Kien ITCSIU23020**: Backend development & REST APIs.