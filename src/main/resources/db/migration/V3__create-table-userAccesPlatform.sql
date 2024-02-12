CREATE TYPE user_role AS ENUM ('admin', 'user');


CREATE SEQUENCE seq_userAccessTable_id START WITH 1 INCREMENT BY 1;


CREATE TABLE tb_user_acess_platform
(
    id       BIGINT DEFAULT nextval('seq_userAccessTable_id') PRIMARY KEY,
    login    VARCHAR,
    password VARCHAR,
    role user_role
);
