CREATE DATABASE exchange_test;

USE exchange_test;

###################################

CREATE TABLE `account` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `rmb` int NOT NULL DEFAULT 0 COMMENT 'rmb',
  `dollar` int NOT NULL DEFAULT 0 COMMENT 'dollar',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号表';

####################################


insert into account (id, rmb, dollar) value (1, 100, 100);
insert into account (id, rmb, dollar) value (2, 100, 100);