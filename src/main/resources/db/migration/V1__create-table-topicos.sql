create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(200) not null unique,
    fechaCreacion varchar(50) not null,
    status varchar(50) not null,
    autor varchar(50) not null,
    curso varchar(50) not null,

    primary key(id)
)