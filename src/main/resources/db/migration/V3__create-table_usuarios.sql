create table usuarios(

    id serial4 not null,
    login varchar(100) not null,
    senha varchar(255) not null,
    
	constraint pk_usuarios PRIMARY KEY(id)

);