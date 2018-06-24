# Clientes
Rest
- Criar um Cliente
- Alterar um Cliente
- Consultar um Cliente por id
- Listar todos os Clientes salvos
- Remover Cliente por id
- Buscar localização geográfica, temperatura minima e máxima com base em um ip de origem.

## Incluir
  - Spring Boot 1.5.9
  - spring-boot-starter-data-jpa
  - spring-boot-starter-web
  - com.h2database:h2: H2 DB connector
  - spring-boot-devtools
  - spring-boot-starter-test
  - jersey-client
  - org.json
  - mockito-core

## Importante properties
application.properties
import.properties

# JDBC Url usar H2 DB arquivo para persistir os dados.
spring.datasource.url=jdbc:h2:file:~/testedb;

# Usar H2 DB Driver
spring.datasource.driver-class-name=org.h2.Driver

## Testes
-Testes Unitários podem ser feitos via IDE;

-Listar clientes: realizar chamada Get sem parametros.
http://localhost:8080/clientes

-Adicionar cliente: realizar chamada Post passando os parametros no body com application/json.  
http://localhost:8080/clientes

-Alterar cliente: realizar chamada Put passando os parametros a serem alterados no body como application/json e na url o codigo do cliente a ser alterado.
http://localhost:8080/clientes/{id}

-Consultar cliente: realizar chamada Get passando o codigo do cliente como na url.
http://localhost:8080/clientes/{id}

-Alterar cliente: realizar chamada Delete passando o codigo do cliente que deseja deletar na url.
http://localhost:8080/clientes/{id}

-Listar historico: realizar chamada Get sem parametros.
http://localhost:8080/historico/listar

## Ferramentas
-Spring Tools Suite
-PostMan para realizar as chamadas aos serviços (https://www.getpostman.com/apps), pode ser utilizado qualquer outro correspondente;

## Documentação
-Desenvolvedor (usando Spring Tools Suite):
baixar o projeto do Git;
baixar as dependencias via maven clean install;
executar no Boot Dashboard o projeto especifico;
para acessar o banco: com o servidor do projeto rodando acessar a url (http://localhost:8080/h2/) no browser com os dados abaixo:
spring.datasource.url=jdbc:h2:file:~/testedb
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
-Tester:
baixar o projeto do Git;
baixar as dependencias via maven clean install;

fazer as seguintes alterações no pom.xml:
mudar o trecho correspondente ao "org.springframework.boot":
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			 <exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
adicionar a dependencia:
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<scope>provided</scope>
		</dependency>
alterar o packaging de:
<packaging>jar</packaging>
para
<packaging>war</packaging>
rodar o maven clean install
subir o servidor da sua preferencia;
fazer o deploy do arquivo cliente-0.0.1-SNAPSHOT.war;

## OBS:
Fiz varios testes para retornar o endereco de IP valido, porem retorna 0:0:0:0:0:0:0:1, vi que a implementacao funciona, mas alguns casos o proxy bloqueia, entao coloquei um ipfixo para poder verificar o restante do código implementacao.
Interface IGerenciador método buscarIp.