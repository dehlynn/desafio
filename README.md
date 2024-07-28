# Projeto de Validação de JWT

Aplicação responsável por validar token JTW

## Descrição

Este projeto é uma aplicação em Spring Boot que fornece um serviço para validar JSON Web Tokens (JWT).
Ele verifica se um token JWT é valido (possui a estrutura de três partes) e se contém as claims estabelecidas como
padrão,
verificando se essas claims atendem aos requisitos especificados.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.2
- Gradle
- Dependencias:
    - Lombok
    - SpringDoc OpenAPI
    - JSON Web Token (JWT)
    - SLF4J e Logback para logging

## Estrutura do Projeto

- O projeto está estuturado da seguinte forma:
```plaintext
  com.github.dehlynn.desafio
  ├── controller
  │   └── Controller.java
  ├── service
  │   ├── TokenService.java
  │   └── impl
  │       └── TokenServiceImpl.java
  ├── util
  │   ├── ClaimsUtils.java
  │   └── JwtUtils.java
  └── DesafioApplication.java
 ```
- 
- *controller* 

  Controller.java: Classe responsável por definir os endpoints da API. 
Atualmente, contém um endpoint /jwt/validar que recebe um token JWT como parâmetro e retorna se ele é válido ou não.
Utiliza o serviço TokenService para a validação do token.


- *service*

  TokenService.java: Interface que define o contrato para os serviços de token. Declara o método validar para validar um token JWT.

  TokenServiceImpl.java: Implementação da interface TokenService. Contém a lógica para validar o token JWT, incluindo a verificação do formato do token, a quantidade de claims e a validação individual de cada claim usando as classes utilitárias JwtUtils e ClaimsUtils.


- *util*

  ClaimsUtils.java: Classe utilitária para validação dos claims extraídos de um token JWT. Contém métodos para verificar se os valores dos claims Name, Role e Seed atendem aos requisitos específicos. 
  JwtUtils.java: Classe utilitária para manipulação de tokens JWT. Inclui métodos para decodificar partes de um token JWT, verificar a validade de um JSON, extrair claims do token e verificar a quantidade de claims presentes.



- *Aplicação Principal*

  DesafioApplication.java: Classe principal da aplicação Spring Boot. 
  Contém o método main que inicia a aplicação.

### Pré-requisitos

- JDK 17
- Gradle
- IDE
- Postman

###
Para executar a aplicação:

```bash
# Clone este repositório
$ git clone https://github.com/dehlynn/desafio.git
$ cd desafio


# Na pasta raiz do projeto, execute o seguinte comando:
$ docker-compose up --build -d

# O servidor iniciará na porta 8080 - acesse <http://localhost:8080>
```

### Endpoint da API

* Validar JWT
* URL: http://localhost:8080/jwt/validar
* Método: POST
* Parâmetros:
    * token (String): O token JWT a ser validado.
* Resposta: Boolean indicando se o token é válido ou não
* Exemplo de requisição:
```
  curl -X POST "http://localhost:8080/jwt/validar" -d "token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
````

### Documentação OpenAPI

É possível verificar o endpoint e parâmetro necessário para execução na API do Swagger UI:
````
http://localhost:8080/swagger-ui/index.html
````

## Testes

### Execução

- Para executar os teste utilize os seguintes comandos:
 ````sh
./gradlew test
````


### Estrutura

Os testes estão no diretório 
```plaintext
src/test/java/com/github/dehlynn/desafio
├── controller
│   └── ControllerTest.java
├── service
│   ├── TokenServiceTest.java
│   └── impl
│       └── TokenServiceImplTest.java
├── util
│   ├── ClaimsUtilsTest.java
│   └── JwtUtilsTest.java

````
E estão estruturados da seguinte forma:
- TokenServiceImplTest: Verifica a funcionalidade do serviço de tokens.
- JwtUtilsTest: Verifica a funcionalidade das utilidades relacionadas ao processamento de JWTs.
- ClaimsUtilsTest: Verifica se os requisitos determinados para os cada um dos claims atendem o esperado.


## Contribuição

Contribuições são bem-vindas! Fique à vontade para abrir issues e pull requests

### Contato:

Para mais informações, entre em contato com [Delaine] em [delainelynn@delaine].

