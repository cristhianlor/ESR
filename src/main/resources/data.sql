insert into tb_cozinha (id, cozinha) values (null, 'Brasileira');
insert into tb_cozinha (id, cozinha) values (null, 'Chilena');
insert into tb_cozinha (id, cozinha) values (null,'Argentina');
insert into tb_cozinha (id, cozinha) values (null, 'Uruguaia');

insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id) values (null,'Romário', 10.5, 1);
insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id) values (null,'Ronaldinho', 12.5, 1);
insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id) values (null,'Zamorano', 9.5, 2);
insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id) values (null,'Salas', 8.5, 2);
insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id) values (null,'Messi', 7.5, 3);
insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id) values (null,'Aguirre', 6.5, 4);

insert into tb_fm_pagto (id, desc_fm_pagto) values (null, 'Débito');
insert into tb_fm_pagto (id, desc_fm_pagto) values (null, 'Crédito');
insert into tb_fm_pagto (id, desc_fm_pagto) values (null, 'PIX');

insert into tb_estado (id, nm_estado) values (null, 'São Paulo');
insert into tb_estado (id, nm_estado) values (null, 'Rio de Janeiro');
insert into tb_estado (id, nm_estado) values (null, 'Minas Gerais');
insert into tb_estado (id, nm_estado) values (null, 'Bahia');

insert into tb_cidade (id, nm_cidade, estado_id) values (null, 'São Paulo', 1);
insert into tb_cidade (id, nm_cidade, estado_id) values (null, 'São Roque', 1);
insert into tb_cidade (id, nm_cidade, estado_id) values (null, 'Saquarema', 2);
insert into tb_cidade (id, nm_cidade, estado_id) values (null, 'Búzios', 2);
insert into tb_cidade (id, nm_cidade, estado_id) values (null, 'Betim', 3);
insert into tb_cidade (id, nm_cidade, estado_id) values (null, 'Belo Horizonte', 3);
insert into tb_cidade (id, nm_cidade, estado_id) values (null, 'Salvador', 4);
insert into tb_cidade (id, nm_cidade, estado_id) values (null, 'Vitória', 4);

insert into tb_rest_fm_pagto (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (2,2), (3,1);
