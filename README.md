# Pahana Edu - Online Billing System

## 📖 Table of Contents
1.  [Project Overview](#-project-overview)
2.  [Features](#-features)
3.  [Technology Stack](#-technology-stack)
4.  [System Design & Architecture](#-system-design--architecture)
5.  [Installation & Setup](#-installation--setup)
6.  [Usage](#-usage)
7.  [Testing](#-testing)
8.  [Version Control & Deployment](#-version-control--deployment)
9. [License](#-license)

---

## 🎯 Project Overview

**Pahana Edu** is a sophisticated web-based billing system developed for a leading bookshop in Colombo City. This system modernizes the manual process of managing customer accounts, inventory, and billing, providing a secure, efficient, and user-friendly platform built with industry-standard technologies and practices.

The application is built as a **distributed Java EE web application** following the MVC architecture. It leverages a modern frontend with **DaisyUI** and a robust backend with **Java Servlets**, and **MySQL** for data persistence, all managed by **Maven**.

**Key Objectives Met:**
*   **LO I:** Demonstrated through comprehensive UML design diagrams.
*   **LO II:** Achieved via implementation of design patterns, a 3-tier architecture, and a rigorous test plan using JUnit.
*   **LO III:** Fulfilled by maintaining a public Git repository with a clear commit history and CI/CD workflow.

---

## ✨ Features

1. **🔐 Secure User Authentication:** Secure login with username and password.
2. **👥 Customer Management:** Full CRUD operations (Create, Read, Update, Delete) for customer accounts.
3. **📦 Inventory Management:** Add, update, delete, and view items from inventory.
4. **🧾 Bill Generation & Printing:** Calculate dynamic bills based on items purchased and generate printable invoices.
5. **📧 Email Invoices:** Send generated invoices directly to the customer's email address.
6. **📊 Dashboard:** Overview of key metrics, recent transactions, and system statistics.
7. **❌ Input Validation:** Comprehensive server-side and client-side validation to ensure data integrity.
8. **💡 Help Section:** Provides users with guidelines on how to use the system.
9. **🚪 Exit:** Secure logout and system exit.

---

## 🛠 Technology Stack

* **Backend:** Java EE, Java Servlets, JDBC
* **Frontend:** JSP, HTML5, CSS3, DaisyUI, JavaScript
* **Database:** MySQL 8.0
* **Build Tool:** Apache Maven
* **Application Server:** Apache Tomcat 9+
* **Email API:** Jakarta Mail
* **Testing:** JUnit 5
* **Version Control:** Git / GitHub
* **IDE:** IntelliJ IDEA Ultimate

---

## 🏗 System Design & Architecture

The system is designed with a clear separation of concerns, following a **Model-View-Controller (MVC)** pattern within a **3-Tier Architecture**.

### Architecture
1.  **Presentation Tier (View):** JSP pages styled with DaisyUI and CSS handle all user interaction and rendering.
2.  **Business Logic Tier (Controller):** Java Servlets process HTTP requests, manage sessions, and coordinate between the user interface and the business logic.
3.  **Data Tier (Model):** JavaBeans (e.g., `Customer`, `Item`, `Invoice`) represent the data model, and Data Access Object (DAO) classes manage all interactions with the **MySQL database**.

### Design Patterns
1.  **Data Access Object (DAO):** Abstracts and encapsulates all database operations.
2.  **Singleton:** Manages database connection pools efficiently.
3.  **Service Layer:** Separates business logic from controller and data access code for improved maintainability.

---

## 📥 Installation & Setup

### Prerequisites
Ensure you have the following installed on your system:
*   Java JDK 11 or higher
*   Apache Tomcat 9.x
*   MySQL Server 8.0
*   Git
*   Maven

### Setup Instructions
1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/your-username/pahana-edu-billing-system.git](https://github.com/ManeeshaVR/CIS6003_Advanced_Programming_Assignment.git)
    cd pahana-edu-billing-system
    ```

2.  **Database Configuration:**
    *   Create a MySQL database named `pahana_edu`.
    *   Create a SQL script to create all necessary tables.
    *   Create a SQL script to populate the database with sample data for testing (ex: user table data for login).

3.  **Application Configuration:**
    *   Update the database connection parameters (URL, username, password).
    *   Configure the email service (SMTP host, port, username, password).

4.  **Run the Application:**
    *   Start your MySQL and Tomcat servers.
    *   Access the application at `http://localhost:8080/pahana-edu/`.

---

## 🖥 Usage

1.  Navigate to the application URL in your web browser.
2.  Log in using your credentials.
3.  Use the intuitive navigation menu to:
    *   **Dashboard:** View a summary of recent activity.
    *   **Customers:** Add, view, edit, or search for customers. Ensure their email is correctly entered to receive invoices.
    *   **Items:** Manage the inventory of books and items.
    *   **New Bill:** Select a customer, add items, generate an invoice, and send it via email.
    *   **Help:** View the user guide.
4.  Remember to log out after your session to maintain security.

---

## 🧪 Testing

A combination of testing strategies ensures application reliability:

*   **Unit Testing:** JUnit 5 tests for DAO layer components, ensuring individual parts work correctly in isolation.
*   **Manual Testing :** A comprehensive test plan with rationale, cases, and data.

---

## 🔄 Version Control & Deployment

This project uses Git for version control with a feature-branch workflow:

*   **Main Branch:** Contains stable, production-ready code.
*   **Feature Branches:** Used for developing new features (e.g., `feature/customer-dev`, `feature/item-dev`).
*   **Pull Requests:** All changes are merged into `main` after review and successful testing.

---

## 📜 License

This project is developed for academic purposes as part of the CIS6003 Advanced Programming module at Cardiff Metropolitan University. All rights reserved.
