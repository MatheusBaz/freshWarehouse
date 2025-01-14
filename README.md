## Informações do Fork

Foi adicionado dois atributos na tabela Batch, Status e valor da liquidação.
Foi feito 2 endpoints:
  - GET: Busca por uma faixa de data os lotes que foram ou estão sendo liquidados, assim como o valor do desconto.
  - PUT: Atualiza o status da liquidação ou o valor dela.

Foi construido o Controler, Service e Repository para essa mudança, assim como os testes para o Controler e Service.

<p align="center">
        <img src="https://avatars.githubusercontent.com/u/109238400?s=400&u=e5b242311297e5a0b1c2a7e4efd42d523c158b59&v=4">
</p>

## Sobre

O FreshWarehouse é uma aplicação feita para ajudar a administrar um estoque de produtos frescos em um Marketplace. Consultando sua API é possivel passar por todo o processo desde quando um vendedor cadastra um produto, até ele ser adicionado em um lote, enviado para um armazem, até ser adicionado em um carrinho de compras por um usuário.


## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando o framework [Spring](https://spring.io/projects/spring-boot) escrito em [Java](https://www.java.com/pt-BR/) e os testes foram feitos utilizando o [JUnit](https://junit.org/junit5/). A API funciona por meio de requisições HTTP, e como meta futura, o deploy seria feito por meio do Fury. A gerencia de dependências é feita pelo [Maven](https://maven.apache.org/).

## Documentação

Documentação da API foi feita em JavaDoc, assim como as collections do postman para fazerem as requisições podem ser encontradas em: [Documentos](https://github.com/javatastico/freshWarehouse/tree/develop/Documents).


## UML

<p align="center">
  <img src="https://github.com/javatastico/freshWarehouse/blob/develop/Documents/UML.png?raw=true">
</p>

## Licença

Este projeto está licenciado sob os termos da [licença MIT](https://github.com/javatastico/freshWarehouse/blob/develop/LICENSE).

Copyright (c) 2022 Javatastico
