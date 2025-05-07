# API Test Cases: CustomerController

## 📌 Overview
Automated test cases for `/customer/signUp` 
and `/customer/logIn` endpoints.  

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

# API Test Cases: Post Controller

## 📌 Overview
Automated test cases for Post CRUD operations and publishing workflow.  
**Endpoints Covered**:  
- `POST /posts` - Create post  
- `PUT /posts/{id}` - Update post  
- `POST /posts/{id}` - Publish post  
- `GET /posts` - Get all posts  
- `GET /posts/author/{authorName}` - Get posts by author  

---

## ✨ Create Post (`POST /posts`)

<details>
<summary><strong>🔍 Expand 6 Test Cases</strong></summary>

| ID            | Scenario                | Request Body (JSON)                                                                 | Expected Status | Validation Criteria                          |
|---------------|-------------------------|------------------------------------------------------------------------------------|-----------------|----------------------------------------------|
| `TC_POST_CREATE_01` | Valid Creation        | ```json { "title": "Valid Title", "content": "Valid content", "author": "user1" } ``` | 200 OK          | Returns `PostVO` with status=DRAFT           |
| `TC_POST_CREATE_02` | Missing Title         | ```json { "content": "Valid content", "author": "user1" } ```                      | 400 BAD_REQUEST | Error: "Title is required"                   |
| `TC_POST_CREATE_03` | Empty Title           | ```json { "title": "", "content": "Valid content", "author": "user1" } ```         | 400 BAD_REQUEST | Error: "Title is required"                   |
| `TC_POST_CREATE_04` | Missing Content       | ```json { "title": "Valid Title", "author": "user1" } ```                          | 400 BAD_REQUEST | Error: "Content is required"                 |
| `TC_POST_CREATE_05` | Empty Content         | ```json { "title": "Valid Title", "content": "", "author": "user1" } ```           | 400 BAD_REQUEST | Error: "Content is required"                 |
| `TC_POST_CREATE_06` | Missing Author        | ```json { "title": "Valid Title", "content": "Valid content" } ```                 | 400 BAD_REQUEST | Error: "Author is required"                  |

</details>

---

## ✏️ Update Post (`PUT /posts/{id}`)

<details>
<summary><strong>🔍 Expand 5 Test Cases</strong></summary>

| ID            | Scenario                | Request Body (JSON)                                                                 | Expected Status | Validation Criteria                          |
|---------------|-------------------------|------------------------------------------------------------------------------------|-----------------|----------------------------------------------|
| `TC_POST_UPDATE_01` | Valid Update         | ```json { "title": "Updated Title", "content": "Updated content" } ```             | 200 OK          | Returns updated `PostVO`                     |
| `TC_POST_UPDATE_02` | Unauthorized Edit    | ```json { "title": "Updated Title" } ``` (by non-owner)                            | 403 FORBIDDEN   | Error: "Unauthorized to edit post"           |
| `TC_POST_UPDATE_03` | Partial Update       | ```json { "title": "Only Title Updated" } ```                                      | 200 OK          | Only title should change                      |
| `TC_POST_UPDATE_04` | Non-Existent Post    | ```json { "title": "Updated Title" } ``` (invalid ID)                              | 404 NOT_FOUND   | Error: "Post not found"                      |
| `TC_POST_UPDATE_05` | Empty Update         | ```json {} ```                                                                     | 200 OK          | Original post remains unchanged              |

</details>

---

## 📢 Publish Post (`POST /posts/{id}`)

<details>
<summary><strong>🔍 Expand 4 Test Cases</strong></summary>

| ID            | Scenario                | Path Variable | Expected Status | Validation Criteria                          |
|---------------|-------------------------|---------------|-----------------|----------------------------------------------|
| `TC_POST_PUBLISH_01` | Valid Publish       | `id=1`        | 200 OK          | Status changes to PUBLISHED                  |
| `TC_POST_PUBLISH_02` | Already Published   | `id=2`        | 400 BAD_REQUEST | Error: "Post already published"              |
| `TC_POST_PUBLISH_03` | Non-Existent Post   | `id=999`      | 404 NOT_FOUND   | Error: "Post not found"                      |
| `TC_POST_PUBLISH_04` | Unauthorized Publish| `id=3` (non-owner) | 403 FORBIDDEN | Error: "Unauthorized to publish"             |

</details>

---

## 📜 Get Posts (`GET /posts`)

<details>
<summary><strong>🔍 Expand 3 Test Cases</strong></summary>

| ID            | Scenario                | Query Params | Expected Status | Validation Criteria                          |
|---------------|-------------------------|--------------|-----------------|----------------------------------------------|
| `TC_POST_GET_01` | Get All Posts        | None         | 200 OK          | Returns list of `PostVO`                     |
| `TC_POST_GET_02` | Empty Database       | None         | 200 OK          | Returns empty array                          |
| `TC_POST_GET_03` | Filter by Author     | `authorName=user1` | 200 OK     | Returns only user1's posts                   |

</details>

---

## 🗑️ Delete Post (`DELETE /posts/{id}`)

<details>
<summary><strong>🔍 Expand 3 Test Cases</strong></summary>

| ID            | Scenario                | Path Variable | Expected Status | Validation Criteria                          |
|---------------|-------------------------|---------------|-----------------|----------------------------------------------|
| `TC_POST_DELETE_01` | Valid Delete       | `id=1`        | 204 NO_CONTENT  | Post no longer exists                        |
| `TC_POST_DELETE_02` | Non-Existent Post  | `id=999`      | 404 NOT_FOUND   | Error: "Post not found"                      |
| `TC_POST_DELETE_03` | Unauthorized Delete| `id=2` (non-owner) | 403 FORBIDDEN | Error: "Unauthorized to delete"             |

</details>

---
