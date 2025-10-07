# 🛍️ E-Commerce API — Ada Tech

API RESTful desenvolvida em **Java Spring Boot** para gerenciamento de um sistema de e-commerce.  
Permite o controle de clientes, pedidos, itens e cupons de desconto, com integração à documentação interativa via **Swagger**
---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Jakarta Validation**
- **Lombok**
- **Swagger / OpenAPI 3**
- **H2 Database** (para ambiente de desenvolvimento)

---

## 📁 Estrutura do Projeto

```
src/
 ├── main/
 │   ├── java/com/ada/ecommerce/
 │   │   ├── config/         # Configurações da aplicação (ex: Swagger)
 │   │   ├── controller/     # Controladores REST (Clientes, Pedidos, Cupons)
 │   │   ├── dto/            # Objetos de transferência de dados
 │   │   ├── model/          # Entidades do domínio
 │   │   ├── service/        # Regras de negócio e validações
 │   │   ├── repository/     # Interfaces de persistência
 │   │   └── mapper/         # Conversões entre entidades e DTOs
 │   └── resources/
 │       ├── application.properties
 │       └── data.sql (opcional)
 └── test/
     └── java/com/ada/ecommerce/
```

---

## ⚙️ Funcionalidades Principais

- **Cadastro e atualização de clientes**
- **Criação e gerenciamento de pedidos**
- **Adição de itens e cálculo automático de valores**
- **Aplicação de cupons de desconto**
- **Controle de status de pedidos e pagamentos**
- **Documentação automática via Swagger UI**

---

## 🚀 Executando o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/ecommerce-api.git
   cd ecommerce-api
   ```

2. **Compile e execute:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Acesse a API:**
   - API: [http://localhost:8080/api](http://localhost:8080/api)
   - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📘 Documentação da API

A documentação interativa é gerada automaticamente pelo **Swagger** e pode ser acessada em:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🧠 Observações

- O projeto utiliza **camadas bem definidas**, com separação clara entre controladores, serviços e entidades.  
- A arquitetura foi pensada para **facilitar manutenção e escalabilidade**, mantendo o código limpo e modular.  
- Os diagramas acima representam as **relações principais e a arquitetura geral** da aplicação.
