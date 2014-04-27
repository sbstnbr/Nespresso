# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table global_order (
  id                        bigint auto_increment not null,
  start                     datetime,
  end                       datetime,
  status                    integer,
  constraint pk_global_order primary key (id))
;

create table order_product (
  id                        bigint auto_increment not null,
  quantity                  integer,
  product_id                bigint,
  user_order_id             bigint,
  constraint pk_order_product primary key (id))
;

create table product (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  price                     float,
  intensity                 integer,
  constraint pk_product primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  is_admin                  tinyint(1) default 0,
  constraint pk_user primary key (id))
;

create table user_order (
  id                        bigint auto_increment not null,
  date                      datetime,
  paid                      tinyint(1) default 0,
  user_id                   bigint,
  global_order_id           bigint,
  constraint pk_user_order primary key (id))
;

alter table order_product add constraint fk_order_product_product_1 foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_order_product_product_1 on order_product (product_id);
alter table order_product add constraint fk_order_product_userOrder_2 foreign key (user_order_id) references user_order (id) on delete restrict on update restrict;
create index ix_order_product_userOrder_2 on order_product (user_order_id);
alter table user_order add constraint fk_user_order_user_3 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_user_order_user_3 on user_order (user_id);
alter table user_order add constraint fk_user_order_globalOrder_4 foreign key (global_order_id) references global_order (id) on delete restrict on update restrict;
create index ix_user_order_globalOrder_4 on user_order (global_order_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table global_order;

drop table order_product;

drop table product;

drop table user;

drop table user_order;

SET FOREIGN_KEY_CHECKS=1;

