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
    mvn clean install
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

4. Access the API:

   Open a web browser or a tool like [Postman](https://www.postman.com/) and make a GET request to `http://localhost:8080/products/`.

## SQL Queries executed
```mysql
create database product_service_api;
create user product_service_api_user IDENTIFIED BY 'product_service_api_user';
GRANT ALL PRIVILEGES ON product_service_api.* TO product_service_api_user;
SELECT user FROM mysql. user
```
#### Make some security changes before exposing application to user. Because you may be exposed to SQL injection attacks.
  - Command to revoke all the privileges from the user associated with the application:
     ```mysql
      revoke all on product_service_api.* from 'product_service_api_user';
      ```
  - And give some necessary privileges to application to make changes to only data of the database.
     ```mysql
      grant select, insert, delete, update on product_service_api.* to 'product_service_api_user';
      ```
#### When you want to make changes to the database:
-  Regrant permissions. 
- Change the spring.jpa.hibernate.ddl-auto =  update
- Re-run your applications.

Then repeat the two commands shown here to make your application safe for production use again. Better still, use a dedicated migration tool, such as Flyway or Liquibase.

## Social Profile
- [LinkedIn](https://www.linkedin.com/in/ashwanicse/)
- [Leetcode](https://leetcode.com/ashwani__kumar/)
- [Topmate](https://topmate.io/ashwanikumar)
## Linkedin Newsletter
- [Subscribe](https://www.linkedin.com/newsletters/7084124970443767808/)
