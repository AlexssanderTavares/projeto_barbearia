CREATE TABLE IF NOT EXISTS filial(
    id_filial UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome TEXT NOT NULL,
    pass VARCHAR(8) NOT NULL,
    email TEXT UNIQUE NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    qtd_profissionais INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS profissional(
    id_prof UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_filial UUID REFERENCES filial(id_filial),
    nome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente(
    id_cliente UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    pass VARCHAR(8) NOT NULL
);

CREATE TABLE IF NOT EXISTS tiposervico(
    id_servico BIGSERIAL PRIMARY KEY NOT NULL,
    nome TEXT NOT NULL,
    duracao INTEGER DEFAULT 0,
    preco NUMERIC(19,2) NOT NULL,
    descricao TEXT DEFAULT ''
);

CREATE TABLE IF NOT EXISTS ordemservico(
    id_os BIGSERIAL PRIMARY KEY NOT NULL,
    id_cliente UUID REFERENCES cliente(id_cliente),
    id_prof UUID REFERENCES profissional(id_prof),
    id_servico BIGINT REFERENCES tiposervico(id_servico),
    agendamento TIMESTAMP NOT NULL,
    status VARCHAR(30) NOT NULL,
    final_duration INT NOT NULL DEFAULT 0,
    subtotal NUMERIC(38,2) NOT NULL DEFAULT 0
);