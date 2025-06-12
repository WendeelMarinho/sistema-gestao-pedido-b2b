# Sistema de Gestão de Pedidos B2B

Este projeto é um microserviço RESTful para gerenciamento de pedidos, desenvolvido em Java 17 com Spring Boot.

## Requisitos

-   Java 17+
-   Docker e Docker Compose

## Como Executar o Projeto

Este projeto é totalmente containerizado e pode ser executado com um único comando.

1.  **Construir a Aplicação:**
    Primeiro, é necessário gerar o arquivo `.jar` da aplicação com o Maven. Na raiz do projeto, execute:
    ```bash
    ./mvnw clean package
    ```
    *Se `./mvnw` não tiver permissão de execução, rode `chmod +x ./mvnw` primeiro.*

2.  **Iniciar os Contêineres:**
    Com o Docker em execução, execute o seguinte comando na raiz do projeto:
    ```bash
    docker-compose up --build
    ```
    Este comando irá:
    -   Construir a imagem Docker da aplicação Java.
    -   Iniciar um contêiner para o banco de dados PostgreSQL.
    -   Iniciar um contêiner para a aplicação, que se conectará ao banco de dados.

A aplicação estará disponível em `http://localhost:8080`.

## Documentação da API

A documentação completa da API, gerada com Swagger/OpenAPI, pode ser acessada no seguinte endereço após a inicialização da aplicação:

-   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Endpoints Principais

-   `POST /pedidos`: Cria um novo pedido.
-   `GET /pedidos/{id}`: Busca um pedido por ID.
-   `GET /pedidos`: Lista pedidos por status ou por período.
-   `PATCH /pedidos/{id}/status`: Atualiza o status de um pedido.
-   `POST /pedidos/{id}/cancelar`: Cancela um pedido.