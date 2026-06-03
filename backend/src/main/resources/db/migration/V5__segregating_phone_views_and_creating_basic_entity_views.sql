ALTER VIEW Cliente_View RENAME TO Cliente_Phone_View;
ALTER VIEW Filial_View RENAME TO Filial_Phone_View;
ALTER VIEW Profissional_View RENAME TO Profissional_Phone_View;

CREATE OR REPLACE VIEW Cliente_View AS
    SELECT id_cliente, nome, email FROM Cliente;

CREATE OR REPLACE VIEW Filial_View AS
    SELECT id_filial, nome, email, cnpj, qtd_profissionais FROM Filial;

CREATE OR REPLACE VIEW Profissional_View AS
    SELECT id_prof, id_filial, nome, email FROM Profissional;

