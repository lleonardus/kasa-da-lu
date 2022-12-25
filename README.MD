# 🎅‍Sobre o projeto🎄

Esse projeto foi feito com o intuito de exercitar minhas habilidades com Java e Spring.
"Kasa da Lu" é uma loja fictícia que vende rabanadas no natal. Através dessa API, é possível cadastrar clientes e 
ordenar pedidos!

## 💿Como rodar na sua máquina

**Pré requisitos:** Git e Java 17

``` shell
# Clone o repositório na sua máquina
$ git clone https://github.com/lleonardus/kasa-da-lu.git

# Abra a pasta do projeto
$ cd kasa-da-lu

# Inicie o projeto
$ ./mvnw spring-boot:run
```
Após esse processo, a API vai estar rodando em **http://localhost:8080** e o banco de dados (H2 database)
pode ser acessado em **http://localhost:8080/h2-console/**

## 💾Banco de Dados

![diagrama](images/diagrama.png)

Dentro de `src/main/resources` coloquei um arquivo chamado **data.sql** que já contém alguns dados
para testar a API. Caso queira fazer alguma alteração nesses dados, basta alterar o arquivo manualmente.

## 🎯Endpoints

### 👨‍👩‍👧‍👦Clients

- **GET** `/kasa-da-lu/clients`: Retorna uma lista com todos os clientes.
- **GET** `/kasa-da-lu/clients/{id}`: Retorna um cliente em específico.
- **POST** `/kasa-da-lu/clients`: Registra um novo cliente.
- **POST** `/kasa-da-lu/clients/{id}/buy?flavor={flavor}&quantity={quantity}`: Ordena o pedido de uma rabanada.
O parâmetro flavor permite os sabores **chocolate, doce de leite, tradicional e nutella**. O parâmetro quantity deve ser um inteiro maior que zero.
- **PUT** `/kasa-da-lu/clients/{id}`: Atualiza um cliente em específico.

### 🍞French Toast

- **GET** `/kasa-da-lu/toasts`: Retorna uma lista com todas as rabanadas disponíveis no cardápio.

### 📜Orders

- **GET** `/kasa-da-lu/orders`: Retorna uma lista com todos os pedidos.

### 👨‍🚀Postman🚀
Caso tenha o Postman instalado na sua máquina e queira ver exemplos de como usar os endpoints,
importe o arquivo que está na pasta `postman`.

## 🧰Ferramentas Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Model Mapper
- Postman