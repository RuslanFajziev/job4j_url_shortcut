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