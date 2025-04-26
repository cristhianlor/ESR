insert into tb_cozinha (id, cozinha) values (1, 'Brasileira');
insert into tb_cozinha (id, cozinha) values (2, 'Chilena');
insert into tb_cozinha (id, cozinha) values (3,'Argentina');
insert into tb_cozinha (id, cozinha) values (4, 'Uruguaia');

insert into tb_estado (id, nm_estado) values (1, 'São Paulo');
insert into tb_estado (id, nm_estado) values (2, 'Rio de Janeiro');
insert into tb_estado (id, nm_estado) values (3, 'Minas Gerais');
insert into tb_estado (id, nm_estado) values (4, 'Espírito Santo');
insert into tb_estado (id, nm_estado) values (5, 'Paraná');
insert into tb_estado (id, nm_estado) values (6, 'Santa Catarina');
insert into tb_estado (id, nm_estado) values (7, 'Rio Grande do Sul');

insert into tb_cidade (id, nm_cidade, estado_id) values (1, 'São Paulo', 1);
insert into tb_cidade (id, nm_cidade, estado_id) values (2, 'São Roque', 1);
insert into tb_cidade (id, nm_cidade, estado_id) values (3, 'Saquarema', 2);
insert into tb_cidade (id, nm_cidade, estado_id) values (4, 'Búzios', 2);
insert into tb_cidade (id, nm_cidade, estado_id) values (5, 'Betim', 3);
insert into tb_cidade (id, nm_cidade, estado_id) values (6, 'Belo Horizonte', 3);
insert into tb_cidade (id, nm_cidade, estado_id) values (7, 'Salvador', 4);
insert into tb_cidade (id, nm_cidade, estado_id) values (8, 'Vitória', 4);

insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, end_cep, end_logr, end_num, end_comp, end_bairro) values (1,'Romario', 10.5, 1, current_date, current_date, 1, '01001000', 'Rua 1', '100', '1º andar', 'Centro');
insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id, data_cadastro, data_atualizacao) values (2,'Ronaldinho', 12.5, 1, current_date, current_date);
insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id, data_cadastro, data_atualizacao) values (3,'Zamorano', 9.5, 2, current_date, current_date);
insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id, data_cadastro, data_atualizacao) values (4,'Salas', 8.5, 2, current_date, current_date);
insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id, data_cadastro, data_atualizacao) values (5,'Messi', 7.5, 3, current_date, current_date);
insert into tb_restaurante (id, nm_restaurante, tx_frete, cozinha_id, data_cadastro, data_atualizacao) values (6,'Aguirre', 6.5, 4, current_date, current_date);

insert into tb_fm_pagto (id, desc_fm_pagto) values (1, 'Débito');
insert into tb_fm_pagto (id, desc_fm_pagto) values (2, 'Crédito');
insert into tb_fm_pagto (id, desc_fm_pagto) values (3, 'PIX');

insert into tb_rest_fm_pagto (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (2,3), (3,2);

insert into tb_grupo (id, nm_grupo) values (1, 'Administrador');
insert into tb_grupo (id, nm_grupo) values (2, 'Gerente');
insert into tb_grupo (id, nm_grupo) values (3, 'Entregador');
insert into tb_grupo (id, nm_grupo) values (4, 'Cliente');
