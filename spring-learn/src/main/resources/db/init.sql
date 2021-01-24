create database demo
CHARACTER   SET   'utf8'
COLLATE   'utf8_general_ci';

use demo;

CREATE TABLE IF NOT EXISTS `tt_user`(
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `user_name` VARCHAR(10) NOT NULL comment '用户姓名',
   `sex` int(1) not null default 1 comment '1男 0女',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '用户表';

insert into tt_user (user_name, sex) values ('张三', 0);
