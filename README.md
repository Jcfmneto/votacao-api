## Funcionalidades
- Cadastrar nova pauta  
- Abrir sessão de votação (tempo configurável ou 1 minuto por default)  
- Receber votos dos associados (Sim/Não, 1 voto por pauta)  
- Consultar votos por sessão ou associado  
- Obter resultado final da votação de uma pauta  

## Tecnologias
- Spring Boot 4.0.0  
- Spring Data JPA  
- H2 Database  
- Spring Validation  
- Springdoc OpenAPI e Swagger UI  
- Dependências de teste (spring-boot-starter-data-jpa-test, spring-boot-starter-webmvc-test)  

## Endpoints principais

### Voto
- POST /api/v1/voto  
- GET /api/v1/voto/sessao/{sessaoId}  
- GET /api/v1/voto/associado/{associadoId}  

### Sessão
- GET /api/v1/sessao  
- POST /api/v1/sessao  
- GET /api/v1/sessao/{id}  
- GET /api/v1/sessao/{id}/resultado  

### Pauta
- GET /api/v1/pauta  
- POST /api/v1/pauta  
- GET /api/v1/pauta/{id}  

### Associado
- GET /api/v1/associado  
- POST /api/v1/associado  
- GET /api/v1/associado/{id}  

## Explicação das escolhas de desenvolvimento
- Usado Spring Boot 4.0.0 
- Spring Data JPA para persistência de dado  
- H2 como banco em memória por simplicidade  
- Spring Validation para validar entradas de usuário  
- Springdoc OpenAPI e Swagger para documentação da API  
- Prefixo /api/v1 para versionamento da API
- Dependências de teste para garantir qualidade e cobertura  
- Estrutura em camadas controller → service → repository para organizar o código
  
## Como Rodar
- git clone https://github.com/seu-repo/votacao-api.git
- cd votacao-api
- mvn spring-boot:run
- Acessar documentação Swagger UI  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  

## Testes
Executar testes automatizados  
mvn test
