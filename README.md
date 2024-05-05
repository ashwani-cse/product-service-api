# product-service-api Microservice

This repository contains a Spring Boot project named **product-service-api**. The project is built using Maven and includes controllers, a repository, a service, and exception handling.

## Table of Contents
- [Overview](#overview)
- [Project Structure](#project-structure)
- [Usage](#usage)
- [Build](#build)

## Overview

The project consists of a Spring Boot application with the following components:

1. **Main Application Class:** `ProductServiceApiApplication`
    - Entry point for the application.
    - Annotated with `@SpringBootApplication`.

2. **Controller Class:** `ProductController`
    - RESTful controller handling product-related requests.

3. **Aspect Class:** `CategoryController`
     - RESTful controller handling category-related requests.

## Project Structure


## Usage

1. Clone the repository:

    ```bash
    git clone https://github.com/ashwani-cse/product-service-api.git
    ```

2. Build the project:

    ```bash
    mvn clean install -U
    ```
-U: It is a optional to update snapshots and releases.
3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

4. Access the API:

   Open a web browser or a tool like [Postman](https://www.postman.com/) and make a GET request to `http://localhost:8080/products/`.

## SQL Queries executed
```mysql
CREATE DATABASE IF NOT EXISTS product_service_flywaydb;
CREATE USER IF NOT EXISTS product_service_flywaydb_user IDENTIFIED BY 'product_service_flywaydb_pass';
GRANT ALL PRIVILEGES ON product_service_flywaydb.* TO product_service_flywaydb_user;
```
#### Make some security changes before exposing application to user. Because you may be exposed to SQL injection attacks.
  - Command to revoke all the privileges from the user associated with the application:
     ```mysql
      revoke all on product_service_flywaydb.* from 'product_service_flywaydb_user';
      ```
  - And give some necessary privileges to application to make changes to only data of the database.
     ```mysql
      grant select, insert, delete, update on product_service_flywaydb.* to 'product_service_flywaydb_user';
      ```
#### When you want to make changes to the database:
-  Re-grant permissions. 
- Change the spring.jpa.hibernate.ddl-auto =  update
- Re-run your applications.
  
Then repeat the two commands shown here to make your application safe for production use again. Better still, use a dedicated migration tool, such as Flyway or Liquibase.


#### OWASP Dependency-Check tool :
This is a popular open-source tool that helps identify project dependencies and check if they have known, publicly disclosed, vulnerabilities.
To identify and report known vulnerabilities in the dependencies of a Maven-based project execute below command - 
   ```bash
    mvn dependency-check:check 
   ```
Note: Use the NVD API key for fast scanning.

If you see any vulnerability, try to update with latest version of that dependency.
In this project i found vulnerability with jackson-databind-core in 2.15.x versions. 
So i updated pom with dependency management with latest jackson-databind core version.

Note: for github use to commit/push and pull
create a token from developer settings then in-place of password enter that token. Because from 2021, password has disabled for security reason.

## Stay Connected

Connect with us on social media and stay updated with the latest news and developments:

- [LinkedIn](https://www.linkedin.com/in/ashwanicse/)
- [Leetcode](https://leetcode.com/ashwani__kumar/)
- [Need Help? Schedule A Call](https://topmate.io/ashwanikumar)

## Subscribe to our Newsletter
Stay ahead of the curve by subscribing to our LinkedIn newsletter:
- [Subscribe Now](https://www.linkedin.com/newsletters/7084124970443767808/)

Experience the future of e-commerce with CommerceNexus - where innovation meets efficiency!
