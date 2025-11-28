📌 Employee & Task Management REST API

A complete Spring Boot application providing RESTful APIs to manage Employees and their Tasks, with full CRUD operations, using a MySQL real database.
This project demonstrates clean architecture using:

Controller Layer

Service Layer

Repository Layer

Entity Layer

It follows best practices with JPA, Hibernate, Lombok, and structured API design.

🚀 Features
✅ Employee Management

Create New Employee

Get All Employees

Get Employee by ID

Update Employee Details

Delete Employee

✅ Task Management

Assign Task to Employee

Create Unassigned Task

Update Task Status

View All Tasks

View Tasks Assigned to Specific Employee

Delete Task

🔗 Employee–Task Relationship

One Employee → Many Tasks

Supports tasks assigned or unassigned (nullable relationship)

🏗 Technologies Used
Technology	Purpose
Java 17+	Programming Language
Spring Boot	REST API Framework
Spring Data JPA	ORM + Database Access
MySQL	Database
Hibernate	JPA implementation
Lombok	Reduce boilerplate code
Postman	API Testing
📁 Project Structure
src/main/java/com/projects/employee_server
│
├── controller
│   ├── EmployeeController.java
│   └── TaskController.java
│
├── service
│   ├── EmployeeService.java
│   └── TaskService.java
│
├── repository
│   ├── EmployeeRepository.java
│   └── TaskRepository.java
│
└── entity
    ├── Employee.java
    └── Task.java

🗄 Database Schema (MySQL)
employees
Column	Type
id	BIGINT (PK)
name	VARCHAR
email	VARCHAR
phone	VARCHAR
department	VARCHAR
tasks
Column	Type
id	BIGINT (PK)
title	VARCHAR
description	TEXT
status	VARCHAR
employee_id	BIGINT (FK → employees.id)
⚙ How to Run the Project
1️⃣ Clone the Repository
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>

2️⃣ Configure MySQL in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/Employee_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

3️⃣ Run the Application
mvn spring-boot:run


Application starts at:
👉 http://localhost:8081

📬 API Endpoints
📌 Employees
Method	Endpoint	Description
POST	/api/employees	Create employee
GET	/api/employees	Get all employees
GET	/api/employees/{id}	Get employee by ID
PUT	/api/employees/{id}	Update employee
DELETE	/api/employees/{id}	Delete employee
📌 Tasks
Method	Endpoint	Description
POST	/api/tasks	Create task
GET	/api/tasks	Get all tasks
GET	/api/tasks/{id}	Get task by ID
PUT	/api/tasks/{id}	Update task
DELETE	/api/tasks/{id}	Delete task
GET	/api/employees/{id}/tasks	Tasks assigned to employee
🧪 Example JSON for Testing
Create Employee
{
  "name": "David Miller",
  "email": "david.miller@example.com",
  "phone": "9911223344",
  "department": "IT"
}

Create Task
{
  "title": "Write REST API Docs",
  "description": "Document API endpoints",
  "status": "OPEN",
  "employee": { "id": 1 }
}

📜 License

This project is open-source and free to use.
