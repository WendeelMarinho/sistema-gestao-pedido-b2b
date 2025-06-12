<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>README - Sistema de Gestão de Pedidos B2B</title>
<style>
  body {
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
      Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
    background-color: #f9f9f9;
    color: #222;
    margin: 0; padding: 0 1rem;
    line-height: 1.6;
  }
  main {
    max-width: 900px;
    margin: 2rem auto;
    background: white;
    padding: 2rem 3rem;
    box-shadow: 0 0 15px rgb(0 0 0 / 0.1);
    border-radius: 8px;
  }
  h1, h2, h3 {
    color: #2c3e50;
    margin-top: 1.5rem;
  }
  h1 {
    font-size: 2.5rem;
    margin-bottom: 0.3rem;
  }
  hr {
    border: none;
    border-top: 1px solid #ddd;
    margin: 1.5rem 0;
  }
  .badges {
    margin-bottom: 1rem;
  }
  .badges img {
    margin-right: 0.4rem;
    height: 32px;
    vertical-align: middle;
  }
  code, pre {
    background: #f3f4f6;
    border-radius: 4px;
    font-family: Consolas, Monaco, "Andale Mono", "Ubuntu Mono", monospace;
    font-size: 0.95rem;
  }
  pre {
    padding: 1rem;
    overflow-x: auto;
    margin: 1rem 0;
  }
  code {
    padding: 0.2rem 0.4rem;
  }
  a {
    color: #0077cc;
    text-decoration: none;
  }
  a:hover {
    text-decoration: underline;
  }
  ul {
    margin-left: 1.5rem;
  }
  .footer {
    margin-top: 3rem;
    font-size: 0.9rem;
    color: #666;
    border-top: 1px solid #eee;
    padding-top: 1rem;
    text-align: center;
  }
</style>
</head>
<body>
<main>
  <h1>Sistema de Gestão de Pedidos B2B</h1>

  <div class="badges">
    <img src="https://img.shields.io/badge/Java-17-blue.svg?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17" />
    <img src="https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen.svg?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot 3.3.0" />
    <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker" />
    <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
    <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven" />
  </div>

  <hr />

  <h2>📜 Sobre o Projeto</h2>
  <p>
    Este projeto é um microserviço RESTful para gerenciamento de pedidos B2B, desenvolvido para um desafio técnico.
    A aplicação recebe, processa e gerencia pedidos de parceiros comerciais, com foco em escalabilidade, concorrência e boas práticas de desenvolvimento.
  </p>
  <p>
    A solução foi construída com <strong>Java 17</strong>, <strong>Spring Boot 3.3.0</strong>, e containerizada com <strong>Docker</strong> para garantir um ambiente consistente e fácil de configurar.
  </p>

  <hr />

  <h2>✨ Funcionalidades</h2>
  <ul>
    <li><strong>API REST Completa:</strong> CRUD para pedidos.</li>
    <li><strong>Gestão de Pedidos:</strong>
      <ul>
        <li>Cadastro com status inicial <code>PENDENTE</code>.</li>
        <li>Consulta por ID, status ou intervalo de datas.</li>
        <li>Atualização de status (ex: <code>APROVADO</code>, <code>ENVIADO</code>).</li>
        <li>Cancelamento de pedidos.</li>
      </ul>
    </li>
    <li><strong>Sistema de Crédito para Parceiros:</strong>
      <ul>
        <li>Limite de crédito gerenciado por parceiro.</li>
        <li>Validação e débito de crédito na aprovação do pedido.</li>
        <li>Crédito restaurado se pedido aprovado for cancelado.</li>
      </ul>
    </li>
    <li><strong>Notificações (Simuladas):</strong> Simulação de envio de notificações a cada alteração de status.</li>
  </ul>

  <hr />

  <h2>🚀 Tecnologias Utilizadas</h2>
  <ul>
    <li><strong>Backend:</strong> Java 17, Spring Boot 3.3.0, Spring Web (REST), Spring Data JPA, Hibernate</li>
    <li><strong>Banco de Dados:</strong> PostgreSQL</li>
    <li><strong>Build & Infraestrutura:</strong> Maven, Docker & Docker Compose</li>
    <li><strong>Documentação e Testes:</strong> Springdoc OpenAPI (Swagger), JUnit 5, Mockito</li>
  </ul>

  <hr />

  <h2>🏛️ Arquitetura e Decisões de Design</h2>
  <p>
    A aplicação segue arquitetura em camadas para garantir separação de responsabilidades:
  </p>
  <ul>
    <li><strong>Controller:</strong> Exposição dos endpoints REST, validação de entrada e roteamento para serviços.</li>
    <li><strong>Service:</strong> Lógica de negócio, validações, regras de crédito e orquestração.</li>
    <li><strong>Repository:</strong> Acesso a dados com Spring Data JPA.</li>
    <li><strong>Model:</strong> Entidades JPA (<code>Pedido</code>, <code>Parceiro</code>, <code>ItemPedido</code>).</li>
  </ul>

  <h3>Tratamento de Concorrência</h3>
  <p>
    Para alta concorrência, foi implementado <strong>Lock Otimista</strong> na entidade <code>Parceiro</code> com a anotação <code>@Version</code>.
    Isso evita condições de corrida ao debitar crédito simultaneamente, lançando <code>ObjectOptimisticLockingFailureException</code> em casos de conflito, assegurando a consistência dos dados.
  </p>

  <hr />

  <h2>⚙️ Pré-requisitos</h2>
  <ul>
    <li>Java 17+ (necessário somente para desenvolvimento local)</li>
    <li>Docker e Docker Compose instalados</li>
  </ul>

  <hr />

  <h2>⚡ Guia de Execução</h2>

  <h3>1. Clone o Repositório</h3>
  <pre><code>git clone https://github.com/WendeelMarinho/sistema-gestao-pedido-b2b.git
cd sistema-gestao-pedido-b2b
  </code></pre>

  <h3>2. Construa a Aplicação</h3>
  <p>Compile e empacote a aplicação usando Maven:</p>
  <pre><code># Linux/macOS/WSL (pode ser necessário: chmod +x ./mvnw)
./mvnw clean package

# Windows (CMD)
mvnw.cmd clean package
  </code></pre>

  <h3>3. Inicie os Contêineres com Docker Compose</h3>
  <pre><code>docker compose up --build -d
  </code></pre>
  <p>
    Isso irá construir a imagem Docker da aplicação, iniciar o contêiner PostgreSQL e a aplicação.
    Acesse em: <a href="http://localhost:8080" target="_blank" rel="noopener">http://localhost:8080</a>
  </p>

  <h3>4. Parar a Aplicação</h3>
  <pre><code>docker compose down
  </code></pre>

  <hr />

  <h2>📡 Documentação da API</h2>
  <p>
    A documentação interativa Swagger está disponível em: <br />
    <a href="http://localhost:8080/swagger-ui.html" target="_blank" rel="noopener">http://localhost:8080/swagger-ui.html</a>
  </p>

  <hr />

  <h2>🛠 Exemplos de Uso com curl</h2>

  <h3>Criar um Pedido</h3>
  <pre><code>curl -X POST http://localhost:8080/pedidos \
-H "Content-Type: application/json" \
-d '{
  "idParceiro": 1,
  "itens": [
    {
      "produto": "Notebook Dell",
      "quantidade": 2,
      "precoUnitario": 3500.00
    }
  ]
}'
  </code></pre>

  <h3>Consultar Pedido por ID</h3>
  <pre><code>curl http://localhost:8080/pedidos/123
  </code></pre>

  <h3>Atualizar Status do Pedido</h3>
  <pre><code>curl -X PUT http://localhost:8080/pedidos/123/status \
-H "Content-Type: application/json" \
-d '{"status": "APROVADO"}'
  </code></pre>

  <hr />

  <h2>🤝 Contato</h2>
  <p>
    Desenvolvido por Wendeel Marinho.<br />
    Para dúvidas ou sugestões, envie um e-mail para <a href="mailto:wendeel@example.com">wendeel@example.com</a>.
  </p>

  <div class="footer">
    &copy; 2025 Wendeel Marinho. Todos os direitos reservados.
  </div>
</main>
</body>
</html>
