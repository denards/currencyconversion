# API de Conversão de Moeda

## Visão Geral
Esta aplicação Spring Boot fornece uma API REST para conversão de moedas, integrando-se ao serviço ExchangeRate-API e armazenando o histórico de conversões em um banco de dados.

## Índice
1. [Desenvolvimento Local](#desenvolvimento-local)
2. [Configuração do Docker](#configuração-do-docker)
3. [Implantação na AWS](#implantação-na-aws)
4. [Documentação da API](#documentação-da-api)
5. [Monitoramento](#monitoramento)

## Desenvolvimento Local

### Pré-requisitos
- Java 17 ou superior
- Maven 3.8+
- Docker (opcional)
- AWS CLI configurado (para implantação na nuvem)
- Terraform 1.0+
- Ansible 2.9+

### Construindo a Aplicação
```bash
# Repositório
git clone https://github.com/yourusername/currencyconversion.git
cd currencyconversion

# Build da aplicação
mvn clean package

# Execução dos testes
mvn test

# Execute a aplicação localmente
java -jar target/currencyconversion-1.0.0.jar

#Docker
docker build -t currency-conversion .
docker run -p 8080:8080 currency-conversion


### Configuração
Crie o arquivo `application.properties` em `src/main/resources`:
```properties
# Banco de Dados
spring.datasource.url=jdbc:h2:mem:conversiondb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true

# Chave da API do ExchangeRate-API
exchange.api.key=chave_api
```