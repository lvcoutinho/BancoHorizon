# BancoHorizon

Este é um sistema bancário em Java chamado "Agência Horizon". Ele permite a criação de contas bancárias, depósitos, saques, transferências.

## Funcionalidades Principais

- **Cadastro de Clientes:** Os usuários podem se cadastrar no banco fornecendo seu nome, número de telefone e CPF.

- **Criação de Contas:** Os usuários podem criar contas bancárias do tipo Poupança ou Corrente. Cada conta é associada a um cliente.

- **Depósitos:** Os clientes podem fazer depósitos em suas contas, aumentando o saldo disponível.

- **Saques:** Os clientes podem realizar saques de suas contas, desde que tenham saldo suficiente.

- **Transferências:** Os clientes podem transferir dinheiro entre suas contas ou para contas de outros clientes.

## Como Usar

1. Compile o código Java em um ambiente de desenvolvimento Java, como o Eclipse ou o IntelliJ IDEA, ou use um compilador Java na linha de comando.

2. Execute o programa compilado pela classe "MenuHorizon".

3. Siga as instruções no menu principal para criar uma conta, acessar uma conta existente e realizar operações bancárias.

4. Siga as instruções do programa para realizar operações específicas, como depósitos, saques e transferências.

5. Para sair do programa, selecione a opção "Sair" no menu principal ou no menu de operações.

## Requisitos

- Java Development Kit (JDK) instalado em seu sistema.

## Estrutura do Código

- O código está organizado em classes que representam entidades como Pessoa, Conta e Transferência.

- **Modelos (Model Classes):** As classes modelo representam entidades de dados no sistema. Alguns dos principais modelos incluem:
    - `Pessoa`: Representa informações sobre os clientes, como nome, telefone e CPF.
    - `Conta`: Representa informações sobre as contas bancárias, incluindo tipo de conta, saldo e número de conta.
    - `Transferência`: Representa informações sobre transferências de dinheiro, incluindo valores e datas.

- O código usa o padrão DAO (Data Access Object) para interagir com o banco de dados (PostgreSQL). Você pode personalizar as classes DAO para armazenar dados em um banco de dados real.

- O código faz uso de BigDecimal para operações de aritmética de ponto flutuante para garantir precisão em cálculos monetários.

## Contribuições

Este projeto é fornecido como uma base de aprendizado e pode ser personalizado e expandido de acordo com suas necessidades. Se você encontrar problemas ou quiser adicionar mais recursos, sinta-se à vontade para contribuir com o projeto.
