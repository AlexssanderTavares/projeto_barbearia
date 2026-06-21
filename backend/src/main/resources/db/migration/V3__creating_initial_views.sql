CREATE OR REPLACE VIEW Cliente_View AS
    SELECT id_cliente, nome, email, contato.telefone FROM cliente
        INNER JOIN contato_cliente AS contato
            ON Cliente.id_cliente = contato.client;

CREATE OR REPLACE VIEW Filial_View AS
    SELECT id_filial, nome, email, cnpj, qtd_profissionais, contato.telefone FROM Filial
        INNER JOIN contato_filial AS contato
            ON filial.id_filial = contato.filial;

CREATE OR REPLACE VIEW Profissional_View AS
    SELECT id_prof, id_filial, nome, email, contato.telefone FROM profissional
        INNER JOIN contato_profissional AS contato
            ON profissional.id_prof = contato.profissional;

CREATE OR REPLACE VIEW servico_View AS
       SELECT id_servico, nome, duracao, preco, descricao FROM tiposervico;