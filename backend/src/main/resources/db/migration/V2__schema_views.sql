CREATE OR REPLACE VIEW endereco_cliente AS
    SELECT nome, email, telefone, cep, cidade, estado, bairro
    FROM cliente CL
    INNER JOIN endereco
        ON CL.endereco = endereco.cep;

CREATE OR REPLACE VIEW endereco_filial AS
    SELECT nome, cnpj, telefone, qtd_profissionais AS quantidadeprofissinais,
    cep, cidade, estado, bairro
    FROM filial FL
    INNER JOIN endereco
        ON FL.endereco = endereco.cep;


CREATE OR REPLACE VIEW profissional_por_filial AS
    SELECT pro.nome AS nomeprofissional, pro.email, pro.telefone AS contatoprofissional,
    fl.nome AS nomefilia, fl.telefone AS contatofilial
    FROM profissional PRO
    INNER JOIN filial FL
        ON pro.id_filial = fl.id_filial;

CREATE OR REPLACE VIEW serivco_por_cliente AS
    SELECT cl.nome AS nomecliente, cl.telefone AS contatocliente, cl.email AS emailcliente, svc.id_servico AS codigoservico,
    svc.nome AS nomeservico, duracao, preco, qt_servico AS quantidadesolicitada
    FROM cliente CL
    INNER JOIN tiposervico SVC
        ON cl.id_cliente = svc.id_cliente;