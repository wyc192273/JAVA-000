create database user_test;
use user_test;

CREATE TABLE `user_0`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name`       varchar(50)     NOT NULL DEFAULT '' COMMENT '名称',
  `age`        int             NOT NULL DEFAULT 0 COMMENT '年龄',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';
CREATE TABLE `user_1`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name`       varchar(50)     NOT NULL DEFAULT '' COMMENT '名称',
  `age`        int             NOT NULL DEFAULT 0 COMMENT '年龄',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';
CREATE TABLE `user_2`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name`       varchar(50)     NOT NULL DEFAULT '' COMMENT '名称',
  `age`        int             NOT NULL DEFAULT 0 COMMENT '年龄',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';
CREATE TABLE `user_3`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name`       varchar(50)     NOT NULL DEFAULT '' COMMENT '名称',
  `age`        int             NOT NULL DEFAULT 0 COMMENT '年龄',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';
CREATE TABLE `user_4`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name`       varchar(50)     NOT NULL DEFAULT '' COMMENT '名称',
  `age`        int             NOT NULL DEFAULT 0 COMMENT '年龄',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';
CREATE TABLE `user_5`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name`       varchar(50)     NOT NULL DEFAULT '' COMMENT '名称',
  `age`        int             NOT NULL DEFAULT 0 COMMENT '年龄',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';
CREATE TABLE `user_6`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name`       varchar(50)     NOT NULL DEFAULT '' COMMENT '名称',
  `age`        int             NOT NULL DEFAULT 0 COMMENT '年龄',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';
CREATE TABLE `user_7`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name`       varchar(50)     NOT NULL DEFAULT '' COMMENT '名称',
  `age`        int             NOT NULL DEFAULT 0 COMMENT '年龄',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

CREATE TABLE `user_address_0`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id`    bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户id',
  `country`    varchar(50)     NOT NULL DEFAULT 0 COMMENT '国家',
  `province`   varchar(50)     NOT NULL DEFAULT 0 COMMENT '省份/州',
  `city`       varchar(50)     NOT NULL DEFAULT 0 COMMENT '地区',
  `detail`     varchar(255)    NOT NULL DEFAULT 0 COMMENT '详细地址',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户地址表';
CREATE TABLE `user_address_1`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id`    bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户id',
  `country`    varchar(50)     NOT NULL DEFAULT 0 COMMENT '国家',
  `province`   varchar(50)     NOT NULL DEFAULT 0 COMMENT '省份/州',
  `city`       varchar(50)     NOT NULL DEFAULT 0 COMMENT '地区',
  `detail`     varchar(255)    NOT NULL DEFAULT 0 COMMENT '详细地址',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户地址表';
CREATE TABLE `user_address_2`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id`    bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户id',
  `country`    varchar(50)     NOT NULL DEFAULT 0 COMMENT '国家',
  `province`   varchar(50)     NOT NULL DEFAULT 0 COMMENT '省份/州',
  `city`       varchar(50)     NOT NULL DEFAULT 0 COMMENT '地区',
  `detail`     varchar(255)    NOT NULL DEFAULT 0 COMMENT '详细地址',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户地址表';
CREATE TABLE `user_address_3`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id`    bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户id',
  `country`    varchar(50)     NOT NULL DEFAULT 0 COMMENT '国家',
  `province`   varchar(50)     NOT NULL DEFAULT 0 COMMENT '省份/州',
  `city`       varchar(50)     NOT NULL DEFAULT 0 COMMENT '地区',
  `detail`     varchar(255)    NOT NULL DEFAULT 0 COMMENT '详细地址',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户地址表';
CREATE TABLE `user_address_4`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id`    bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户id',
  `country`    varchar(50)     NOT NULL DEFAULT 0 COMMENT '国家',
  `province`   varchar(50)     NOT NULL DEFAULT 0 COMMENT '省份/州',
  `city`       varchar(50)     NOT NULL DEFAULT 0 COMMENT '地区',
  `detail`     varchar(255)    NOT NULL DEFAULT 0 COMMENT '详细地址',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户地址表';
CREATE TABLE `user_address_5`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id`    bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户id',
  `country`    varchar(50)     NOT NULL DEFAULT 0 COMMENT '国家',
  `province`   varchar(50)     NOT NULL DEFAULT 0 COMMENT '省份/州',
  `city`       varchar(50)     NOT NULL DEFAULT 0 COMMENT '地区',
  `detail`     varchar(255)    NOT NULL DEFAULT 0 COMMENT '详细地址',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户地址表';
CREATE TABLE `user_address_6`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id`    bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户id',
  `country`    varchar(50)     NOT NULL DEFAULT 0 COMMENT '国家',
  `province`   varchar(50)     NOT NULL DEFAULT 0 COMMENT '省份/州',
  `city`       varchar(50)     NOT NULL DEFAULT 0 COMMENT '地区',
  `detail`     varchar(255)    NOT NULL DEFAULT 0 COMMENT '详细地址',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户地址表';
CREATE TABLE `user_address_7`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `user_id`    bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户id',
  `country`    varchar(50)     NOT NULL DEFAULT 0 COMMENT '国家',
  `province`   varchar(50)     NOT NULL DEFAULT 0 COMMENT '省份/州',
  `city`       varchar(50)     NOT NULL DEFAULT 0 COMMENT '地区',
  `detail`     varchar(255)    NOT NULL DEFAULT 0 COMMENT '详细地址',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户地址表';


#####################################################################
create database order_test;
use order_test;

CREATE TABLE `commodity`
(
  `id`         bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name`       varchar(255)    NOT NULL DEFAULT '' COMMENT '名称',
  `sub_name`   varchar(255)    NOT NULL DEFAULT '' COMMENT '二级名称',
  `memo`       varchar(255)    NOT NULL DEFAULT '' COMMENT '商品备注',
  `price`      int unsigned    NOT NULL DEFAULT 0 COMMENT '价格，`分` 为单位',
  `created_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品表';

CREATE TABLE `order_0`
(
  `id`                 bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`           varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`              int unsigned    NOT NULL DEFAULT 0 COMMENT '订单金额',
  `memo`               varchar(255)    NOT NULL DEFAULT '' COMMENT '订单备注',
  `user_id`            bigint unsigned NOT NULL default 0 COMMENT '用户id',
  `user_address_id`    bigint unsigned NOT NULL default 0 COMMENT '用户地址id',
  `status`             tinyint         not null default 0 COMMENT '订单状态',
  `commodity_snapshot` json            not null COMMENT '商品快照',
  `created_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';
CREATE TABLE `order_1`
(
  `id`                 bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`           varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`              int unsigned    NOT NULL DEFAULT 0 COMMENT '订单金额',
  `memo`               varchar(255)    NOT NULL DEFAULT '' COMMENT '订单备注',
  `user_id`            bigint unsigned NOT NULL default 0 COMMENT '用户id',
  `user_address_id`    bigint unsigned NOT NULL default 0 COMMENT '用户地址id',
  `status`             tinyint         not null default 0 COMMENT '订单状态',
  `commodity_snapshot` json            not null COMMENT '商品快照',
  `created_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';
CREATE TABLE `order_2`
(
  `id`                 bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`           varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`              int unsigned    NOT NULL DEFAULT 0 COMMENT '订单金额',
  `memo`               varchar(255)    NOT NULL DEFAULT '' COMMENT '订单备注',
  `user_id`            bigint unsigned NOT NULL default 0 COMMENT '用户id',
  `user_address_id`    bigint unsigned NOT NULL default 0 COMMENT '用户地址id',
  `status`             tinyint         not null default 0 COMMENT '订单状态',
  `commodity_snapshot` json            not null COMMENT '商品快照',
  `created_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';
CREATE TABLE `order_3`
(
  `id`                 bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`           varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`              int unsigned    NOT NULL DEFAULT 0 COMMENT '订单金额',
  `memo`               varchar(255)    NOT NULL DEFAULT '' COMMENT '订单备注',
  `user_id`            bigint unsigned NOT NULL default 0 COMMENT '用户id',
  `user_address_id`    bigint unsigned NOT NULL default 0 COMMENT '用户地址id',
  `status`             tinyint         not null default 0 COMMENT '订单状态',
  `commodity_snapshot` json            not null COMMENT '商品快照',
  `created_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';
CREATE TABLE `order_4`
(
  `id`                 bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`           varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`              int unsigned    NOT NULL DEFAULT 0 COMMENT '订单金额',
  `memo`               varchar(255)    NOT NULL DEFAULT '' COMMENT '订单备注',
  `user_id`            bigint unsigned NOT NULL default 0 COMMENT '用户id',
  `user_address_id`    bigint unsigned NOT NULL default 0 COMMENT '用户地址id',
  `status`             tinyint         not null default 0 COMMENT '订单状态',
  `commodity_snapshot` json            not null COMMENT '商品快照',
  `created_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';
CREATE TABLE `order_5`
(
  `id`                 bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`           varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`              int unsigned    NOT NULL DEFAULT 0 COMMENT '订单金额',
  `memo`               varchar(255)    NOT NULL DEFAULT '' COMMENT '订单备注',
  `user_id`            bigint unsigned NOT NULL default 0 COMMENT '用户id',
  `user_address_id`    bigint unsigned NOT NULL default 0 COMMENT '用户地址id',
  `status`             tinyint         not null default 0 COMMENT '订单状态',
  `commodity_snapshot` json            not null COMMENT '商品快照',
  `created_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';
CREATE TABLE `order_6`
(
  `id`                 bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`           varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`              int unsigned    NOT NULL DEFAULT 0 COMMENT '订单金额',
  `memo`               varchar(255)    NOT NULL DEFAULT '' COMMENT '订单备注',
  `user_id`            bigint unsigned NOT NULL default 0 COMMENT '用户id',
  `user_address_id`    bigint unsigned NOT NULL default 0 COMMENT '用户地址id',
  `status`             tinyint         not null default 0 COMMENT '订单状态',
  `commodity_snapshot` json            not null COMMENT '商品快照',
  `created_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';
CREATE TABLE `order_7`
(
  `id`                 bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`           varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`              int unsigned    NOT NULL DEFAULT 0 COMMENT '订单金额',
  `memo`               varchar(255)    NOT NULL DEFAULT '' COMMENT '订单备注',
  `user_id`            bigint unsigned NOT NULL default 0 COMMENT '用户id',
  `user_address_id`    bigint unsigned NOT NULL default 0 COMMENT '用户地址id',
  `status`             tinyint         not null default 0 COMMENT '订单状态',
  `commodity_snapshot` json            not null COMMENT '商品快照',
  `created_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`         timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';


CREATE TABLE `order_related_0`
(
  `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`     varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品金额',
  `count`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品数量',
  `commodity_id` int            not null COMMENT '商品id',
  `created_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单商品关联表';
CREATE TABLE `order_related_1`
(
  `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`     varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品金额',
  `count`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品数量',
  `commodity_id` int            not null COMMENT '商品id',
  `created_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单商品关联表';
CREATE TABLE `order_related_2`
(
  `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`     varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品金额',
  `count`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品数量',
  `commodity_id` int            not null COMMENT '商品id',
  `created_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单商品关联表';
CREATE TABLE `order_related_3`
(
  `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`     varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品金额',
  `count`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品数量',
  `commodity_id` int            not null COMMENT '商品id',
  `created_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单商品关联表';
CREATE TABLE `order_related_4`
(
  `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`     varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品金额',
  `count`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品数量',
  `commodity_id` int            not null COMMENT '商品id',
  `created_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单商品关联表';
CREATE TABLE `order_related_5`
(
  `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`     varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品金额',
  `count`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品数量',
  `commodity_id` int            not null COMMENT '商品id',
  `created_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单商品关联表';
CREATE TABLE `order_related_6`
(
  `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`     varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品金额',
  `count`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品数量',
  `commodity_id` int            not null COMMENT '商品id',
  `created_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单商品关联表';
CREATE TABLE `order_related_7`
(
  `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `order_no`     varchar(50)     NOT NULL DEFAULT '' COMMENT '订单号',
  `price`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品金额',
  `count`        int unsigned    NOT NULL DEFAULT 0 COMMENT '商品数量',
  `commodity_id` int            not null COMMENT '商品id',
  `created_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单商品关联表';