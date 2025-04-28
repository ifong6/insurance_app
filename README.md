# Health Insurance Backend Server (Tech Stack Aligned with U.S. Industry)
Hands-on Practice: Spring Boot + Spring Security + JPA + PostgreSQL + Redis + Docker + GitHub Actions

---

## 💡 Tech Stack Overview

<details>
<summary><strong>Click to expand tech stack card</strong></summary>

- **Backend Framework**: Spring Boot, Spring Security
- **Databases**: PostgreSQL, Redis, AWS RDS, DynamoDB (optional)
- **Cloud & Storage**: AWS S3, AWS Parameter Store, AWS SQS/SNS
- **Build & CI/CD**: Docker, GitHub Actions, Testcontainers
- **Testing**: JUnit, Mockito
- **API Tools**: Swagger, MapStruct
- **Architecture**: Layered (DTO/Entity/Service), RBAC, Config Profiles

</details>

---

## 📚 Table of Contents (English)
- [1. Spring Boot Project Initialization](#1-spring-boot-project-initialization)
- [2. Multi-Type Data Source Integration (AWS)](#2-multi-type-data-source-integration-with-aws-services)
- [3. User Authentication and Authorization](#3-user-authentication-and-authorization)
- [4. Centralized Configuration](#4-centralized-configuration)
- [5. Caching and Messaging](#5-caching-and-messaging)
- [6. Project Architecture and Code Quality](#6-project-architecture-and-code-quality)
- [7. DevOps Practices](#7-devops-practices)
- [8. Business Scenario Implementation](#8-business-scenario-implementation)
- [9. Middleware Selection and Usage](#9-middleware-selection-and-usage)

---

<details>
<summary><strong>1. Spring Boot Project Initialization</strong></summary>

- [x] Initialize project using Spring Initializr  
- [x] Set up basic dependencies like `spring-boot-starter-web`, `spring-boot-starter-data-jpa`  

</details>

<details>
<summary><strong>2. Multi-Type Data Source Integration (with AWS Services)</strong></summary>

- [ ] Configure multiple types of data sources to support complex insurance business needs  
  - **Relational DB**: Integrate AWS RDS (PostgreSQL / MySQL)  
  - **Cache DB**: Use AWS ElastiCache (Redis)  
  - **NoSQL DB**: Optionally integrate AWS DynamoDB  
  - **Object Storage**: Integrate AWS S3  
  - **Message Queues**: Use AWS SQS / SNS or Apache Kafka  
- [ ] Manage configs via `application.yml` + Spring Profiles  
- [ ] Build modular repository layer  
- [ ] Integration tests using JUnit, Mockito, Testcontainers  

</details>

<details>
<summary><strong>3. User Authentication and Authorization</strong></summary>

- [ ] Build registration and login APIs  
- [ ] Use `auth0/java-jwt` for JWT  
- [ ] Implement RBAC  

</details>

<details>
<summary><strong>4. Centralized Configuration</strong></summary>

- [ ] Use Spring Cloud Config (or AWS Parameter Store)  
- [ ] Enable dynamic config refresh  

</details>

<details>
<summary><strong>5. Caching and Messaging</strong></summary>

- [ ] Integrate Redis for caching and resilience  
- [ ] Use Kafka or RabbitMQ for async processing  

</details>

<details>
<summary><strong>6. Project Architecture and Code Quality</strong></summary>

- [ ] DTO/Entity separation  
- [ ] Use MapStruct for mapping  
- [ ] Global error handling  
- [ ] Swagger for API docs  

</details>

<details>
<summary><strong>7. DevOps Practices</strong></summary>

- [ ] Multi-stage Dockerfile  
- [ ] GitHub Actions CI/CD  
- [ ] Deployment checklist  

</details>

<details>
<summary><strong>8. Business Scenario Implementation</strong></summary>

- [ ] Simulate realistic flows like issuance, claims, etc.  
- [ ] Include API, DB, business logic  

</details>

<details>
<summary><strong>9. Middleware Selection and Usage</strong></summary>

- [ ] Choose and justify MySQL/Redis/Kafka  
- [ ] Compare pros/cons  
- [ ] Document challenges and configs  

</details>

---

📘 以下为 **中文版本**，适合中文读者或技术笔记归档使用：

## 📚 中文目录（Chinese Table of Contents）
- [1. Spring Boot 项目初始化](#1-spring-boot-项目初始化)
- [2. 多类型数据源配置（结合 AWS 服务）](#2-多类型数据源配置结合-aws-服务)
- [3. 用户认证与授权](#3-用户认证与授权)
- [4. 配置管理](#4-配置管理)
- [5. 缓存与消息中间件](#5-缓存与消息中间件)
- [6. 项目结构与质量优化](#6-项目结构与质量优化)
- [7. DevOps 实践](#7-devops-实践)
- [8. 保险业务场景练习](#8-保险业务场景练习)
- [9. 中间件选型实践](#9-中间件选型实践)

---

<details>
<summary><strong>1. Spring Boot 项目初始化</strong></summary>

- [x] 使用 Spring Initializr 初始化项目结构  
- [x] 配置 `spring-boot-starter-web`、`spring-boot-starter-data-jpa` 等基础依赖  

</details>

<details>
<summary><strong>2. 多类型数据源配置（结合 AWS 服务）</strong></summary>

- [ ] 配置多种数据源类型以支持复杂保险业务系统，包括关系型数据库、NoSQL、缓存、对象存储与消息队列  
  - **关系型数据库**：使用 Spring Data JPA 配置 AWS RDS（PostgreSQL / MySQL）  
  - **缓存数据库**：集成 AWS ElastiCache（Redis）进行热点数据缓存和缓存穿透防护  
  - **NoSQL 数据库**：扩展支持 AWS DynamoDB，适用于非结构化或高吞吐场景  
  - **对象存储**：集成 AWS S3 进行保单附件、发票等静态资源存储与访问控制  
  - **消息队列**：集成 AWS SQS / SNS 或 Apache Kafka，实现异步化投保处理与通知系统  
- [ ] 使用 `application.yml` 管理多数据源配置，配合 Spring Profile 支持多环境部署  
- [ ] 建立模块化的 Repository 层结构，支持多源数据并发调用与统一封装  
- [ ] 实现核心接口的集成测试，使用 JUnit + Mockito + Testcontainers 进行端到端验证  

</details>

<details>
<summary><strong>3. 用户认证与授权</strong></summary>

- [ ] 使用 Spring Security 实现注册 / 登录 API  
- [ ] 集成 `auth0/java-jwt` 实现 JWT 签发与解析  
- [ ] 实现基于角色的访问控制（RBAC）  

</details>

<details>
<summary><strong>4. 配置管理</strong></summary>

- [ ] 使用 Spring Cloud Config 统一管理应用配置（或支持迁移到 AWS Parameter Store）  
- [ ] 支持配置热更新（使用 Spring Cloud Bus）  

</details>

<details>
<summary><strong>5. 缓存与消息中间件</strong></summary>

- [ ] 集成 Redis 实现缓存与防护机制（如穿透/雪崩）  
- [ ] 使用 Kafka 或 RabbitMQ 实现异步任务处理  

</details>

<details>
<summary><strong>6. 项目结构与质量优化</strong></summary>

- [ ] 采用 DTO/Entity 分层设计，使用 MapStruct 简化对象转换  
- [ ] 使用 `@ControllerAdvice` + 自定义异常类统一错误处理  
- [ ] 使用 Swagger 自动生成 API 文档  

</details>

<details>
<summary><strong>7. DevOps 实践</strong></summary>

- [ ] 编写多阶段 Dockerfile 构建镜像  
- [ ] 使用 GitHub Actions 实现自动化构建 / 测试 / 部署流程  
- [ ] 输出部署说明文档和健康检查策略  

</details>

<details>
<summary><strong>8. 保险业务场景练习</strong></summary>

- [ ] 尝试构建和实现我们可以想象到的保险业务流程，如投保、出单、理赔、状态流转等，训练对业务逻辑的建模能力  
- [ ] 每个场景需配合对应 API 设计、数据库模型、业务规则等进行完整实现  

</details>

<details>
<summary><strong>9. 中间件选型实践</strong></summary>

- [ ] 针对 MySQL / Redis / 消息队列（如 Kafka、RabbitMQ）的使用场景进行分析与落地  
- [ ] 比较各类中间件的优缺点，并结合实际需求做出合理选型  
- [ ] 记录使用过程中的注意事项与配置优化经验  

</details>

## Notes:
### Component Relationship Flow

