drop table if exists users cascade;
create table users (
id bigserial,
phone VARCHAR(30) not null UNIQUE,
password VARCHAR(80) not null,
email VARCHAR(50) UNIQUE,
first_name VARCHAR(50),
last_name VARCHAR(50),
user_block boolean,
PRIMARY KEY (id)
);

drop table if exists roles cascade;
create table roles (id serial, name VARCHAR(50) not null, primary key (id));

drop table if exists users_roles cascade;
create table users_roles (
user_id INT NOT NULL,
role_id INT NOT NULL,
primary key (user_id, role_id),
FOREIGN KEY (user_id)
REFERENCES users (id),
FOREIGN KEY (role_id)
REFERENCES roles (id)
);

insert into roles (name)
values ('ROLE_CUSTOMER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

insert into users (phone, password, first_name, last_name, email, user_block)
values ('8-910-123-45-67','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','admin','admin','admin@gmail.com', false);

insert into users_roles (user_id, role_id)
values (1, 1), (1, 2), (1, 3);
