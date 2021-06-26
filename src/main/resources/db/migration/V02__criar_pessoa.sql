CREATE TABLE pessoas(
id bigserial NOT NULL PRIMARY KEY,
nome varchar(50) not NULL,
ativo boolean NOT NULL,
logradouro varchar(255),
numero varchar(10),
complemento varchar(150),
bairro varchar(150) ,
cep varchar(20) ,
cidade varchar(100) , 
estado varchar(50) 
)