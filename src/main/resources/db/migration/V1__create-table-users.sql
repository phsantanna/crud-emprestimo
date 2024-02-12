CREATE TYPE situacao AS ENUM ('APROVADO', 'NEGADO');

CREATE SEQUENCE seq_user_id START WITH 1 INCREMENT BY 1;

CREATE TABLE tb_users
(
    id       BIGINT DEFAULT nextval('seq_user_id') PRIMARY KEY,
    nome     VARCHAR(255),
    cpf      VARCHAR(255),
    score    int,
    situacao_user situacao

);