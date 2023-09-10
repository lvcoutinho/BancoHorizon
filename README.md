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

## Query do Banco de dados 

(PostgreSQL)

- Primeira table (Pessoa):

- -- Table: public.pessoa

-- DROP TABLE IF EXISTS public.pessoa;

CREATE TABLE IF NOT EXISTS public.pessoa
(
    id bigint NOT NULL DEFAULT nextval('pessoa_id_seq'::regclass),
    nome character varying COLLATE pg_catalog."default",
    telefone character varying COLLATE pg_catalog."default",
    cpf character varying COLLATE pg_catalog."default",
    CONSTRAINT pessoa_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.pessoa
    OWNER to postgres;

- Segunda table (Conta):

- -- Table: public.conta

-- DROP TABLE IF EXISTS public.conta;

CREATE TABLE IF NOT EXISTS public.conta
(
    id bigint NOT NULL DEFAULT nextval('conta_id_seq'::regclass),
    pessoa_id integer,
    numero integer,
    digito integer,
    saldo numeric,
    tipo_conta character varying COLLATE pg_catalog."default",
    CONSTRAINT conta_pkey PRIMARY KEY (id),
    CONSTRAINT conta_pessoa_id_fkey FOREIGN KEY (pessoa_id)
        REFERENCES public.pessoa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.conta
    OWNER to postgres;

- Terceira table (Transferencia):

- -- Table: public.transferencia

-- DROP TABLE IF EXISTS public.transferencia;

CREATE TABLE IF NOT EXISTS public.transferencia
(
    id bigint NOT NULL DEFAULT nextval('transferencia_id_seq'::regclass),
    conta_origem_id integer,
    conta_destino_id integer,
    valor numeric,
    data date,
    CONSTRAINT transferencia_pkey PRIMARY KEY (id),
    CONSTRAINT transferencia_conta_destino_id_fkey FOREIGN KEY (conta_destino_id)
        REFERENCES public.conta (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT transferencia_conta_origem_id_fkey FOREIGN KEY (conta_origem_id)
        REFERENCES public.conta (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.transferencia
    OWNER to postgres;
