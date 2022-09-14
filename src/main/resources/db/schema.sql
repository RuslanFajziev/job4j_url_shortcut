create table logins (
    id serial primary key not null,
    login varchar(2000),
    password varchar(2000),
    site varchar(2000)
);

create table urlredirect (
    id serial primary key not null,
    url_out varchar(2000),
    code varchar(2000),
    request_count bigint not null
);
--
-- create table role (
--                       id serial primary key not null,
--                       name varchar(2000)
-- );
--
-- create table person (
--                         id serial primary key not null,
--                         login varchar(2000),
--                         password varchar(2000),
--                         employee_id int references employee(id),
--                         role_id int references role(id)
-- );
--
-- insert into employee(name, surname, inn, employmentDate)
-- VALUES ('Дима', 'Красин', 12345678, now()),
--        ('Вася', 'Майснер', 87654321, now());
--
-- insert into role(name)
-- VALUES ('Admin'), ('User');
--
-- insert into person(login, password, employee_id, role_id)
-- VALUES ('parsentev', '123', (select distinct id from employee where inn = '12345678'), (select distinct id from role where id = 1)),
--        ('ban', '123', (select distinct id from employee where inn = '12345678'), (select distinct id from role where id = 2)),
--        ('ivan', '123', (select distinct id from employee where inn = '87654321'), (select distinct id from role where id = 1));
--
-- create table room (
--                       id serial primary key not null,
--                       name varchar(2000)
-- );
--
-- create table message(
--                         id serial primary key not null,
--                         tittle varchar(2000),
--                         bodyMessage varchar(2000),
--                         room_id int references room(id),
--                         person_id int references person(id)
-- );
--
-- insert into room(name)
-- VALUES ('For It communication'), ('For free communication');
--
-- insert into message(tittle, bodyMessage, room_id, person_id)
-- VALUES ('Как я сервер поднимал колен', 'Однажды поднимал с колен сервак бла бла бал...',
--         (select distinct id from room where id = 1), (select distinct id from person where login = 'parsentev')),
--        ('Как запоминать много инфы', 'Чтобы запомниать много инвыб нужно много спать и бла бла бал...',
--         (select distinct id from room where id = 2), (select distinct id from person where login = 'ivan'));
--
-- create table logins(
--                        id serial primary key not null,
--                        username varchar(2000),
--                        password varchar(2000)
-- );
--
-- insert into logins(username, password) VALUES ('admin', '$2a$10$bUDTdsOoEUQ3CiTdmYCZMeuCDi3voY31X.QLHR0PUSdyb9sdw87Q2');