CREATE TABLE IF NOT EXISTS contato_cliente(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    client UUID REFERENCES cliente(id_cliente),
    telefone TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS contato_filial(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    filial UUID REFERENCES filial(id_filial),
    telefone TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS contato_profissional(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    profissional UUID REFERENCES profissional(id_prof),
    telefone TEXT NOT NULL
);