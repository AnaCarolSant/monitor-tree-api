
# 🌳 monitor-tree-api

API para monitoramento de sensores, alertas e usuários, com autenticação via JWT 🔐.

---

## 🛢 Banco de Dados

Esta API utiliza **MySQL** 🐬.

Configure o arquivo `application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/monitor_tree
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🔐 Autenticação JWT

Para acessar endpoints protegidos, é necessário autenticar-se.

1. Faça um **POST** para `/login` com email e senha.
2. O token retornado deve ser enviado no header **Authorization**:

```http
Authorization: Bearer <token>
```

---

## 🚀 Endpoints

### 1. 🔑 Login

**POST** `http://localhost:8080/login`  
**Body:**
```json
{
  "email": "ana@fiap.com.br",
  "password": "1234"
}
```

**Resposta:**
```json
{
  "token": "<token JWT>",
  "type": "Bearer",
  "email": "ana@fiap.com.br"
}
```

---

### 2. 👤 Criar usuário

**POST** `http://localhost:8080/usuarios`  
**Headers:**
```
Authorization: Bearer <token>
Content-Type: application/json
```

**Body:**
```json
{
  "nome": "Leticia",
  "cpf": "390.533.447-05",
  "telefone": "11999999999",
  "email": "leticia@fiap.com.br",
  "role": "USER",
  "senha": "12356"
}
```

---

### 3. 📋 Listar usuários

**GET** `http://localhost:8080/usuarios`  
**Headers:**
```
Authorization: Bearer <token>
```

---

### 4. 🚨 Criar alerta

**POST** `http://localhost:8080/alertas`  
**Headers:**
```
Authorization: Bearer <token>
Content-Type: application/json
```

**Body:**
```json
{
  "descricao": "Alerta de temperatura alta",
  "sensorId": 1
}
```

---

### 5. 📄 Listar alertas

**GET** `http://localhost:8080/alertas`  
**Headers:**
```
Authorization: Bearer <token>
```

---

### 6. 📝 Criar leitura

**POST** `http://localhost:8080/leituras`  
**Headers:**
```
Authorization: Bearer <token>
Content-Type: application/json
```

**Body:**
```json
{
  "valor": 25.5,
  "sensorId": 1,
  "timestamp": "2025-06-04T12:00:00"
}
```

---

### 7. 📊 Listar leituras

**GET** `http://localhost:8080/leituras`  
**Headers:**
```
Authorization: Bearer <token>
```

---

### 8. 🛠 Cadastrar sensor

**POST** `http://localhost:8080/sensores`  
**Headers:**
```
Authorization: Bearer <token>
Content-Type: application/json
```

**Body:**
```json
{
  "nome": "Sensor de Temperatura",
  "tipo": "TEMPERATURA",
  "localizacao": "Sala 1",
  "dataCriacao": "2025-06-04T21:00:00"
}
```

---

### 9. 🔍 Listar sensores

**GET** `http://localhost:8080/sensores`  
**Headers:**
```
Authorization: Bearer <token>
```

---

### 10. 🗑 Deletar sensor

**DELETE** `http://localhost:8080/sensores/{id}`  
**Headers:**
```
Authorization: Bearer <token>
```

---

## 🎉 Pronto para usar!

