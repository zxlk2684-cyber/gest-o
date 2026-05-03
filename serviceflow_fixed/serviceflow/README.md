# ServiceFlow - Plataforma de Gestão de Serviços

![ServiceFlow Logo](https://via.placeholder.com/150x50?text=ServiceFlow)

## Visão Geral

O **ServiceFlow** é uma plataforma robusta de gestão de serviços desenvolvida para otimizar o fluxo de trabalho de empresas e profissionais autônomos que atuam em áreas como pintura, reformas, manutenção e outros serviços especializados. A aplicação permite gerenciar clientes, projetos, orçamentos e materiais de forma eficiente, centralizando todas as informações e processos em um único lugar.

Este projeto demonstra a capacidade de construir aplicações Full Stack com Java Spring Boot, utilizando Docker para containerização e facilitando o deploy em plataformas como Railway.

## Funcionalidades

*   **Gestão de Clientes:** Cadastro, consulta, atualização e exclusão de clientes, com detalhes de contato e histórico.
*   **Gestão de Projetos:** Criação e acompanhamento de projetos, definindo status, datas, valores e progresso.
*   **Gestão de Orçamentos:** Elaboração de orçamentos detalhados, com cálculo de mão de obra, materiais, descontos e status de aprovação.
*   **Gestão de Materiais:** Controle de materiais necessários e utilizados por projeto, com cálculo de custos.
*   **API RESTful:** Interface de programação de aplicações para integração com outros sistemas ou frontends.
*   **Documentação Interativa:** Swagger UI para explorar e testar os endpoints da API.

## Tecnologias Utilizadas

### Backend

*   **Java 17:** Linguagem de programação.
*   **Spring Boot 3.2.0:** Framework para desenvolvimento rápido de aplicações Java.
*   **Spring Data JPA:** Para persistência de dados com Hibernate.
*   **Lombok:** Para reduzir código boilerplate.
*   **Springdoc OpenAPI (Swagger UI):** Para documentação e teste da API.

### Banco de Dados

*   **PostgreSQL:** Banco de dados relacional robusto e escalável.

### Containerização e Orquestração

*   **Docker:** Para empacotar a aplicação e suas dependências em contêineres.
*   **Docker Compose:** Para definir e executar aplicações Docker multi-container.

## Pré-requisitos

Para executar este projeto localmente, você precisará ter instalado:

*   Java Development Kit (JDK) 17 ou superior
*   Maven 3.6.0 ou superior
*   Docker Desktop (inclui Docker Engine e Docker Compose)

## Como Executar Localmente

Siga os passos abaixo para configurar e executar o ServiceFlow em sua máquina local:

1.  **Clone o Repositório:**
    ```bash
    git clone https://github.com/seu-usuario/serviceflow.git
    cd serviceflow
    ```

2.  **Construa e Suba os Contêineres com Docker Compose:**
    ```bash
    docker-compose up --build
    ```
    Este comando irá:
    *   Construir a imagem Docker da aplicação Spring Boot.
    *   Iniciar um contêiner PostgreSQL.
    *   Iniciar o contêiner da aplicação, conectando-o ao banco de dados.

3.  **Acesse a Aplicação:**
    *   A API estará disponível em `http://localhost:8080/api`.
    *   A documentação interativa (Swagger UI) estará em `http://localhost:8080/api/swagger-ui.html`.

## Deploy no Railway

O ServiceFlow foi projetado para ser facilmente implantado em plataformas como o Railway. Siga os passos abaixo:

1.  **Crie um Repositório no GitHub:** Faça o upload do seu projeto para um novo repositório no GitHub.

2.  **Conecte ao Railway:**
    *   Acesse o [Railway](https://railway.app/) e faça login.
    *   Crie um novo projeto e selecione a opção "Deploy from GitHub Repo".
    *   Conecte seu repositório `serviceflow`.

3.  **Configuração de Variáveis de Ambiente:**
    O Railway detectará automaticamente o `Dockerfile` e o `docker-compose.yml`. Certifique-se de configurar as seguintes variáveis de ambiente no Railway para o serviço da aplicação, caso não sejam detectadas automaticamente ou se você quiser usar credenciais diferentes:

    | Variável        | Descrição                                  | Exemplo Padrão (local) |
    | :-------------- | :----------------------------------------- | :--------------------- |
    | `DB_HOST`       | Host do banco de dados                     | `db`                   |
    | `DB_PORT`       | Porta do banco de dados                    | `5432`                 |
    | `DB_NAME`       | Nome do banco de dados                     | `serviceflow`          |
    | `DB_USER`       | Usuário do banco de dados                  | `serviceflow`          |
    | `DB_PASSWORD`   | Senha do banco de dados                    | `serviceflow123`       |
    | `SERVER_PORT`   | Porta em que a aplicação será executada    | `8080`                 |
    | `JAVA_OPTS`     | Opções da JVM (ex: memória)                | `-Xmx512m -Xms256m`    |

    *Nota: O Railway geralmente provisiona um banco de dados PostgreSQL automaticamente e injeta as variáveis de ambiente corretas. Verifique a documentação do Railway para detalhes específicos sobre a conexão com o banco de dados provisionado.* 

4.  **Deploy:** O Railway iniciará o processo de build e deploy. Uma vez concluído, sua aplicação estará acessível através da URL fornecida pelo Railway.

## Estrutura do Projeto

```
serviceflow/
├── src/
│   ├── main/
│   │   ├── java/com/serviceflow/
│   │   │   ├── controller/       # Controladores REST
│   │   │   ├── service/          # Camada de Serviço (lógica de negócio)
│   │   │   ├── repository/       # Repositórios JPA para acesso a dados
│   │   │   ├── entity/           # Entidades de domínio (modelos de dados)
│   │   │   ├── dto/              # Objetos de Transferência de Dados
│   │   │   └── ServiceFlowApplication.java # Classe principal da aplicação
│   │   └── resources/
│   │       └── application.yml   # Configurações da aplicação
│   └── test/
│       └── java/com/serviceflow/ # Testes unitários e de integração
├── pom.xml                       # Configurações do Maven
├── Dockerfile                    # Instruções para construir a imagem Docker
├── docker-compose.yml            # Definição de serviços Docker (app e db)
└── README.md                     # Este arquivo
```

## Endpoints da API (Exemplos)

Você pode explorar todos os endpoints através do Swagger UI em `/api/swagger-ui.html`.

### Clientes

*   `POST /api/clientes`: Criar um novo cliente.
*   `GET /api/clientes`: Listar todos os clientes.
*   `GET /api/clientes/{id}`: Obter cliente por ID.
*   `PUT /api/clientes/{id}`: Atualizar cliente.
*   `DELETE /api/clientes/{id}`: Deletar cliente.

### Projetos

*   `POST /api/projetos`: Criar um novo projeto.
*   `GET /api/projetos`: Listar todos os projetos.
*   `GET /api/projetos/{id}`: Obter projeto por ID.
*   `GET /api/projetos/cliente/{clienteId}`: Listar projetos por cliente.
*   `PUT /api/projetos/{id}`: Atualizar projeto.
*   `DELETE /api/projetos/{id}`: Deletar projeto.

### Orçamentos

*   `POST /api/orcamentos`: Criar um novo orçamento.
*   `GET /api/orcamentos`: Listar todos os orçamentos.
*   `GET /api/orcamentos/{id}`: Obter orçamento por ID.
*   `GET /api/orcamentos/projeto/{projetoId}`: Listar orçamentos por projeto.
*   `PUT /api/orcamentos/{id}`: Atualizar orçamento.
*   `PUT /api/orcamentos/{id}/aprovar`: Aprovar orçamento.
*   `DELETE /api/orcamentos/{id}`: Deletar orçamento.

## Contribuição

Sinta-se à vontade para contribuir com melhorias, novas funcionalidades ou correções de bugs. Para isso, siga os passos:

1.  Faça um fork do projeto.
2.  Crie uma nova branch (`git checkout -b feature/nova-funcionalidade`).
3.  Faça suas alterações e commit (`git commit -m 'feat: adiciona nova funcionalidade'`).
4.  Envie para a branch (`git push origin feature/nova-funcionalidade`).
5.  Abra um Pull Request.

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## Contato

Alifer Guimarães do Nascimento - [Seu LinkedIn](https://www.linkedin.com/in/alifer-guimaraes)

---
