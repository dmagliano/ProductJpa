INSERT INTO usuario
    (id, data_criacao, email, nome, senha, username)
VALUES (DEFAULT, '2022-11-02 18:04:14.60000', 'luiz@gmail.com', 'Luiz Paulo', '123456', 'lpaulo'),
       (DEFAULT, NOW(), 'dmagliano@gmail.com', 'Diogo Magliano', '123456', 'dmagliano'),
       (DEFAULT, NOW(), 'zezinho@gmail.com', 'Jose Silva', '123456', 'zezinho');

INSERT INTO produto
    (id, codigo_barras, descricao, nome, preco, vendedor_id)
VALUES (DEFAULT, 789654321, 'Lapis de cor com 12 cores aquarelavel', 'Lapis de cor 12 cores',
        '39.99', 1),
       (DEFAULT, 789321456, 'Papel A4 resma com 500 fls', 'Resma A4 500fls', '25.20', 1),
       (DEFAULT, 789654321, 'Caneta esferografica 0.7 azul', 'Caneta 0.7 Azul', '9.99', 2);

INSERT INTO avaliacao
    (id, avaliacao, data_criacao, produto_id, usuario_id)
VALUES (DEFAULT, 'Muito bom, macio de escrever recomendo!',NOW(),1,2),
       (DEFAULT, 'Achei mais fino o traço do que 0.7 boa',NOW(),1,3),
       (DEFAULT, 'Papel um pouco fino, veio amassado!',NOW(),2,2),
       (DEFAULT, 'Papel, branco bom para jato de tinta',NOW(),2,3);

INSERT INTO ANUNCIO_PRODUTO
(id_anuncio, data_comeco, data_fim, produto_id)
VALUES (DEFAULT, '2018-01-07T16:11:26.485', '2018-01-27T16:11:26.485' ,1);


SELECT * FROM ANUNCIO_PRODUTO;

SELECT PRODUTO_ID FROM ANUNCIO_PRODUTO WHERE DATA_COMECO <= '2018-02-01 16:11:26.485' and DATA_FIM >= '2018-01-26 16:11:26.485';

SELECT * FROM PRODUTO;

SELECT * FROM PRODUTO WHERE ID NOT IN (SELECT PRODUTO_ID FROM ANUNCIO_PRODUTO WHERE DATA_COMECO <= '2018-02-01 16:11:26.485' and DATA_FIM >= '2018-01-26 16:11:26.485');