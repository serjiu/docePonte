# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  email                     varchar(255) not null,
  name                      varchar(30) not null,
  company                   varchar(30),
  phone_number              varchar(30) not null,
  street                    varchar(50),
  postal_code               varchar(8),
  city                      varchar(30),
  gps_lat_pos               varchar(255),
  password                  varchar(255) not null,
  active                    boolean not null,
  power_user                boolean not null,
  registry_date             timestamp,
  active_date               timestamp,
  last_status_date          timestamp,
  comments                  varchar(255),
  constraint pk_user primary key (email))
;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

