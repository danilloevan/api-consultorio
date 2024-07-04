create table medicos(

    id serial4 not null,
    nome varchar(100) not null,
    email varchar(100) not null unique,
	telefone varchar(20) not null,
    crm varchar(6) not null unique,
    especialidade varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
	numero varchar(20),
    complemento varchar(100),
    cidade varchar(100) not null,
    uf char(2) not null,
	excluido BOOLEAN not null,
    

	constraint pk_medicos PRIMARY KEY(id)

);