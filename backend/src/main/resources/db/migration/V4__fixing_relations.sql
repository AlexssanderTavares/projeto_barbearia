CREATE OR REPLACE VIEW os_view AS
SELECT id_os, agendamento AS data, os.status, CL.id_cliente AS cod_cliente, CL.nome AS nome_cliente,
       CL.email AS email_cliente, PRO.nome AS profissional, SVC.nome AS servicos, SVC.descricao AS descricao,
       SVC.duracao AS tempo_duracao, SVC.preco AS preco_servico
FROM ordemservico AS OS
         INNER JOIN cliente AS CL
                    ON OS.id_cliente = CL.id_cliente
         INNER JOIN profissional AS PRO
                    ON OS.id_prof = PRO.id_prof
         INNER JOIN tiposervico AS SVC
                    ON OS.id_servico = SVC.id_servico;

CREATE OR REPLACE VIEW Agendamento AS
SELECT id_os, agendamento AS data, os.status, CL.id_cliente AS cod_cliente, CL.nome AS cliente,
       CL.email AS email_cliente, PRO.nome AS profissional, final_duration, subtotal
FROM ordemservico AS OS
         INNER JOIN cliente AS CL
                    ON OS.id_cliente = CL.id_cliente
         INNER JOIN profissional AS PRO
                    ON OS.id_prof = PRO.id_prof;
