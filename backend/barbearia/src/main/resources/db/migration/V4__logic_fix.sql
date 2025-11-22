DROP VIEW endereco_filial;
DROP VIEW endereco_cliente;
DROP VIEW profissional_por_filial;
DROP VIEW serivco_por_cliente;

ALTER TABLE tiposervico ALTER COLUMN preco TYPE NUMERIC(19,2);