CREATE DATABASE IF NOT EXISTS projeto_barbearia;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS endereco(
    cep VARCHAR(9) PRIMARY KEY NOT NULL,
    cidade TEXT NOT NULL,
    estado TEXT NOT NULL,
    bairro TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS filial(
    id_filial UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome TEXT NOT NULL,
    pass VARCHAR(8) NOT NULL,
    email TEXT UNIQUE NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    qtd_profissionais INTEGER DEFAULT 0,
    telefone TEXT[],
    endereco VARCHAR(9) REFERENCES endereco(cep)
);

CREATE TABLE IF NOT EXISTS profissional(
    id_prof UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_filial UUID REFERENCES filial(id_filial),
    nome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    telefone TEXT[]
);

CREATE TABLE IF NOT EXISTS cliente(
    id_cliente UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    pass VARCHAR(8) NOT NULL,
    telefone TEXT[],
    endereco VARCHAR(9) REFERENCES endereco(cep)
);

CREATE TABLE IF NOT EXISTS tiposervico(
    id_servico BIGSERIAL PRIMARY KEY NOT NULL,
    nome TEXT NOT NULL,
    duracao INTEGER DEFAULT 0,
    preco MONEY NOT NULL,
    qt_servico INTEGER DEFAULT 0,
    id_cliente UUID REFERENCES cliente(id_cliente)
);

CREATE TABLE IF NOT EXISTS ordemservico(
    id_os BIGSERIAL PRIMARY KEY NOT NULL,
    id_cliente UUID REFERENCES cliente(id_cliente),
    id_prof UUID REFERENCES profissinal(id_prof),
    agendamento TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS associacao_os_servico(
    id_os INTEGER PRIMARY KEY REFERENCES ordemservico(id_os),
    id_tiposervico INTEGER REFERENCES tiposervico(id_servico)
);

CREATE OR REPLACE VIEW endereco_cliente AS
    SELECT nome, email, telefone, cep, cidade, estado, bairro
    FROM cliente CL
    INNER JOIN endereco
        ON CL.endereco = endereco.cep;

CREATE OR REPLACE VIEW endereco_filial AS
    SELECT nome, cnpj, contatos, qtd_profissionais AS quantidadeprofissinais,
    cep, cidade, estado, bairro
    FROM filial FL
    INNER JOIN endereco
        ON FL.endereco = endereco.cep;

CREATE OR REPLACE VIEW pedido AS
    SELECT id_os AS solicitacao, os.id_cliente AS cliente, os.id_prof AS barbeiro, id_tiposervico AS servico,
    agendamento, svc.qt_servico * svc.preco AS subtotal
    FROM ordemservico OS
    INNER JOIN cliente CL
        ON os.id_cliente = cl.id_cliente
    INNER JOIN profissional PRO
        ON os.id_prof = PRO.id_prof
    INNER JOIN tiposervico SVC
        ON os.id_tiposervico = SVC.id_servico;

CREATE OR REPLACE VIEW profissional_por_filial AS
    SELECT pro.nome AS nomeprofissional, pro.email, pro.telefone AS contatoprofissional,
    fl.nome AS nomefilia, fl.telefone AS contatofilial
    FROM profissinal PRO
    INNER JOIN filial FL
        ON pro.id_filial = fl.id_filial;

CREATE OR REPLACE VIEW serivco_por_cliente AS
    SELECT cl.nome AS nomecliente, cl.telefone AS contatocliente, cl.email AS emailcliente, svc.id_servico AS codigoservico,
    svc.nome AS nomeservico, duracao, preco, qt_servico AS quantidadesolicitada
    FROM cliente CL
    INNER JOIN tiposervico SVC
        ON cl.id_cliente = svc.id_cliente;


