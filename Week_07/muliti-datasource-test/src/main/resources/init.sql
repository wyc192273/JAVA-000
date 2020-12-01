create database test_master;
use test_master;

##########################################################

CREATE TABLE test_t (
  `id` bigint COMMENT 'id',
  `name` varchar(20) COMMENT '名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试表';

insert into test_t values (1, '1'), (2, '2');

###########################################################

create database test_slave;
use test_slave;

##########################################################

CREATE TABLE test_t (
  `id` bigint COMMENT 'id',
  `name` varchar(20) COMMENT '名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试表';

insert into test_t values (1, '1'), (2, '2');

###########################################################
###########################################################

create database test_slave2;
use test_slave2;

##########################################################

CREATE TABLE test_t (
                      `id` bigint COMMENT 'id',
                      `name` varchar(20) COMMENT '名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试表';

insert into test_t values (1, '3'), (2, '4');

###########################################################