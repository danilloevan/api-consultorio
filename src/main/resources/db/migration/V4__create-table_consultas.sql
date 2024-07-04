create table consultas(

    id serial4 not null,
    id_medico bigint not null,
    id_paciente bigint not null,
    data_hora TIMESTAMP not null,
	cancelada boolean not null,
	motivo_cancelamento varchar(100),

    primary key(id),
    constraint fk_consultas_id_medico foreign key(id_medico) references medicos(id),
    constraint fk_consultas_id_paciente foreign key(id_paciente) references pacientes(id)

);