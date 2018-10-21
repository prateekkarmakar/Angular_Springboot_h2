create table books

(
   id integer not null AUTO_INCREMENT,
   name varchar(255) not null,
   author varchar(255) not null,
   publisher varchar(255) not null,
   price double not null,
   isbn varchar(255) not null,
   primary key(id)
);