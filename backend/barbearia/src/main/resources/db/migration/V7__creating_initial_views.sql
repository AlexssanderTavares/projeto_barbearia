ALTER TABLE profissional DROP COLUMN name;
ALTER TABLE tiposervico DROP COLUMN id_cliente;
ALTER TABLE tiposervico DROP COLUMN qt_servico;
ALTER TABLE tiposervico ADD COLUMN descricao TEXT NOT NULL;
ALTER TABLE ordemservico ADD COLUMN status VARCHAR(30) NOT NULL;
ALTER TABLE associacao_os_servico ADD COLUMN qt_solicitado INT NOT NULL;
ALTER TABLE associacao_os_servico ADD COLUMN vlr_unit NUMERIC(38,2) NOT NULL;
ALTER TABLE associacao_os_servico ADD COLUMN total_solicitado NUMERIC(38,2) NOT NULL;

CREATE OR REPLACE VIEW Cliente_View AS
    SELECT id_cliente, nome, email, endereco, telefone FROM cliente
        INNER JOIN contato_cliente AS contato
            ON Cliente.id_cliente = contato.client;

CREATE OR REPLACE VIEW Filial_View AS
    SELECT id_filial, nome, email, cnpj, qtd_profissionais, endereco, telefone FROM Filial
        INNER JOIN contato_filial AS contato
            ON filial.id_filial = contato.filial;

CREATE OR REPLACE VIEW Profissional_View AS
    SELECT id_prof, id_filial, nome, email, telefone FROM profissional
        INNER JOIN contato_profissional AS contato
            ON profissional.id_prof = contato.profissional;

CREATE OR REPLACE VIEW servico_View AS
       SELECT id_servico, nome, duracao, preco, descricao FROM tiposervico;

CREATE OR REPLACE VIEW os_view AS
       SELECT id_os, agendamento AS data, status, CL.nome AS cliente, PRO.nome AS profissional
       FROM ordemservico AS OS
       INNER JOIN cliente AS CL
            ON OS.id_cliente = CL.id_cliente
       INNER JOIN profissional AS PRO
            ON OS.id_prof = PRO.id_prof;

CREATE OR REPLACE VIEW servico_solicitado AS
       SELECT id_assoc, agendamento, nome, duracao, preco AS valor_unitario, qt_solicitado,
              preco * qt_solicitado AS subtotal, duracao * qt_solicitado AS duracao_final
       FROM associacao_os_servico AS AOS
       INNER JOIN ordemservico AS OS
            ON AOS.id_os = OS.id_os
       INNER JOIN tiposervico AS TS
            ON AOS.id_tiposervico = TS.id_servico;