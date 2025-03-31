# Equaciona Fórum

Um simples fórum para estudantes do Ensino Fundamental, Médio e Superior que desejam tirar dúvidas e resoluções de questões das disciplinas de Física, Química e Matemática de vestibulares, concursos, materiais escolares, tarefas de casa etc. Foi desenvolvido com o objetivo de colocar em prática meus conhecimentos sobre programação front e back-end.

## Tecnologias

As principais tecnologias utilizadas para a criação do site foram:
* Java Spring Boot;
* Spring Security;
* React;
* Banco de dados H2 (em memória).

## Imagens
![Image](https://github.com/user-attachments/assets/cabc19dc-f7c6-4a14-afe5-e631f702ae4f)
![Image](https://github.com/user-attachments/assets/0d71addb-9e91-4796-8889-926f094405fe)
![Image](https://github.com/user-attachments/assets/50b462cb-68b6-4d7a-aad5-04f2e5ae37ab)
![Image](https://github.com/user-attachments/assets/208114a8-fc64-4169-8c7b-cf8cbe00c1b1)
## Features

* Criar uma conta;
* Autenticação de usuário;
* Buscar tópicos;
* Criar um novo tópico;
* Excluir tópicos;
* Comentar em um tópico.

## Como executar o projeto
### 1. Executando a API
```

// Clone este repositório
$ git clone https://github.com/rafaroseira/projeto-forum.git

// Certifique-se de ter o maven instalado. Caso não tenha, utilize o comando:
$ sudo apt install maven

// Abra a pasta principal do projeto e acesse a pasta da API (demo)
$ cd demo

// Execute a API
$ mvn spring-boot:run

// A API estará executando em localhost:8080
// A documentação da API encontra-se em localhost:8080/swagger-ui/index.html
```
### 2. Executando a aplicação
```
// Certifique-se de ter o Node instalado

// Acesse a pasta front-end
$ cd front-end

// Instale as dependências
$ npm install

// Execute a aplicação
$ npm start

// A aplicação estará executando em localhost:3000
```
