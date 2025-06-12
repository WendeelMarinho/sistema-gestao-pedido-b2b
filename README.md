<h1 align="center">
  Sistema de Gestão de Pedidos B2B
</h1>

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-17-blue.svg?style=for-the-badge&logo=openjdk&logoColor=white">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen.svg?style=for-the-badge&logo=spring-boot&logoColor=white">
  <img alt="Docker" src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
  <img alt="PostgreSQL" src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white">
  <img alt="Maven" src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white">
</p>

<p align="center">
  Um microserviço RESTful robusto para gerenciamento de pedidos, construído com Java e Spring Boot, e totalmente containerizado com Docker.
</p>

---

## 📜 Sobre o Projeto

Este projeto é uma solução completa para um desafio técnico de backend, focado na criação de um microserviço para um sistema de gestão de pedidos B2B. A aplicação foi desenvolvida com ênfase em escalabilidade, boas práticas de código e preparação para cenários de alta concorrência.

O sistema permite o gerenciamento completo do ciclo de vida de um pedido, desde sua criação até a entrega, incluindo uma lógica de negócio para controle de crédito dos parceiros comerciais.

---

## ✨ Principais Funcionalidades

-   **API REST Completa:** Endpoints para todas as operações de CRUD (Create, Read, Update, Delete) de pedidos.
-   **Gestão de Pedidos:** Cadastro, consulta (ID, status, período), atualização de status e cancelamento.
-   **Sistema de Crédito para Parceiros:** Validação e débito de crédito na aprovação de pedidos, e restauração no cancelamento.
-   **Notificações (Simuladas):** Mecanismo para simular o envio de notificações a cada mudança de status do pedido.
-   **Tratamento de Concorrência:** Implementado Lock Otimista (`@Version`) para garantir a consistência de dados em operações simultâneas de crédito.

---

## 🚀 Tecnologias e Ferramentas

| Categoria             | Tecnologias                                                                 |
| --------------------- | --------------------------------------------------------------------------- |
| **Linguagem & Framework** | Java 17, Spring Boot 3.3.0                                                  |
| **API & Web**             | Spring Web (REST), Springdoc OpenAPI (Swagger)                              |
| **Persistência de Dados** | Spring Data JPA, Hibernate, PostgreSQL                                      |
| **Testes**                | JUnit 5, Mockito                                                            |
| **Build & Empacotamento** | Maven                                                                       |
| **Containerização**     | Docker, Docker Compose                                                      |

---

## ⚡ Guia Rápido de Execução

O projeto é 100% containerizado. Com **Docker** e **Docker Compose** instalados, basta seguir os passos abaixo.

### 1. Clone o Repositório
```bash
git clone https://github.com/WendeelMarinho/sistema-gestao-pedido-b2b.git
cd sistema-gestao-pedido-b2b

2. Construa o Projeto com Maven
Este passo gera o arquivo .jar executável da aplicação.
# No Linux/macOS/WSL (pode ser necessário dar permissão: chmod +x ./mvnw)
./mvnw clean package

3. Inicie o Ambiente com Docker
Este comando irá construir as imagens e iniciar os contêineres da aplicação e do banco de dados.
docker compose up --build -d

4. Parando o Ambiente
Para parar e remover os contêineres, use:
docker compose down

📡 API e Documentação
Documentação Interativa (Swagger)
A documentação completa e interativa da API, onde você pode testar todos os endpoints, está disponível em:
http://localhost:8080/swagger-ui.html
Exemplo: Criar um Pedido
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
Use code with caution.
Bash
✅ Testes Automatizados
Para executar a suíte de testes unitários do projeto, rode o seguinte comando:
./mvnw test
Use code with caution.
Bash
💡 Possíveis Melhorias Futuras
Implementar um sistema de mensageria real (RabbitMQ/Kafka) para o NotificationService.
Adicionar testes de integração (@SpringBootTest) com Testcontainers.
Implementar segurança na API com Spring Security e JWT.
Configurar um pipeline de CI/CD com GitHub Actions.

