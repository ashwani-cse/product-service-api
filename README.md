# fakestoreapi.com Consumer App

This repository contains a Spring Boot project named **fakestore-api-consumer** that serves as a consumer for a FakeStore API. The project is built using Maven and includes controllers, a repository, a service, and exception handling.

## Table of Contents
- [Overview](#overview)
- [Project Structure](#project-structure)
- [Usage](#usage)
- [Aspect Details](#aspect-details)
- [Dependencies](#dependencies)
- [Build](#build)

## Overview

The project consists of a Spring Boot application with the following components:

1. **Main Application Class:** `ConsumingFakestoreApiApplication`
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
    git clone <repository-url>
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


## Dependencies

- Spring Boot Starter Actuator
- Spring Boot Starter Web
- Spring Boot DevTools (runtime)
- Project Lombok (optional)
- Spring Boot Starter Test (for testing)


## Social Profile
- [LinkedIn](https://www.linkedin.com/in/ashwanicse/)
- [Leetcode](https://leetcode.com/ashwani__kumar/)
- [Topmate](https://topmate.io/ashwanikumar)
## Linkedin Newsletter
- [Subscribe](https://www.linkedin.com/newsletters/7084124970443767808/)
