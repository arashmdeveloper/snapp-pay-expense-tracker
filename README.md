# Snapp Pay Expense Tracker

A microservices-based fintech application for tracking user expenses and managing user authentication. This project is designed using Spring Boot and Docker, and follows secure and modular design patterns.

## 🚀 Features

- JWT-based user authentication
- Secure user registration and login
- Add, view, and manage expense entries
- Role-based access control
- Swagger UI for API documentation
- Containerized with Docker Compose

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Maven
- Docker & Docker Compose
- Swagger (OpenAPI)

## 🧩 Architecture Overview

The application is divided into two microservices:

1. **User Service**
   - Handles user registration, login, and JWT issuance.
2. **Expense Service**
   - Handles CRUD operations for expenses and enforces user-based access control.

Both services are containerized and can be run using Docker Compose.

## 🧰 Getting Started

### Prerequisites

- Java 17
- Maven
- Docker & Docker Compose

### Clone the Repository

```bash
git clone https://github.com/arashmdeveloper/snapp-pay-expense-tracker.git
cd snapp-pay-expense-tracker
```

## 🐳 Running with Docker

```bash
docker-compose up --build
```

> This will start both services and expose them on their configured ports.

## 📫 API Documentation

Once the services are up, access Swagger UI:

- **User Service**: `http://localhost:8081/swagger-ui/index.html`
- **Expense Service**: `http://localhost:8082/swagger-ui/index.html`

## 🧪 Running Tests

Each service contains unit and integration tests. To run tests:

```bash
# Inside each service directory
mvn test
```

## 📁 Folder Structure

```
snapp-pay-expense-tracker/
├── docker-compose.yml
├── user-service/
└── expense-service/
```

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you'd like to change.

## 📄 License

This project is licensed under the MIT License. See `LICENSE` file for details.
