# API Test Cases: CustomerController

## 📌 Overview
Automated test cases for `/customer/signUp` and `/customer/logIn` endpoints.  
**Test Framework**: JUnit 5 + RestAssured  

---

## 🔄 SignUp Endpoint (`POST /customer/signUp`)

<details>
<summary><strong>🔍 Expand 8 Test Cases</strong></summary>

| ID           | Scenario                | Request Body (JSON)                                                                 | Expected Status | Validation Criteria                          |
|--------------|-------------------------|------------------------------------------------------------------------------------|-----------------|----------------------------------------------|
| `TC_SIGNUP_01` | Valid Registration      | ```json { "email": "valid@example.com", "password": "ValidPass123!", "name": "John Doe" } ``` | 201 CREATED     | Returns `SessionCustomerVO` with non-null ID |
| `TC_SIGNUP_02` | Missing Email           | ```json { "password": "ValidPass123!", "name": "John Doe" } ```                    | 400 BAD_REQUEST | Error: "Email is required"                  |
| `TC_SIGNUP_03` | Invalid Email Format    | ```json { "email": "invalid-email", "password": "ValidPass123!", "name": "John Doe" } ``` | 400 BAD_REQUEST | Error: "Invalid email format"               |
| `TC_SIGNUP_04` | Missing Password        | ```json { "email": "valid@example.com", "name": "John Doe" } ```                   | 400 BAD_REQUEST | Error: "Password is required"               |
| `TC_SIGNUP_05` | Weak Password (<8 chars)| ```json { "email": "valid@example.com", "password": "weak", "name": "John Doe" } ``` | 400 BAD_REQUEST | Error: "Password must be ≥8 characters"     |
| `TC_SIGNUP_06` | Missing Name            | ```json { "email": "valid@example.com", "password": "ValidPass123!" } ```          | 400 BAD_REQUEST | Error: "Name is required"                   |
| `TC_SIGNUP_07` | Duplicate Email         | ```json { "email": "existing@example.com", "password": "ValidPass123!", "name": "John Doe" } ``` | 409 CONFLICT    | Error: "Email already registered"           |
| `TC_SIGNUP_08` | Empty Request Body      | ```json {} ```                                                                     | 400 BAD_REQUEST | Error: "Invalid request body"               |

</details>

---

## 🔑 LogIn Endpoint (`POST /customer/logIn`)

<details>
<summary><strong>🔍 Expand 7 Test Cases</strong></summary>

| ID          | Scenario                | Request Body (JSON)                                               | Expected Status  | Validation Criteria                          |
|-------------|-------------------------|-------------------------------------------------------------------|------------------|----------------------------------------------|
| `TC_LOGIN_01` | Valid Login            | ```json { "email": "valid@example.com", "password": "ValidPass123!" } ``` | 201 CREATED      | Returns `CustomerPO` with matching email     |
| `TC_LOGIN_02` | Missing Email          | ```json { "password": "ValidPass123!" } ```                       | 400 BAD_REQUEST  | Error: "Email is required"                  |
| `TC_LOGIN_03` | Missing Password       | ```json { "email": "valid@example.com" } ```                      | 400 BAD_REQUEST  | Error: "Password is required"               |
| `TC_LOGIN_04` | Invalid Email Format   | ```json { "email": "invalid-email", "password": "ValidPass123!" } ``` | 400 BAD_REQUEST  | Error: "Invalid email format"               |
| `TC_LOGIN_05` | Incorrect Password     | ```json { "email": "valid@example.com", "password": "WrongPass123!" } ``` | 401 UNAUTHORIZED | Error: "Invalid credentials"                |
| `TC_LOGIN_06` | Non-Existent User      | ```json { "email": "nonexistent@example.com", "password": "ValidPass123!" } ``` | 404 NOT_FOUND    | Error: "User not found"                     |
| `TC_LOGIN_07` | Empty Request Body     | ```json {} ```                                                    | 400 BAD_REQUEST  | Error: "Invalid request body"               |

</details>

---
