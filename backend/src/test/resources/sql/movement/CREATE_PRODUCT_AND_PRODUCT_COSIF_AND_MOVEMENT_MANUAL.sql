INSERT INTO public.PRODUTO(COD_PRODUTO, DES_PRODUTO)
VALUES('2DD510DA-CFA9-47BD-9C50-FAB727E69C91'::uuid, 'Teste Delete Product');
INSERT INTO produto_cosif (cod_produto, cod_cosif, cod_classificacao)
VALUES('2DD510DA-CFA9-47BD-9C50-FAB727E69C91'::uuid, '7E938588-FFB9-49D9-BFFE-770E44F7ADF9'::uuid, 'C6EF17');
INSERT INTO movimento_manual (dat_mes, dat_ano, num_lancamento, cod_produto, cod_cosif, des_descricao, dat_movimento, cod_usuario, val_valor) 
VALUES(1, 2022, 1, '2DD510DA-CFA9-47BD-9C50-FAB727E69C91'::uuid, '7E938588-FFB9-49D9-BFFE-770E44F7ADF9'::uuid, 'Test Launch', '2022-06-30 15:01:19.268', 'CL512DO0H0', 1200.00);
