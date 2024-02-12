CREATE SEQUENCE seq_emprestimo_id START WITH 1 INCREMENT BY 1;


CREATE TABLE tb_emprestimo
(
    id              BIGINT DEFAULT nextval('seq_emprestimo_id') PRIMARY KEY,
    total           FLOAT,
    dataSolicitacao DATE,
    id_user         BIGINT,
    FOREIGN KEY (id_user) REFERENCES tb_users (id)
);