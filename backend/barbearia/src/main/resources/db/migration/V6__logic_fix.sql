ALTER TABLE cliente DROP CONSTRAINT cliente_endereco_fkey;
ALTER TABLE filial DROP CONSTRAINT filial_endereco_fkey;
ALTER TABLE cliente ALTER COLUMN endereco TYPE VARCHAR(8);
ALTER TABLE cliente ALTER COLUMN endereco SET NOT NULL;
ALTER TABLE cliente ADD CONSTRAINT uq_cliente_endereco UNIQUE (endereco);
ALTER TABLE filial ALTER COLUMN endereco TYPE VARCHAR(8);
ALTER TABLE filial ALTER COLUMN endereco SET NOT NULL;
ALTER TABLE filial ADD CONSTRAINT uq_filial_endereco UNIQUE (endereco);
DROP TABLE endereco;