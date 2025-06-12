🛒 Sistema de Gestão de Pedidos B2B
Microserviço RESTful para gerenciamento de pedidos B2B com foco em alta concorrência e escalabilidade.
<p align="center">
<img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 17">
<img src="https://img.shields.io/badge/Spring_Boot-3.3.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot 3.3.0">
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker">
<img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
<img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven">
<img src="https://img.shields.io/badge/status-concluído-brightgreen?style=for-the-badge" alt="Status do Projeto">
</p>
Índice
📜 Sobre o Projeto
✨ Funcionalidades
🏗️ Arquitetura e Decisões de Design
🚀 Tecnologias Utilizadas
⚙️ Pré-requisitos
🚀 Guia de Execução
📡 Endpoints da API
🧪 Executando os Testes
💡 Possíveis Melhorias
✒️ Autor
📜 Sobre o Projeto
Este projeto é um microserviço RESTful robusto para gerenciamento de pedidos B2B, desenvolvido como solução para um desafio técnico. A aplicação é responsável por receber, processar e gerenciar pedidos de parceiros comerciais, com foco em:
🔄 Escalabilidade Horizontal: Arquitetura preparada para crescer conforme a demanda, com containers Docker e load balancing.
⚡ Alta Concorrência: Sistema de lock otimista para garantir consistência em operações simultâneas.
🛡️ Consistência de Dados: Transações ACID e controle de versionamento para integridade dos dados.
📊 Observabilidade: Monitoramento integrado com Spring Actuator e health checks.
✨ Funcionalidades
🔧 API REST Completa
✅ CRUD completo para pedidos
✅ Filtros avançados por status e período
✅ Busca otimizada com paginação
✅ Validação de dados robusta
💰 Sistema de Crédito Inteligente
✅ Validação automática de limite de crédito
✅ Débito do valor do pedido na aprovação
✅ Restauração do crédito no cancelamento
✅ Controle de concorrência para evitar inconsistências
📧 Sistema de Notificações
✅ Notificações automáticas sobre mudanças de status
✅ Múltiplos canais (simulado via logs)
✅ Tracking de eventos importantes
✅ Logs estruturados para fácil monitoramento
🏗️ Arquitetura e Decisões de Design
🏛️ Arquitetura em Camadas (Layered Architecture)
O projeto segue uma arquitetura em camadas clássica para garantir a separação de responsabilidades e a manutenibilidade do código.
Controller (API Layer): Responsável por expor os endpoints REST, receber as requisições, validar os dados de entrada e delegar para a camada de serviço.
Service (Business Logic): Contém toda a lógica de negócio, como validação de crédito, mudança de status dos pedidos e orquestração das operações.
Repository (Data Access Layer): Abstrai o acesso ao banco de dados utilizando Spring Data JPA.
Model (Entity Layer): Representa as entidades do domínio, como Pedido, Parceiro e ItemPedido.
🔐 Tratamento de Concorrência
Para lidar com múltiplas atualizações simultâneas no limite de crédito dos parceiros, foi implementado um mecanismo de Lock Otimista utilizando a anotação @Version do JPA na entidade Parceiro.
@Entity
public class Parceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version; // Controle de concorrência

    private BigDecimal limiteCredito;
    // ... outros atributos
}
Use code with caution.
Java
💡 Benefícios: Esta abordagem previne condições de corrida e garante a consistência dos dados com alta performance, sendo mais escalável que o lock pessimista em cenários de alta concorrência.
🚀 Tecnologias Utilizadas
🛠️ Categoria	📦 Tecnologia	📝 Descrição
Backend	Java 17	Versão LTS com melhorias de performance.
Framework	Spring Boot 3.3.0	Framework principal para criação da API.
Persistência	Spring Data JPA + Hibernate	ORM para abstração e acesso a dados.
Banco de Dados	PostgreSQL	Banco de dados relacional robusto e confiável.
Containerização	Docker & Docker Compose	Criação de um ambiente de execução consistente.
Build	Maven	Gerenciamento de dependências e build.
Documentação	Springdoc OpenAPI (Swagger UI)	Geração automática de documentação da API.
Testes	JUnit 5 & Mockito	Testes unitários para garantir a qualidade.
⚙️ Pré-requisitos
🛠️ Ferramenta	📋 Versão	🎯 Necessário para
Docker	20.10+	✅ Execução (Recomendado)
Docker Compose	2.0+	✅ Execução (Recomendado)
Java (JDK)	17+	⚠️ Desenvolvimento local
Maven	3.8+	⚠️ Build local
💡 Dica: A utilização do Docker e Docker Compose é a forma mais simples e rápida de executar o projeto, pois abstrai toda a configuração do ambiente.
🚀 Guia de Execução
🐳 Método Recomendado: Docker Compose
Clone o repositório:
git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git
cd SEU_REPOSITORIO
Use code with caution.
Bash
Construa a aplicação com Maven:
(Isso gera o arquivo .jar que será usado pelo Docker)
# Linux/macOS
./mvnw clean package

# Windows
mvnw.cmd clean package
Use code with caution.
Bash
Inicie os serviços com Docker Compose:
(Este comando irá construir a imagem da aplicação e subir os containers da API e do banco de dados)
docker compose up --build -d
Use code with caution.
Bash
Verifique o status:
Aguarde alguns segundos para a aplicação iniciar. Você pode verificar os logs:
docker compose logs -f
Use code with caution.
Bash
🌐 Acessos Disponíveis
Após a inicialização, os seguintes serviços estarão disponíveis:
🔗 Serviço	📍 URL	📝 Descrição
API	http://localhost:8080	Endpoints REST.
Swagger UI	http://localhost:8080/swagger-ui.html	Documentação interativa.
Health Check	http://localhost:8080/actuator/health	Status da aplicação.
🛑 Parando a Aplicação
Para parar e remover os containers, execute:
docker compose down
Use code with caution.
Bash
📡 Endpoints da API
📚 A documentação completa e interativa da API está disponível no Swagger UI.
Exemplos com cURL
📝 Criar um Pedido
curl -X 'POST' \
  'http://localhost:8080/pedidos' \
  -H 'Content-Type: application/json' \
  -d '{
    "idParceiro": 1,
    "itens": [
      {
        "produto": "Notebook Pro",
        "quantidade": 2,
        "precoUnitario": 3500.00
      },
      {
        "produto": "Mouse Wireless",
        "quantidade": 1,
        "precoUnitario": 150.00
      }
    ]
  }'
Use code with caution.
Bash
✅ Aprovar um Pedido (substitua {id} pelo ID do pedido)
curl -X 'PATCH' \
  'http://localhost:8080/pedidos/{id}/status' \
  -H 'Content-Type: application/json' \
  -d '"APROVADO"'
Use code with caution.
Bash
🔍 Buscar Pedidos por Status
curl -X 'GET' 'http://localhost:8080/pedidos?status=PENDENTE&page=0&size=10'
Use code with caution.
Bash
📋 Status Disponíveis
🏷️ Status	📝 Descrição	💰 Ação no Crédito
PENDENTE	Pedido recém-criado, aguardando aprovação.	-
APROVADO	Pedido aprovado. O valor total é debitado do crédito.	➖ Débito
ENVIADO	Pedido despachado para o parceiro.	-
ENTREGUE	Pedido entregue ao parceiro.	-
CANCELADO	Pedido cancelado. O crédito é restaurado (se já aprovado).	➕ Crédito restaurado
REJEITADO	Pedido rejeitado por falta de crédito ou outro motivo.	-
🧪 Executando os Testes
Para garantir a qualidade e a integridade do código, o projeto possui uma suíte de testes unitários.
Executar todos os testes:
./mvnw test
Use code with caution.
Bash
Gerar o relatório de cobertura (Jacoco):
./mvnw jacoco:report
Use code with caution.
Bash
✅ O relatório estará disponível em target/site/jacoco/index.html.
💡 Possíveis Melhorias
Mensageria Assíncrona: Implementar um sistema de mensageria real (RabbitMQ ou Kafka) para o NotificationService, desacoplando o envio de notificações.
Testes de Integração: Adicionar testes de integração (@SpringBootTest) que utilizem Testcontainers para criar um ambiente de teste com banco de dados real.
Segurança: Implementar segurança na API com Spring Security e JWT/OAuth2 para autenticação e autorização de endpoints.
API Gateway: Adicionar um API Gateway (ex: Spring Cloud Gateway) na frente do microserviço para gerenciar rotas, rate limiting e segurança de forma centralizada.
CI/CD: Configurar um pipeline de Integração Contínua e Entrega Contínua (ex: GitHub Actions) para automatizar o build, testes e deploy da aplicação.
Monitoramento Avançado: Integrar ferramentas de monitoramento como Prometheus e Grafana para coletar métricas detalhadas da aplicação.
✒️ Autor
Desenvolvido por Wendeel Marinho.
<p>
<a href="https://github.com/WendeelMarinho" target="_blank">
<img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub">
</a>
<a href="https://www.linkedin.com/in/wendeel-marinho/" target="_blank">
<img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn">
</a>
</p>
