ALTER TABLE associacao_os_servico ADD COLUMN id_assoc SERIAL NOT NULL;
ALTER TABLE associacao_os_servico DROP CONSTRAINT associacao_os_servico_pkey;
ALTER TABLE associacao_os_servico ADD PRIMARY KEY (id_assoc);
