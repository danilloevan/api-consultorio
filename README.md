Sobre a API:
O projeto consiste em uma API Rest para agendamentos de consultas com pacientes e médicos.
Parada realizar as resquições é necessário está logado, a autenticação foi realizada utilizando Spring Security e JWT.
Com o token de autenticação é possível realizar as requisições de CRUD para Pacientes e Médicos.
A regra de negócio acontece no agendamento de consultas onde é realizado diverças validações de desponibilidade de médicos e horários.
Referente a camada de persistencia foi utilizado Postgres com banco de dados. Todo o banco é criado ao rodar o projeto pela primeira vez
através das migrations e as operações são realizadas utilizando JPA e Hibernate.

O projeto foi desenvolvido utilizando principios SOLIDs, buscando uma melhor organização e manutenabilidade.

Stack do projeto:
  - API Rest com Spring Framework(MVC, Data, Security);
  - Persistencia com JPA e Hibernate;
  - Banco de dados Postgres;
  - Autorization JWT;

PS: O texto pode até está ruim, mas foi escrito por mim.
