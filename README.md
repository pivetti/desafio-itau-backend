# 🚀 Desafio Itaú Backend: API de Transações

Esta é uma API REST desenvolvida em Spring Boot projetada para o registro de transações financeiras e o cálculo de estatísticas em tempo real, utilizando uma janela de tempo configurável (padrão de 60 segundos). O projeto foi construído com foco em alta performance, boas práticas de backend e observabilidade.

Link do desafio: https://github.com/feltex/desafio-itau-backend

## 🛠️ Tecnologias Utilizadas

Java 17  
Spring Boot 3  
Spring Validation: Para validação de payloads  
Spring Boot Actuator: Para monitoramento de saúde  
Springdoc OpenAPI (Swagger): Documentação interativa  
JUnit 5 & AssertJ: Testes unitários e de integração  
Docker: Conteinerização da aplicação  
Maven: Gestão de dependências e build  

## 📋 Pré-requisitos

Antes de começar, você precisará ter instalado:  
Java 17 ou superior  
Maven 3.8+  
Docker (opcional)

## 📦 Como Construir o Projeto

Na raiz do projeto, utilize o Maven para gerar o artefato:

```bash
mvn clean package
```

Nota: Este comando executa os testes automatizados, compila o código e gera o arquivo .jar no diretório target/.

## ▶️ Como Executar a Aplicação

Opção 1: Via Maven

```bash
mvn spring-boot:run
```

Opção 2: Via JAR

```bash
java -jar target/desafio-itau-3.jar
```

A API ficará disponível em:  
http://localhost:8080

Opção 3: Via Docker

```bash
docker build -t desafio_itau_3 .
docker run -p 8080:8080 desafio_itau_3
```

## 📘 Documentação da API (Swagger)

A API possui documentação interativa para facilitar o consumo e testes:

Swagger UI: http://localhost:8080/swaggerOpenAPI  
JSON: http://localhost:8080/api-docs  

## ❤️ Monitoramento e Erros 

Healthcheck
Verifique a saúde da aplicação através do endpoint do Actuator:

GET /actuator/health

Tratamento de Erros  
A aplicação utiliza o padrão RFC 7807 (Problem Details for HTTP APIs). Isso garante respostas de erro consistentes e claras:

```json
{
  "type": "https://example.com/problems/business-rule",
  "title": "Business rule violation",
  "status": 422,
  "detail": "Invalid transaction",
  "instance": "/transacao"
}
```

## 🧪 Testes e Performance

Execução de Testes

```bash
mvn test
```

A suíte de testes cobre casos de sucesso, erros de validação, regras de negócio e cenários de borda (ex: transações antigas).

Análise de Performance  
Cálculo: Utiliza DoubleSummaryStatistics para processar métricas (soma, média, min, max, count).  
Complexidade de Tempo: O(n), onde n é o número de transações na janela ativa.  
Complexidade de Espaço: O(1) para o objeto de estatísticas, mantendo eficiência mesmo sob carga.

## 🏁 Considerações Finais

Este projeto demonstra a aplicação de conceitos sólidos de engenharia de software, incluindo separação de responsabilidades, observabilidade e containerização, visando um ambiente de produção robusto.

Este projeto foi desenvolvido exclusivamente para fins de treinamento.
