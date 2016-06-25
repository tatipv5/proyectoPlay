# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table curso (
  id                            bigint auto_increment not null,
  identificador                 varchar(255),
  constraint pk_curso primary key (id)
);

create table docente (
  id                            bigint auto_increment not null,
  nombre                        varchar(255),
  materia                       varchar(255),
  cedula                        varchar(255),
  telefono                      varchar(255),
  constraint pk_docente primary key (id)
);

create table estudiant (
  id                            bigint auto_increment not null,
  nombre                        varchar(255),
  direccion                     varchar(255),
  cedula                        varchar(255),
  telefono                      varchar(255),
  requerida                     tinyint(1) default 0,
  constraint pk_estudiant primary key (id)
);

create table grupo (
  id                            bigint auto_increment not null,
  nombre                        varchar(255),
  identificador                 varchar(255),
  constraint pk_grupo primary key (id)
);


# --- !Downs

drop table if exists curso;

drop table if exists docente;

drop table if exists estudiant;

drop table if exists grupo;

