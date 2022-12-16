/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/12/22 15:39:58                          */
/*==============================================================*/

drop database if exists store;

create database store;

use store;


drop table if exists Customers;

drop table if exists Goods;

drop table if exists OrderLineItems;

drop table if exists Orders;

/*==============================================================*/
/* Table: Customers                                             */
/*==============================================================*/
create table Customers
(
   id                   varchar(20) not null,
   name                 varchar(50) not null,
   password             varchar(20) not null,
   address              varchar(100),
   phone                varchar(20),
   birthday             bigint,
   primary key (id)
);

alter table Customers comment '用户';

/*==============================================================*/
/* Table: Goods                                                 */
/*==============================================================*/
create table Goods
(
   id                   int not null auto_increment,
   name                 varchar(100) not null,
   price                float comment '单价',
   description          varchar(200) comment '产品描述',
   brand                varchar(30) comment '电脑品牌',
   cpu_brand            varchar(30) comment 'CPU品牌',
   cpu_type             varchar(30) comment 'CPU型号',
   memory_capacity      varchar(30) comment '内存容量',
   hd_capacity          varchar(30) comment '硬盘容量',
   card_model           varchar(30) comment '显卡型号',
   displaysize          varchar(30) comment '显示器尺寸',
   image                varchar(100) comment '电脑图片',
   primary key (id)
);

/*==============================================================*/
/* Table: OrderLineItems                                        */
/*==============================================================*/
create table OrderLineItems
(
   id                   int not null auto_increment,
   goodsid              int not null,
   orderid              varchar(20) not null,
   quantity             int,
   sub_total            float,
   primary key (id)
);

/*==============================================================*/
/* Table: Orders                                                */
/*==============================================================*/
create table Orders
(
   id                   varchar(20) not null,
   order_date           bigint default NULL,
   status               int default 1 comment '1 代表待确认 0 代表已确认',
   total                float,
   primary key (id)
);

alter table OrderLineItems add constraint FK_Relationship_3 foreign key (orderid)
      references Orders (id) on delete restrict on update restrict;

alter table OrderLineItems add constraint FK_Relationship_4 foreign key (goodsid)
      references Goods (id) on delete restrict on update restrict;

