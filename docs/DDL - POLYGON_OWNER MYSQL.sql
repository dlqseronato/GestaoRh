DROP TABLE IF EXISTS POLYGON_OWNER.TELEFONE;
DROP TABLE IF EXISTS POLYGON_OWNER.COLABORADOR;
DROP TABLE IF EXISTS POLYGON_OWNER.CARGO;
DROP TABLE IF EXISTS POLYGON_OWNER.CONTA_BANCARIA;
DROP TABLE IF EXISTS POLYGON_OWNER.ENDERECO;
DROP VIEW POLYGON_OWNER.DADOS_COLABORADORES;

CREATE TABLE
POLYGON_OWNER.CARGO 
(
ID				NUMERIC(20)  NOT NULL,
NOME            VARCHAR(150) NOT NULL,
DESCRICAO       VARCHAR(300),
NIVEL           NUMERIC(20) NOT NULL,
VALOR_BASE_HORA NUMERIC(20,2) NOT NULL,
CONSTRAINT PK_CARGO_01 PRIMARY KEY(ID)
)
;

CREATE TABLE
POLYGON_OWNER.CONTA_BANCARIA
(
ID        NUMERIC(20) NOT NULL,
BANCO     NUMERIC(20) NOT NULL,
AGENCIA   NUMERIC(20) NOT NULL,
CONTA     NUMERIC(20) NOT NULL,
TIPO      NUMERIC(20) NOT NULL,
CONSTRAINT PK_CONTA_BANCARIA_01 PRIMARY KEY (ID),
CONSTRAINT C_COLABORADOR_01 UNIQUE (BANCO,AGENCIA,CONTA)

);

CREATE TABLE
POLYGON_OWNER.ENDERECO
(
ID			NUMERIC(20)	  NOT NULL,
RUA         VARCHAR(150) NOT NULL,
NUMERO      NUMERIC(20)    NOT NULL,
CEP         NUMERIC(20)    NOT NULL,
ESTADO      VARCHAR(50)   ,
PAIS        VARCHAR(50)   ,
CONSTRAINT PK_ENDERECO_01 PRIMARY KEY (ID)
);


CREATE TABLE
POLYGON_OWNER.COLABORADOR
(
ID             NUMERIC(20) NOT NULL,
NOME           VARCHAR(150) NOT NULL,
CPF            NUMERIC (11) NOT NULL,
DT_NASCIMENTO  DATE,
GENERO         VARCHAR(1),
EMAIL          VARCHAR(150),
CTPS_NUM       NUMERIC(11) NOT NULL,
PIS_PASEP      NUMERIC(11) NOT NULL,
ID_CONTA_BANCARIA  NUMERIC(20) NOT NULL,
ID_ENDERECO    NUMERIC(20) ,
ID_CARGO       NUMERIC(20) NOT NULL,
CONSTRAINT PK_COLABORADOR_01 PRIMARY KEY (ID),
 CONSTRAINT C_COLABORADOR_01 UNIQUE (CPF),
 CONSTRAINT C_COLABORADOR_02 UNIQUE (ID_CONTA_BANCARIA),
 CONSTRAINT FK_COLABORADOR_01 FOREIGN KEY (ID_ENDERECO) REFERENCES ENDERECO (ID),
 CONSTRAINT FK_COLABORADOR_02 FOREIGN KEY (ID_CONTA_BANCARIA) REFERENCES CONTA_BANCARIA (ID),
 CONSTRAINT FK_COLABORADOR_03 FOREIGN KEY (ID_CARGO) REFERENCES CARGO (ID)
);

CREATE TABLE
POLYGON_OWNER.TELEFONE 
(
ID			NUMERIC(20)  NOT NULL,
ID_COLABORADOR NUMERIC(20)  NOT NULL,
PREFIXO     NUMERIC(20)  NOT NULL,
NUMERO      NUMERIC(20)  NOT NULL,
TIPO        NUMERIC(20)  NOT NULL,
CONSTRAINT PK_TELEFONE_01 PRIMARY KEY (ID,ID_COLABORADOR),
CONSTRAINT FK_TELEFONE_01 FOREIGN KEY (ID_COLABORADOR) REFERENCES COLABORADOR (ID)
);

CREATE VIEW POLYGON_OWNER.DADOS_COLABORADORES 
AS
SELECT  CO.ID, CO.NOME, CO.CPF, CO.DT_NASCIMENTO, CO.GENERO, CO.EMAIL, CO.CTPS_NUM, CO.PIS_PASEP,
        CB.ID ID_CONTA, CB.BANCO, CB.AGENCIA, CB.CONTA,CB.TIPO,
        TE.ID ID_TELEFONE, TE.ID_COLABORADOR, TE.PREFIXO, TE.NUMERO, TE.TIPO TIPO_TELEFONE,
        E.ID ID_ENDERECO, E.RUA, E.NUMERO NUMERO_RUA, E.CEP, E.ESTADO, E.PAIS,
        CA.ID ID_CARGO, CA.NOME NOME_CARGO, CA.DESCRICAO, CA.NIVEL, CA.VALOR_BASE_HORA
FROM 
POLYGON_OWNER.COLABORADOR CO JOIN POLYGON_OWNER.CARGO CA
ON CO.ID_CARGO = CA.ID
JOIN POLYGON_OWNER.TELEFONE TE
ON CO.ID = TE.ID_COLABORADOR
JOIN POLYGON_OWNER.CONTA_BANCARIA CB
ON CO.ID_CONTA_BANCARIA = CB.ID
JOIN POLYGON_OWNER.ENDERECO E
ON CO.ID_ENDERECO = E.ID;
