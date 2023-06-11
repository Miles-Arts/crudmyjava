create database os_systems;

\connect os_systems;

create table books (
  id bigint primary key generated always as identity,
  name character varying(255) not null,

  CONSTRAINT books_name_uniq UNIQUE (name)
);

insert into books (name) values ('The Little Prince'), ('Flow'), ('7 Habits');