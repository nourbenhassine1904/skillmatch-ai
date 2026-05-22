# SkillMatch AI - Spring Boot Backend

SkillMatch AI is a Spring Boot backend project for managing users, skills, and intelligent skill recommendations.

## Current Features

- CRUD Skill
- CRUD User
- Many-to-Many relationship between User and Skill
- DTO and Mapper layer
- Validation and exception handling
- Missing skills recommendation endpoint

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Validation
- Lombok
- Maven

## Main Endpoint

```http
GET /api/recommendations/users/{userId}/missing-skills
````
## How to Run
./mvnw spring-boot:run

## On Windows:
mvnw.cmd spring-boot:run
