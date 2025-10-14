-- This script creates the 'tripdb' database if you are not using the 
-- 'createDatabaseIfNotExist=true' setting in application.yml.
-- If using Spring Boot's automatic creation, this step is often optional.

-- 1. DROP and CREATE DATABASE
DROP DATABASE IF EXISTS tripdb;
CREATE DATABASE tripdb;
 USE tripdb;

-- 2. CREATE TABLE
-- Note: Spring Data JPA (ddl-auto: update) usually creates this table, 
-- but this script provides the schema definition for documentation/manual setup.
CREATE TABLE IF NOT EXISTS trips (
    id INT AUTO_INCREMENT PRIMARY KEY,
    destination VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    price DOUBLE NOT NULL CHECK (price > 0),
    status ENUM('PLANNED', 'ONGOING', 'COMPLETED') NOT NULL
);

-- 3. INSERT Sample Data (Optional, but useful for testing)
INSERT INTO trips (destination, start_date, end_date, price, status) VALUES 
('Paris', '2025-10-20', '2025-10-30', 1200.00, 'PLANNED'), 
('Tokyo', '2025-11-05', '2025-11-15', 2500.50, 'PLANNED'), 
('New York', '2024-05-01', '2024-05-07', 900.00, 'COMPLETED'), 
('London', '2025-06-15', '2025-06-25', 1800.00, 'ONGOING');