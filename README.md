# ✈️ Trip Management System API

This project delivers a robust RESTful API for managing trip records, built to fulfill the **Badkul Technology Java Spring Boot + Hibernate Assignment**.

[![Java Version](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Database](https://img.shields.io/badge/Database-MySQL-orange.svg)](https://www.mysql.com/)
[![API Docs](https://img.shields.io/badge/API%20Docs-Swagger%20(OpenAPI)-6DB33F.svg)](https://swagger.io/)

---

## 🛠️ Technical Stack

* **Backend Framework:** Spring Boot 3.3.0
* **Language:** Java 17
* **Persistence:** Spring Data JPA / Hibernate
* **Database:** MySQL
* **Build Tool:** Apache Maven
* **API Documentation:** Springdoc OpenAPI (Swagger UI)
* **Testing:** JUnit 5 & Mockito

---

## 🚀 Getting Started

### 1. Prerequisites

Ensure the following tools are installed on your development machine:

* **Java Development Kit (JDK):** Version 17 or higher.
* **Apache Maven:** Version 3.x (Must be configured in your system's **PATH**).
* **MySQL Server:** Running instance on `localhost:3306`.
* **Git:** For cloning the repository.

### 2. Database Initialization

The application connects to a database named `tripdb`.

1.  **Configure Credentials:** Open `src/main/resources/application.yml` and update the `spring.datasource.password` field to match your MySQL server password.
2.  **Initialize Schema:** Execute the SQL script located at `src/main/resources/tripdb.sql` using MySQL Workbench or the command line. This creates the necessary tables and inserts sample data for immediate testing.

### 3. Build and Run Application

Navigate to the project's root directory (where `pom.xml` is located) in your terminal.

| Step | Command | Purpose |
| :--- | :--- | :--- |
| **Build** | `mvn clean install -DskipTests` | Compiles the source code and packages the application into a single, executable JAR file. |
| **Run** | `java -jar target/trip-app-0.0.1-SNAPSHOT.jar` | Executes the JAR file, starting the Spring Boot server on `http://localhost:8080`. |

---

## 🧪 Testing and Endpoints

### 1. API Documentation (Swagger UI)

All API functionality, DTO schemas, and validation rules are documented via Swagger. This is the primary interface for testing.

**URL:** `http://localhost:8080/swagger-ui.html`

### 2. Key Functional Endpoints

The API supports all nine functional requirements:

| Feature | Method | Endpoint | Example Usage |
| :--- | :--- | :--- | :--- |
| **CRUD Operations** | `POST/GET/PUT/DELETE` | `/api/trips` | Full management of trip records with custom Date Validation (`endDate > startDate`). |
| **Pagination** | `GET` | `/api/trips` | Access paginated results: `/api/trips?page=0&size=10&sort=price,asc` |
| **Get Summary** | `GET` | `/api/trips/summary` | Calculates and returns Min, Max, and Average Price, and Total Trips. |
| **Search/Filter** | `GET` | `/api/trips/search` | Search by destination (case-insensitive): `/api/trips/search?destination=tokyo` |
| **Filter by Status** | `GET` | `/api/trips/filter` | Filter by status enum: `/api/trips/filter?status=PLANNED` |
| **Date Range** | `GET` | `/api/trips/daterange` | Find trips between two dates: `/api/trips/daterange?start=2025-10-01&end=2026-01-01` |

### 3. Unit Testing

The business logic in the Service layer is tested using JUnit 5 and Mockito (in `TripServiceTest.java`).


# Run all project tests
mvn test
---
## 4. Project Structure
```bash
tripapp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/tripapp/
│   │   │       ├── config/              # Configuration files (e.g., SwaggerConfig.java)
│   │   │       ├── controller/          # REST API Endpoints (TripController.java)
│   │   │       ├── dto/                 # Data Transfer Objects (TripRequest.java, TripResponse.java)
│   │   │       ├── entity/              # JPA Model (Trip.java)
│   │   │       ├── exception/           # Custom Exceptions & Global Handler
│   │   │       ├── repository/          # Data Access Layer (TripRepository.java)
│   │   │       └── service/             # Business Logic Layer (TripService.java, TripServiceImpl.java)
│   │   └── resources/
│   │       ├── application.yml          # Spring & Database Configuration
│   │       └── tripdb.sql               # Database Initialization Script
│   └── test/
│       └── java/
│           └── com/example/tripapp/
│               └── service/             # Unit Tests (TripServiceTest.java)
└── pom.xml                              # Maven Configuration File
```
### Visiualization or reports
