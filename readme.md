
## API de CRUD de Pessoa
Esta é uma API de CRUD, desenvolvida com o uso de tecnologias como:

<code>Spring Boot 3.0.5</code>,<br>
<code>Spring Boot Data JPA</code>, <br>
<code>Spring Boot Validation</code>,<br>
<code>H2 Database</code>, <br>
<code>Lombok</code>,<br>
<code>springdoc</code>.

## Funcionalidades

* Cadastro de uma nova pessoa
* Atualização dos dados de uma pessoa já cadastrada
* Exclusão de uma pessoa
* Listagem de todas as pessoas cadastradas
* Consulta de uma pessoa específica
* Criar um endereço para uma pessoa
* Listar todos os endereços de uma pessoa
* Definir o endereço principal de uma pessoa

## Instalação

Para rodar a API em sua máquina, siga os seguintes passos:

Faça o download do repositório

No arquivo <code>application.properties</code>, configure as informações de acesso ao seu banco de dados
Execute o comando <code>mvn spring-boot:run</code> para rodar a aplicação.
Acesse a API através do endereço <code>http://localhost:8080/api/v1/person/endpoint </code>

## Endpoints

* URL base = "api/v1/person"

* GET: lista todas as pessoas cadastradas
* GET /{id}: consulta uma pessoa específica pelo seu ID
* POST: cadastra uma nova pessoa
* PUT /{id}: atualiza os dados de uma pessoa já cadastrada
* DELETE /{id}: exclui uma pessoa pelo seu ID
* GET /address/{personId}: Criar um endereço para uma pessoa
* GET /address/{personId}: Listar todos os endereços de uma pessoa
* POST address/main/{addressId}: Definir o endereço principal de uma pessoa

Com o projeto em execução é possivel acessar o swagger pelo link abaixo:
Link: http://localhost:8080/swagger-ui/index.html
