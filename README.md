# Restaurant Review System

A Spring Boot 3.x REST API to manage **restaurants** and their **reviews**. This system supports CRUD operations, basic analytics, authentication, pagination, and API documentation.

---

## **Table of Contents**

- [Background](#background)  
- [Features](#features)  
- [Tech Stack](#tech-stack)  
- [Setup Instructions](#setup-instructions)  
- [API Endpoints](#api-endpoints)  
- [Authentication](#authentication)  
- [Testing](#testing)  
- [Database](#database)  
- [Swagger API Documentation](#swagger-api-documentation)  
- [Assumptions](#assumptions)  

---

## **Background**

This project was created as part of a **Spring Boot Developer Coding Challenge**. The goal was to build a **Restaurant Review API** that manages restaurants, customer reviews, and basic analytics like average ratings and top 3 restaurants by cuisine type.

---

## **Features**

- **Restaurant Management**  
  - Create, read, update, delete restaurants  
  - Properties: `name`, `cuisine`, `address`, `price range (LOW, MEDIUM, HIGH)`  

- **Review Management**  
  - Submit, update, delete, and list reviews  
  - Properties: `rating (1-5)`, `comment`, `visit date`, `status (PENDING, APPROVED)`  

- **Basic Analytics**  
  - Average rating per restaurant  
  - Top 3 restaurants by cuisine  

- **Technical Features**  
  - REST API following best practices  
  - Input validation and error handling  
  - Pagination for list endpoints  
  - Basic authentication (admin/user roles)  
  - Swagger/OpenAPI documentation  

---

## **Tech Stack**

- Java 17+  
- Spring Boot 3.x  
- Spring Data JPA  
- H2 in-memory database (for simplicity)  
- Spring Security (Basic Auth)  
- Swagger/OpenAPI (springdoc)  
- Maven  

---

## **Setup Instructions**

### **Prerequisites**
- Java 17+  
- Maven 3.x  

### **Clone the Repository**
```bash
https://github.com/theabhison/restaurant.git
cd restaurant-review-system
