# 🏨 Wild Oasis – Hotel Management Backend

**Wild Oasis** is a backend system built with **Spring Boot** and **JWT-based authentication** designed to manage hotel operations including employees, guests, rooms, bookings, and room service tasks.

---

## ⚙️ Tech Stack

- **Java 17+**
- **Spring Boot 3**
- **Spring Security (JWT Authentication)**
- **Spring Data JPA**
- **PostgreSQL / Oracle DB**
- **Maven**
- **Jakarta Validation**
- **Lombok**

---

## 🔐 Authentication & Authorization

- JWT-based stateless authentication.
- Role-based authorization:
  - `MANAGER`: full CRUD access.
  - `RECEPTIONIST`: read/write access (no deletes).
- Passwords are securely hashed using **BCrypt**.

---

## 📦 API Endpoints

### 🔑 Auth

| Method | Endpoint             | Access     | Description            |
|--------|----------------------|------------|------------------------|
| POST   | `/api/auth/register` | Public     | Register new employee  |
| POST   | `/api/auth/login`    | Public     | Login and receive token|

### 👥 Employees

| Method | Endpoint                    | Access               | Description             |
|--------|-----------------------------|----------------------|-------------------------|
| GET    | `/api/employees`            | Manager/Receptionist | List all employees      |
| POST   | `/api/employees`            | Manager/Receptionist | Create a new employee   |
| PUT    | `/api/employees/update/{id}`| Manager/Receptionist | Update employee         |
| DELETE | `/api/employees/{id}`       | Manager only         | Delete employee         |

### 🏨 Rooms, Guests, Bookings, Tasks

Similar CRUD structure applies with role restrictions.

---


