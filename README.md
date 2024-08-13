# Gerenciamento de Locais

## Sumário

- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Como Executar a Aplicação](#como-executar-a-aplicação)
- [Como Executar os Testes](#como-executar-os-testes)
- [Documentação Javadoc](#documentação-javadoc)
- [Endpoints da API](#endpoints-da-api)
- [Contribuição](#contribuição)

## Visão Geral

Este projeto é uma aplicação Java desenvolvida com Spring Boot para gerenciar locais com operações CRUD. Ele permite criar, listar, atualizar e remover locais, utilizando o banco de dados em memória H2 database.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **H2 Database**: Banco de dados relacional em memória para desenvolvimento e testes.
- **Swagger**: Ferramenta para documentação da API.
- **JUnit 5**: Framework para testes unitários.
- **Maven**: Ferramenta de gerenciamento de dependências e build.

## Configuração do Ambiente

1. **Clone o repositório**

   ```bash
   git clone https://github.com/VivianKailany/Gerenciador-de-Locais.git
   ```

2. **Navegue até o diretório do projeto**

   ```bash
    cd .\gerenciador-de-locais\gerenciador-de-locais\
   ```

3. **Certifique-se de que o Maven está instalado**. Caso não tenha o Maven instalado, siga as instruções [aqui](https://maven.apache.org/install.html).

## Como Executar a Aplicação

1. **Compile e execute a aplicação**

   ```bash
   mvn spring-boot:run
   ```

   Isso iniciará a aplicação na porta padrão 8080.

2. **Acesse a aplicação**

   Abra o navegador e vá para `http://localhost:8080/swagger-ui/index.html#/` para verificar se a aplicação está funcionando.

## Como Executar os Testes

1. **Execute os testes unitários**

   ```bash
   mvn test
   ```

   Isso executará todos os testes definidos na aplicação e exibirá os resultados no console.

2. **Gerar o relatório de testes**

   Após a execução dos testes, o relatório estará disponível em `target/surefire-reports`.

## Documentação Javadoc

A documentação Javadoc para o projeto pode ser visualizada localmente após a construção do projeto. Para gerar e visualizar a documentação Javadoc, siga estes passos:

1. **Gerar a Documentação Javadoc:**

   Execute o seguinte comando Maven para gerar a documentação:

   ```bash
   mvn javadoc:javadoc
   ```

   Isso criará a documentação Javadoc no diretório `target/site/apidocs`.

2. **Localizar e Visualizar a Documentação:**

   Navegue até o diretório onde a documentação foi gerada:

   ```bash
   cd target/site/apidocs
   ```

   Abra o arquivo `index.html` com um navegador da web para visualizar a documentação:

   ```bash
   start index.html
   ```

   (No Linux, substitua `start` por `xdg-open`.)

## Endpoints da API

Aqui estão alguns exemplos de endpoints disponíveis na API:

- **Criar Local**

  ```http
  POST /locais
  ```

  Corpo da solicitação:
  ```json
  {
    "nome": "Praça da Fonte",
    "bairro": "Centro",
    "cidade": "Pacatuba",
    "estado": "CE"
  }
  ```

- **Listar Todos os Locais por ordem de criação**

  ```http
  GET /locais
  ```

- **Listar Todos os Locais por id**

  ```http
  GET /locais/{id}
  ```

- **Listar os Locais por nome**

  ```http
  GET /locais/nome/{nome}
  ```

- **Atualizar Local**

  ```http
  PUT /locais/{id}
  ```

  Corpo da solicitação:
  ```json
  {
    "nome": "Praça da Fonte atualizada",
    "bairro": "Centro",
    "cidade": "Pacatuba",
    "estado": "CE"
  }
  ```

- **Excluir Local por id**

  ```http
  DELETE /locais/{id}
  ```

- **Excluir Local por nome**

  ```http
  DELETE /nome/{nome}
  ```

## Contribuição

Se você deseja contribuir para este projeto, por favor, siga os passos abaixo:

1. **Faça um Fork do repositório**
2. **Crie uma branch para a sua feature**

   ```bash
   git checkout -b minha-feature
   ```

3. **Faça suas alterações e faça commit**

   ```bash
   git commit -am 'Adiciona nova feature'
   ```

4. **Envie a branch para o GitHub**

   ```bash
   git push origin minha-feature
   ```

5. **Crie um Pull Request**

