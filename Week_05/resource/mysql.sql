use test;

##########################################################

CREATE TABLE school (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '学校名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学校表';

CREATE TABLE klass (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '班级名称',
  `school_id` bigint NOT NULL DEFAULT 0 COMMENT '学校id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级表';

CREATE TABLE student (
   `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
   `name` varchar(20) NOT NULL DEFAULT '' COMMENT '学生名称',
   `age` int NOT NULL DEFAULT 0 COMMENT '年龄',
   `klass_id` bigint NOT NULL DEFAULT 0 COMMENT '班级id',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

###########################################################

INSERT INTO school (`id`, `name`) VALUE (1, "geek_school");
INSERT INTO klass (`id`, `name`, `school_id`) VALUES (1, "klass_1", 1), (2, "klass_2", 1);
INSERT INTO student (`id`, `name`, `age`, `klass_id`) VALUES (1, "wyc", 28, 1), (2, "maod", 30, 1);