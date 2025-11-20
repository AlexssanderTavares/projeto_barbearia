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
    id_prof UUID REFERENCES profissional(id_prof),
    agendamento TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS associacao_os_servico(
    id_os INTEGER PRIMARY KEY REFERENCES ordemservico(id_os),
    id_tiposervico INTEGER REFERENCES tiposervico(id_servico)
);