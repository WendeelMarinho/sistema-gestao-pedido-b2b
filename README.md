# Sistema de Gestão de Pedidos B2B

![Java](https://img.shields.io/badge/Java-17-blue.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen.svg?style=for-the-badge&logo=spring-boot&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## 📜 Sobre o Projeto

Este projeto é um microserviço RESTful para gerenciamento de pedidos B2B, desenvolvido como solução para um desafio técnico. A aplicação é responsável por receber, processar e gerenciar pedidos de parceiros comerciais, com foco em escalabilidade, concorrência e boas práticas de desenvolvimento.

A solução foi construída utilizando **Java 17**, **Spring Boot**, e foi totalmente containerizada com **Docker** para garantir um ambiente de execução consistente e de fácil configuração.

## ✨ Funcionalidades

-   **API REST Completa:** Endpoints para todas as operações de CRUD (Create, Read, Update, Delete) de pedidos.
-   **Gestão de Pedidos:**
    -   Cadastro de novos pedidos com status inicial `PENDENTE`.
    -   Consulta de pedidos por ID, status ou período de datas.
    -   Atualização de status (ex: `APROVADO`, `ENVIADO`, etc.).
    -   Cancelamento de pedidos.
-   **Sistema de Crédito para Parceiros:**
    -   Cada parceiro possui um limite de crédito gerenciado pelo sistema.
    -   O crédito é validado e debitado no momento da **aprovação** de um pedido.
    -   O crédito é restaurado caso um pedido aprovado seja **cancelado**.
-   **Notificações (Simuladas):** Um mecanismo para simular o envio de notificações a cada mudança de status do pedido.

## 🚀 Tecnologias Utilizadas

-   **Backend:**
    -   Java 17
    -   Spring Boot 3.3.0
    -   Spring Web (API REST)
    -   Spring Data JPA (Persistência)
    -   Hibernate
-   **Banco de Dados:**
    -   PostgreSQL
-   **Ferramentas e Build:**
    -   Maven
    -   Docker & Docker Compose
-   **Documentação e Testes:**
    -   Springdoc OpenAPI (Swagger)
    -   JUnit 5 & Mockito

## 🏛️ Arquitetura e Decisões de Design

A aplicação segue uma arquitetura em camadas clássica para garantir a separação de responsabilidades:

-   **`Controller`**: Responsável por expor os endpoints da API REST, receber as requisições, validar os dados de entrada (DTOs) e delegar a lógica para a camada de serviço.
-   **`Service`**: Contém a lógica de negócio principal. Orquestra as operações, validações de crédito, mudanças de estado e interações com a camada de persistência.
-   **`Repository`**: Interface de acesso a dados, utilizando Spring Data JPA para abstrair as operações com o banco de dados.
-   **`Model`**: Entidades JPA que representam as tabelas do banco de dados (`Pedido`, `Parceiro`, `ItemPedido`).

### Tratamento de Concorrência

Para atender ao requisito de alta concorrência, foi implementado o **Lock Otimista** na entidade `Parceiro` através da anotação `@Version`. Isso previne "condições de corrida" ao debitar o crédito, garantindo que duas transações simultâneas para o mesmo parceiro não corrompam o saldo. Se uma colisão ocorrer, o Spring lançará uma `ObjectOptimisticLockingFailureException`, garantindo a consistência dos dados.

## ⚙️ Pré-requisitos

-   Java 17+ (apenas para desenvolvimento local, não é necessário para rodar com Docker)
-   Docker e Docker Compose

## ⚡ Executando a Aplicação

Este projeto é totalmente containerizado e pode ser executado com um único comando.

#### 1. Construir a Aplicação

Primeiro, é necessário gerar o arquivo `.jar` da aplicação com o Maven. Na raiz do projeto, execute:

```bash
# No Linux/macOS/WSL (pode ser necessário dar permissão de execução primeiro: chmod +x ./mvnw)
./mvnw clean package

# No Windows (CMD)
mvnw.cmd clean package
