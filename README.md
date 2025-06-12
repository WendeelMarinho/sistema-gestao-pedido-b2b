<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestão de Pedidos B2B</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
            line-height: 1.6;
            color: #333;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background: white;
            margin-top: 20px;
            margin-bottom: 20px;
            border-radius: 16px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.1);
        }

        .header {
            text-align: center;
            padding: 40px 0;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            margin: -20px -20px 40px -20px;
            border-radius: 16px 16px 0 0;
        }

        .header h1 {
            font-size: 3rem;
            margin-bottom: 10px;
            font-weight: 700;
        }

        .header p {
            font-size: 1.2rem;
            opacity: 0.9;
            margin-bottom: 30px;
        }

        .badges {
            display: flex;
            justify-content: center;
            gap: 10px;
            flex-wrap: wrap;
            margin-bottom: 20px;
        }

        .badge {
            padding: 8px 16px;
            border-radius: 20px;
            font-size: 0.9rem;
            font-weight: 600;
            text-decoration: none;
            color: white;
            transition: transform 0.2s;
        }

        .badge:hover {
            transform: translateY(-2px);
        }

        .badge.java { background: #ED8B00; }
        .badge.spring { background: #6DB33F; }
        .badge.docker { background: #2496ED; }
        .badge.postgresql { background: #316192; }
        .badge.maven { background: #C71A36; }

        .nav-links {
            display: flex;
            justify-content: center;
            gap: 20px;
            flex-wrap: wrap;
        }

        .nav-link {
            color: white;
            text-decoration: none;
            padding: 10px 20px;
            border: 2px solid rgba(255,255,255,0.3);
            border-radius: 25px;
            transition: all 0.3s;
        }

        .nav-link:hover {
            background: rgba(255,255,255,0.2);
            transform: translateY(-2px);
        }

        .section {
            margin: 40px 0;
        }

        .section h2 {
            color: #2d3748;
            font-size: 2.2rem;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .section h3 {
            color: #4a5568;
            font-size: 1.5rem;
            margin: 25px 0 15px 0;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
            margin: 20px 0;
        }

        .card {
            background: white;
            border: 1px solid #e2e8f0;
            border-radius: 12px;
            padding: 25px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.05);
            transition: all 0.3s;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
        }

        .card h4 {
            color: #2d3748;
            margin-bottom: 15px;
            font-size: 1.3rem;
        }

        .feature-list {
            list-style: none;
            padding: 0;
        }

        .feature-list li {
            padding: 8px 0;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .feature-list li::before {
            content: "✅";
            font-size: 1.1rem;
        }

        .code-block {
            background: #1a202c;
            color: #e2e8f0;
            padding: 20px;
            border-radius: 8px;
            margin: 15px 0;
            overflow-x: auto;
            font-family: 'Fira Code', monospace;
            position: relative;
        }

        .code-block::before {
            content: attr(data-lang);
            position: absolute;
            top: 5px;
            right: 10px;
            font-size: 0.8rem;
            color: #a0aec0;
            text-transform: uppercase;
        }

        .copy-btn {
            position: absolute;
            top: 10px;
            right: 60px;
            background: #4299e1;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 0.8rem;
            transition: background 0.2s;
        }

        .copy-btn:hover {
            background: #3182ce;
        }

        .table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 6px rgba(0,0,0,0.05);
        }

        .table th,
        .table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #e2e8f0;
        }

        .table th {
            background: #f7fafc;
            font-weight: 600;
            color: #2d3748;
        }

        .table tr:hover {
            background: #f7fafc;
        }

        .status-badge {
            padding: 4px 8px;
            border-radius: 12px;
            font-size: 0.8rem;
            font-weight: 600;
        }

        .status-pendente { background: #fed7d7; color: #c53030; }
        .status-aprovado { background: #c6f6d5; color: #38a169; }
        .status-enviado { background: #bee3f8; color: #3182ce; }
        .status-entregue { background: #d4edda; color: #155724; }
        .status-cancelado { background: #f8d7da; color: #721c24; }
        .status-rejeitado { background: #f5c6cb; color: #721c24; }

        .alert {
            padding: 15px;
            border-radius: 8px;
            margin: 15px 0;
            border-left: 4px solid;
        }

        .alert.info {
            background: #ebf8ff;
            border-color: #3182ce;
            color: #2c5282;
        }

        .alert.warning {
            background: #fffbeb;
            border-color: #d69e2e;
            color: #744210;
        }

        .alert.success {
            background: #f0fff4;
            border-color: #38a169;
            color: #22543d;
        }

        .architecture-diagram {
            text-align: center;
            margin: 30px 0;
            padding: 20px;
            background: #f7fafc;
            border-radius: 8px;
        }

        .layer {
            background: white;
            border: 2px solid #e2e8f0;
            border-radius: 8px;
            padding: 15px;
            margin: 5px 0;
            font-weight: 600;
            color: #2d3748;
        }

        .layer.controller { border-color: #4299e1; }
        .layer.service { border-color: #38a169; }
        .layer.repository { border-color: #ed8936; }
        .layer.model { border-color: #9f7aea; }

        .highlight {
            background: linear-gradient(120deg, #a8edea 0%, #fed6e3 100%);
            padding: 2px 6px;
            border-radius: 4px;
            font-weight: 600;
        }

        .footer {
            text-align: center;
            padding: 40px 0;
            background: #2d3748;
            color: white;
            margin: 40px -20px -20px -20px;
            border-radius: 0 0 16px 16px;
        }

        .footer h3 {
            margin-bottom: 15px;
        }

        .footer a {
            color: #63b3ed;
            text-decoration: none;
            margin: 0 15px;
        }

        .footer a:hover {
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            .header h1 {
                font-size: 2rem;
            }
            
            .container {
                margin: 10px;
                padding: 15px;
            }
            
            .grid {
                grid-template-columns: 1fr;
            }
            
            .nav-links {
                flex-direction: column;
                align-items: center;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <header class="header">
            <h1>🛒 Sistema de Gestão de Pedidos B2B</h1>
            <p>Microserviço RESTful para gerenciamento de pedidos B2B com alta concorrência e escalabilidade</p>
            
            <div class="badges">
                <a href="#" class="badge java">Java 17</a>
                <a href="#" class="badge spring">Spring Boot 3.3.0</a>
                <a href="#" class="badge docker">Docker</a>
                <a href="#" class="badge postgresql">PostgreSQL</a>
                <a href="#" class="badge maven">Maven</a>
            </div>
            
            <div class="nav-links">
                <a href="#sobre" class="nav-link">🚀 Começar</a>
                <a href="#endpoints" class="nav-link">📖 Documentação</a>
                <a href="#testes" class="nav-link">🧪 Testes</a>
                <a href="#autor" class="nav-link">🛠️ Contribuir</a>
            </div>
        </header>

        <section id="sobre" class="section">
            <h2>📜 Sobre o Projeto</h2>
            <p>Este projeto é um <span class="highlight">microserviço RESTful</span> robusto para gerenciamento de pedidos B2B, desenvolvido como solução para um desafio técnico. A aplicação é responsável por receber, processar e gerenciar pedidos de parceiros comerciais, com foco em:</p>
            
            <div class="grid">
                <div class="card">
                    <h4>🔄 Escalabilidade Horizontal</h4>
                    <p>Arquitetura preparada para crescer conforme a demanda, com containers Docker e load balancing.</p>
                </div>
                <div class="card">
                    <h4>⚡ Alta Concorrência</h4>
                    <p>Sistema de lock otimista para garantir consistência em operações simultâneas.</p>
                </div>
                <div class="card">
                    <h4>🛡️ Consistência de Dados</h4>
                    <p>Transações ACID e controle de versioning para integridade dos dados.</p>
                </div>
                <div class="card">
                    <h4>📊 Observabilidade</h4>
                    <p>Monitoramento integrado com Spring Actuator e health checks.</p>
                </div>
            </div>
        </section>

        <section class="section">
            <h2>✨ Funcionalidades</h2>
            
            <div class="grid">
                <div class="card">
                    <h4>🔧 API REST Completa</h4>
                    <ul class="feature-list">
                        <li>CRUD completo para pedidos</li>
                        <li>Filtros avançados por status e período</li>
                        <li>Busca otimizada com paginação</li>
                        <li>Validação de dados robuста</li>
                    </ul>
                </div>
                
                <div class="card">
                    <h4>💰 Sistema de Crédito Inteligente</h4>
                    <ul class="feature-list">
                        <li>Validação automática de limite</li>
                        <li>Débito na aprovação</li>
                        <li>Restauração no cancelamento</li>
                        <li>Controle de concorrência</li>
                    </ul>
                </div>
                
                <div class="card">
                    <h4>📧 Sistema de Notificações</h4>
                    <ul class="feature-list">
                        <li>Notificações automáticas</li>
                        <li>Múltiplos canais (simulado)</li>
                        <li>Tracking de eventos</li>
                        <li>Logs estruturados</li>
                    </ul>
                </div>
            </div>
        </section>

        <section class="section">
            <h2>🏗️ Arquitetura e Decisões de Design</h2>
            
            <h3>🏛️ Arquitetura em Camadas</h3>
            <div class="architecture-diagram">
                <div class="layer controller">Controller - REST API Layer</div>
                <div class="layer service">Service - Business Logic</div>
                <div class="layer repository">Repository - Data Access Layer</div>
                <div class="layer model">Model - Entity Layer</div>
            </div>
            
            <h3>🔐 Tratamento de Concorrência</h3>
            <p><strong>Lock Otimista</strong> implementado na entidade Parceiro:</p>
            
            <div class="code-block" data-lang="java">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
@Entity
public class Parceiro {
    @Version
    private Long version; // Controle de concorrência
    
    private BigDecimal limiteCredito;
    // ...
}
            </div>
            
            <div class="alert info">
                <strong>💡 Benefícios:</strong> Previne condições de corrida, mantém consistência de dados, performance superior ao lock pessimista e escalabilidade horizontal.
            </div>
        </section>

        <section class="section">
            <h2>🚀 Tecnologias Utilizadas</h2>
            
            <table class="table">
                <thead>
                    <tr>
                        <th>🛠️ Categoria</th>
                        <th>📦 Tecnologia</th>
                        <th>📝 Descrição</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Backend</td>
                        <td>Java 17</td>
                        <td>LTS com melhorias de performance</td>
                    </tr>
                    <tr>
                        <td>Framework</td>
                        <td>Spring Boot 3.3.0</td>
                        <td>Framework principal</td>
                    </tr>
                    <tr>
                        <td>Persistência</td>
                        <td>Spring Data JPA + Hibernate</td>
                        <td>ORM e acesso a dados</td>
                    </tr>
                    <tr>
                        <td>Banco de Dados</td>
                        <td>PostgreSQL</td>
                        <td>Banco relacional robusto</td>
                    </tr>
                    <tr>
                        <td>Containerização</td>
                        <td>Docker & Docker Compose</td>
                        <td>Ambiente consistente</td>
                    </tr>
                    <tr>
                        <td>Build</td>
                        <td>Maven</td>
                        <td>Gerenciamento de dependências</td>
                    </tr>
                    <tr>
                        <td>Documentação</td>
                        <td>Springdoc OpenAPI</td>
                        <td>Swagger UI automático</td>
                    </tr>
                    <tr>
                        <td>Testes</td>
                        <td>JUnit 5 & Mockito</td>
                        <td>Testes automatizados</td>
                    </tr>
                </tbody>
            </table>
        </section>

        <section class="section">
            <h2>⚙️ Pré-requisitos</h2>
            
            <table class="table">
                <thead>
                    <tr>
                        <th>🛠️ Ferramenta</th>
                        <th>📋 Versão</th>
                        <th>🎯 Necessário para</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><strong>Docker</strong></td>
                        <td>20.10+</td>
                        <td>✅ Execução (Recomendado)</td>
                    </tr>
                    <tr>
                        <td><strong>Docker Compose</strong></td>
                        <td>2.0+</td>
                        <td>✅ Execução (Recomendado)</td>
                    </tr>
                    <tr>
                        <td><strong>Java</strong></td>
                        <td>17+</td>
                        <td>⚠️ Desenvolvimento local</td>
                    </tr>
                    <tr>
                        <td><strong>Maven</strong></td>
                        <td>3.8+</td>
                        <td>⚠️ Build local</td>
                    </tr>
                </tbody>
            </table>
            
            <div class="alert info">
                <strong>💡 Dica:</strong> Use Docker para uma configuração mais rápida e sem dependências locais!
            </div>
        </section>

        <section class="section">
            <h2>🚀 Guia de Execução</h2>
            
            <h3>🐳 Método Recomendado: Docker Compose</h3>
            
            <h4>1️⃣ Clone o Repositório</h4>
            <div class="code-block" data-lang="bash">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
git clone https://github.com/WendeelMarinho/sistema-gestao-pedido-b2b.git
cd sistema-gestao-pedido-b2b
            </div>
            
            <h4>2️⃣ Construa a Aplicação</h4>
            <div class="code-block" data-lang="bash">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
# Linux/macOS/WSL
./mvnw clean package

# Windows
mvnw.cmd clean package
            </div>
            
            <h4>3️⃣ Inicie os Serviços</h4>
            <div class="code-block" data-lang="bash">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
docker compose up --build -d
            </div>
            
            <h4>4️⃣ Verifique o Status</h4>
            <div class="code-block" data-lang="bash">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
# Verificar logs
docker compose logs -f

# Verificar containers
docker compose ps
            </div>
            
            <h3>🌐 Acessos Disponíveis</h3>
            <table class="table">
                <thead>
                    <tr>
                        <th>🔗 Serviço</th>
                        <th>📍 URL</th>
                        <th>📝 Descrição</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><strong>API</strong></td>
                        <td><a href="http://localhost:8080">http://localhost:8080</a></td>
                        <td>Endpoints REST</td>
                    </tr>
                    <tr>
                        <td><strong>Swagger UI</strong></td>
                        <td><a href="http://localhost:8080/swagger-ui.html">http://localhost:8080/swagger-ui.html</a></td>
                        <td>Documentação interativa</td>
                    </tr>
                    <tr>
                        <td><strong>Health Check</strong></td>
                        <td><a href="http://localhost:8080/actuator/health">http://localhost:8080/actuator/health</a></td>
                        <td>Status da aplicação</td>
                    </tr>
                </tbody>
            </table>
            
            <h3>🛑 Parando a Aplicação</h3>
            <div class="code-block" data-lang="bash">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
docker compose down
            </div>
        </section>

        <section id="endpoints" class="section">
            <h2>📡 Endpoints da API</h2>
            
            <div class="alert info">
                <strong>📚 Documentação Interativa:</strong> 
                <a href="http://localhost:8080/swagger-ui.html">http://localhost:8080/swagger-ui.html</a>
            </div>
            
            <h3>🚀 Exemplos Práticos</h3>
            
            <h4>📝 Criar um Pedido</h4>
            <div class="code-block" data-lang="curl">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
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
            </div>
            
            <h4>✅ Aprovar um Pedido</h4>
            <div class="code-block" data-lang="curl">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
curl -X 'PATCH' \
  'http://localhost:8080/pedidos/{ID_DO_PEDIDO}/status' \
  -H 'Content-Type: application/json' \
  -d '"APROVADO"'
            </div>
            
            <h4>🔍 Buscar Pedidos por Status</h4>
            <div class="code-block" data-lang="curl">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
curl -X 'GET' \
  'http://localhost:8080/pedidos?status=PENDENTE&page=0&size=10'
            </div>
            
            <h4>📊 Buscar Pedidos por Período</h4>
            <div class="code-block" data-lang="curl">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
curl -X 'GET' \
  'http://localhost:8080/pedidos?dataInicio=2024-01-01&dataFim=2024-12-31'
            </div>
            
            <h3>📋 Status Disponíveis</h3>
            <table class="table">
                <thead>
                    <tr>
                        <th>🏷️ Status</th>
                        <th>📝 Descrição</th>
                        <th>💰 Ação no Crédito</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><span class="status-badge status-pendente">PENDENTE</span></td>
                        <td>Pedido aguardando aprovação</td>
                        <td>-</td>
                    </tr>
                    <tr>
                        <td><span class="status-badge status-aprovado">APROVADO</span></td>
                        <td>Pedido aprovado</td>
                        <td>➖ Débito</td>
                    </tr>
                    <tr>
                        <td><span class="status-badge status-enviado">ENVIADO</span></td>
                        <td>Pedido enviado</td>
                        <td>-</td>
                    </tr>
                    <tr>
                        <td><span class="status-badge status-entregue">ENTREGUE</span></td>
                        <td>Pedido entregue</td>
                        <td>-</td>
                    </tr>
                    <tr>
                        <td><span class="status-badge status-cancelado">CANCELADO</span></td>
                        <td>Pedido cancelado</td>
                        <td>➕ Crédito restaurado</td>
                    </tr>
                    <tr>
                        <td><span class="status-badge status-rejeitado">REJEITADO</span></td>
                        <td>Pedido rejeitado</td>
                        <td>-</td>
                    </tr>
                </tbody>
            </table>
        </section>

        <section id="testes" class="section">
            <h2>🧪 Executando os Testes</h2>
            
            <h3>🔬 Testes Unitários</h3>
            <div class="code-block" data-lang="bash">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
./mvnw test
            </div>
            
            <h3>📊 Relatório de Cobertura</h3>
            <div class="code-block" data-lang="bash">
                <button class="copy-btn" onclick="copyCode(this)">Copiar</button>
./mvnw jacoco:report
            </div>
            
            <div class="alert success">
                <strong>✅ Cobertura de Testes:</strong> O projeto mantém alta cobertura de testes unitários para garantir a qualidade do código.
            </div>
        </section>

        <section class="section">
            <h2>💡 Possíveis Melhorias</h2>
            
            <div class="grid">
                <div class="card">
                    <h4>🔄 Mensageria</h4>
                    <p>Implementar sistema de mensageria real (RabbitMQ ou Kafka) para o NotificationService.</p>
                </div>
                <div class="card">
                    <h4>🧪 Testes de Integração</h4>
                    <p>Adicionar testes de integração (@SpringBootTest) que utilizem Testcontainers.</p>
                </div>
                <div class="card">
                    <h4>🔐 Segurança</h4>
                    <p>Implementar segurança na API com Spring Security e JWT.</p>
                </div>
                <div class="card">
                    <h4>🚪 API Gateway</h4>
                    <p>Adicionar um API Gateway para gerenciar o tráfego.</p>
                </div>
                <div class="card">
                    <h4>🔄 CI/CD</h4>
                    <p>Configurar pipeline de CI/CD (GitHub Actions) para automatizar builds e testes.</p>
                </div>
                <div class="card">
                    <h4>📊 Monitoramento</h4>
                    <p>Integrar ferramentas de monitoramento como Prometheus e Grafana.</p>
                </div>
            </div>
        </section>

        <footer id="autor" class="footer">
            <h3>✒️ Autor</h3>
            <p><strong>Wendeel Marinho</strong></p>
            <div>
                <a href="https://github.com/WendeelMarinho">🐙 GitHub</a>
                <a href="https://www.linkedin.com/in/wendeel-marinho/">💼 LinkedIn</a>
            </div>
        </footer>
    </div>

    <script>
        function copyCode(button) {
            const codeBlock = button.parentElement;
            const code = codeBlock.textContent.replace('Copiar', '').trim();
            
            navigator.clipboard.writeText(code).then(() => {
                const originalText = button.textContent;
                button.textContent = 'Copiado!';
                button.style.background = '#38a169';
                
                setTimeout(() => {
                    button.textContent = originalText;
                    button.style.background = '#4299e1';
                }, 2000);
            }).catch(err => {
                console.error('Erro ao copiar:', err);
                button.textContent =
