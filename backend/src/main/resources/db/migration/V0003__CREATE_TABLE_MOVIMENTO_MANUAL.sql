CREATE TABLE IF NOT EXISTS MOVIMENTO_MANUAL(
    DAT_MES SMALLINT NOT NULL,
    DAT_ANO SMALLINT NOT NULL,
    NUM_LANCAMENTO NUMERIC(18,0) NOT NULL,
    COD_PRODUTO UUID NOT NULL,
    COD_COSIF UUID NOT NULL,
    DES_DESCRICAO VARCHAR(50) NOT NULL CHECK (DES_DESCRICAO <> ''),
    DAT_MOVIMENTO TIMESTAMP WITH TIME ZONE NOT NULL,
    COD_USUARIO VARCHAR(15) NOT NULL CHECK (COD_USUARIO <> ''),
    VAL_VALOR NUMERIC(18,2) NOT NULL,
    CREATED_AT TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_MOVIMENTO_MANUAL PRIMARY KEY(DAT_MES, DAT_ANO, NUM_LANCAMENTO, COD_PRODUTO, COD_COSIF),
    CONSTRAINT FK_PRODUTO_COD_PRODUTO FOREIGN KEY(COD_PRODUTO) REFERENCES PRODUTO(COD_PRODUTO),
    CONSTRAINT FK_PRODUTO_COSIF_COD_COSIF FOREIGN KEY(COD_PRODUTO, COD_COSIF) REFERENCES PRODUTO_COSIF(COD_PRODUTO, COD_COSIF)
);

CREATE INDEX MOVIMENTO_MANUAL_DES_DESCRICAO_IDX ON PUBLIC.MOVIMENTO_MANUAL USING BTREE(DES_DESCRICAO);