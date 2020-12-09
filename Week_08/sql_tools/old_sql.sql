CREATE TABLE `user` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
    `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
    `age` int NOT NULL DEFAULT 0 COMMENT '年龄',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `user_address` (
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
    `user_id` bigint unsigned NOT NULL DEFAULT 0 COMMENT '用户id',
    `country` varchar(50) NOT NULL DEFAULT 0 COMMENT '国家',
    `province` varchar(50) NOT NULL DEFAULT 0 COMMENT '省份/州',
    `city` varchar(50) NOT NULL DEFAULT 0 COMMENT '地区',
    `detail` varchar(255) NOT NULL DEFAULT 0 COMMENT '详细地址',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户地址表';

CREATE TABLE `commodity` (
     `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
     `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
     `sub_name` varchar(255) NOT NULL DEFAULT '' COMMENT '二级名称',
     `memo` varchar(255) NOT NULL DEFAULT '' COMMENT '商品备注',
     `price` int unsigned NOT NULL DEFAULT 0 COMMENT '价格，`分` 为单位',
     `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

CREATE TABLE `order` (
     `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
     `order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '订单号',
     `price` int unsigned NOT NULL DEFAULT 0 COMMENT '订单金额',
     `memo` varchar(255) NOT NULL DEFAULT '' COMMENT '订单备注',
     `user_id` bigint unsigned NOT NULL default 0 COMMENT '用户id',
     `user_address_id` bigint unsigned NOT NULL default 0 COMMENT '用户地址id',
     `status` tinyint not null default 0 COMMENT '订单状态',
     `commodity_snapshot` json not null COMMENT '商品快照',
     `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`id`),
     UNIQUE KEY `uk_order_no` (`order_no`),
     KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';


CREATE TABLE `order_related` (
     `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
     `order_no` varchar(50) NOT NULL DEFAULT '' COMMENT '订单号',
     `price` int unsigned NOT NULL DEFAULT 0 COMMENT '商品金额',
     `count` int unsigned NOT NULL DEFAULT 0 COMMENT '商品数量',
     `commodity_id` int not null COMMENT '商品id',
     `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`id`),
     KEY `idx_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品关联表';