🚀 Getting Started
1. Prerequisites
Ensure the following tools are installed on your development machine:

Java Development Kit (JDK): Version 17 or higher.

Apache Maven: Version 3.x (Must be configured in your system's PATH).

MySQL Server: Running instance on localhost:3306.

Git: For cloning the repository.

2. Database Initialization
The application connects to a database named tripdb.

Configure Credentials: Open src/main/resources/application.yml and update the spring.datasource.password field to match your MySQL server password.

Initialize Schema: Execute the SQL script located at src/main/resources/tripdb.sql using MySQL Workbench or the command line. This creates the necessary tables and inserts sample data for immediate testing.

3. Build and Run Application
Navigate to the project's root directory (where pom.xml is located) in your terminal.

Step	Command	Purpose
Build	mvn clean install -DskipTests	Compiles the source code and packages the application into a single, executable JAR file.
Run	java -jar target/trip-app-0.0.1-SNAPSHOT.jar	Executes the JAR file, starting the Spring Boot server on http://localhost:8080.

Export to Sheets
🧪 Testing and Endpoints
1. API Documentation (Swagger UI)
All API functionality, DTO schemas, and validation rules are documented via Swagger. This is the primary interface for testing.

URL: http://localhost:8080/swagger-ui.html

2. Key Functional Endpoints
The API supports all nine functional requirements:

Feature	Method	Endpoint	Example Usage
CRUD Operations	POST/GET/PUT/DELETE	/api/trips	Full management of trip records with custom Date Validation (endDate > startDate).
Pagination	GET	/api/trips	Access paginated results: /api/trips?page=0&size=10&sort=price,asc
Get Summary	GET	/api/trips/summary	Calculates and returns Min, Max, and Average Price, and Total Trips.
Search/Filter	GET	/api/trips/search	Search by destination (case-insensitive): /api/trips/search?destination=tokyo
Filter by Status	GET	/api/trips/filter	Filter by status enum: /api/trips/filter?status=PLANNED
Date Range	GET	/api/trips/daterange	Find trips between two dates: /api/trips/daterange?start=2025-10-01&end=2026-01-01

Export to Sheets
3. Unit Testing
The business logic in the Service layer is tested using JUnit 5 and Mockito (in TripServiceTest.java).

Bash

# Run all project tests
mvn test
🏗️ Project Structure
The project adheres to the standard Spring Boot layered architecture:

trip-app/
├── src/main/java/com/example/tripapp/
│   ├── config/             # Swagger/OpenAPI Configuration
│   ├── controller/         # REST Endpoints (Works with DTOs)
│   ├── dto/                # Data Transfer Objects (API Contract & Validation)
│   ├── entity/             # JPA Entity (Database Model)
│   ├── exception/          # Custom Exception Handling
│   ├── repository/         # Data Access Layer (JPA/Custom Queries)
│   └── service/            # Business Logic Implementation
├── src/main/resources/
│   ├── application.yml     # Spring Configuration & Database Credentials
│   └── tripdb.sql          # Database Initialization Script (Deliverable)
└── pom.xml   