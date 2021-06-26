CREATE TABLE lancamentos(
id bigserial PRIMARY key not NULL,
descricao varchar(50) not NULL,
data_vencimento date NOT NULL,
data_pagamento date NOT NULL,
valor numeric(10,2) not NULL,
observacao varchar(100),
tipo varchar(20) NOT NULL,
id_categoria bigserial not null,
id_pessoa bigserial not null,
FOREIGN KEY (id_categoria) REFERENCES categorias(id),
FOREIGN KEY (id_pessoa) REFERENCES pessoas(id)
)