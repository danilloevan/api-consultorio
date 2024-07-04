create table pacientes(
    id serial4 not null,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(14) not null unique,
    telefone varchar(20) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
	numero varchar(20),
    complemento varchar(100),
    cidade varchar(100) not null,
    uf char(2) not null,
    excluido boolean not null,

    CONSTRAINT pk_paciente PRIMARY KEY(id)
);