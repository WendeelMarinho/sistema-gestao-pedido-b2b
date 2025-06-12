
# Sistema de Gestão de Pedidos B2B

---
**Tecnologias:** Java 17 | Spring Boot 3.3.0 | Docker | PostgreSQL | Maven
---

## 📜 Sobre o Projeto

Este projeto é um microserviço RESTful para gerenciamento de pedidos B2B, desenvolvido como solução para um desafio técnico. A aplicação é responsável por receber, processar e gerenciar pedidos de parceiros comerciais, com foco em escalabilidade, boas práticas de código e preparação para cenários de alta concorrência.

O sistema permite o gerenciamento completo do ciclo de vida de um pedido, desde sua criação até a entrega, incluindo uma lógica de negócio para controle de crédito dos parceiros comerciais.

---

## ✨ Principais Funcionalidades

- **API REST Completa:** Endpoints para todas as operações de CRUD (Create, Read, Update, Delete) de pedidos.
- **Gestão de Pedidos:** Cadastro, consulta (ID, status, período), atualização de status e cancelamento.
- **Sistema de Crédito para Parceiros:** Validação e débito de crédito na aprovação de pedidos, e restauração no cancelamento.
- **Notificações (Simuladas):** Mecanismo para simular o envio de notificações a cada mudança de status do pedido.
- **Tratamento de Concorrência:** Implementado Lock Otimista (`@Version`) para garantir a consistência de dados em operações simultâneas de crédito.

---

## 🚀 Tecnologias e Ferramentas

- **Backend:**  
  Java 17, Spring Boot 3.3.0, Spring Web (REST), Spring Data JPA, Hibernate  
- **Banco de Dados:**  
  PostgreSQL  
- **Ferramentas e Build:**  
  Maven, Docker & Docker Compose  
- **Documentação e Testes:**  
  Springdoc OpenAPI (Swagger), JUnit 5 & Mockito

---

## ⚡ Guia Rápido de Execução

O projeto é 100% containerizado. Com **Docker** e **Docker Compose** instalados, basta seguir os passos abaixo.

### 1. Clone o Repositório
```bash
git clone https://github.com/WendeelMarinho/sistema-gestao-pedido-b2b.git
cd sistema-gestao-pedido-b2b
```

### 2. Construa o Projeto com Maven  
Este passo gera o arquivo .jar executável da aplicação.
```bash
# No Linux/macOS/WSL (pode ser necessário dar permissão: chmod +x ./mvnw)
./mvnw clean package
```

### 3. Inicie o Ambiente com Docker  
Este comando irá construir as imagens e iniciar os contêineres da aplicação e do banco de dados.
```bash
docker compose up --build -d
```

A aplicação estará disponível em http://localhost:8080.

### 4. Parando o Ambiente  
Para parar e remover os contêineres, use:
```bash
docker compose down
```

---

## 📡 API e Documentação

### Documentação Interativa (Swagger)  
A documentação completa e interativa da API, onde você pode testar todos os endpoints, está disponível em:  
http://localhost:8080/swagger-ui.html

### Exemplo de Requisição (curl)  
Para criar um novo pedido:
```bash
curl -X 'POST' \
  'http://localhost:8080/pedidos' \
  -H 'Content-Type: application/json' \
  -d '{
  "idParceiro": 1,
  "itens": [
    {
      "produto": "Notebook Pro",
      "quantidade": 1,
      "precoUnitario": 7500.50
    }
  ]
}'
```

---

## ✅ Testes Automatizados

Para executar a suíte de testes unitários do projeto, rode o seguinte comando:
```bash
./mvnw test
```

---

## 💡 Possíveis Melhorias Futuras

- Implementar um sistema de mensageria real (RabbitMQ/Kafka).
- Adicionar testes de integração com Testcontainers.
- Implementar segurança na API com Spring Security e JWT.
- Configurar um pipeline de CI/CD com GitHub Actions.

---

✒️ **Autor**  
Wendeel Marinho
