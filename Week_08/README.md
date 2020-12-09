学习笔记

必做1：分库分表：

[老的单库单表数据sql](sql_tools/old_sql.sql)

[新的sql，将订单相关提出来，放到新的库中，并将订单表拆表](sql_tools/old_sql.sql)

拆分规则：
* 将 user和订单商品相关的拆分成两个库 user_test 和 order_test
* 将 order 订单 和 order_related 订单关联表 拆分8个表，以order_no订单号拆分
* 将 user 用户表 和 user_address 用户地址表 拆分成 8个表，以user_id拆分

[拆表后简单的增删改查操作](sql_tools/src/main/java/com/wyc/sql/tool/dao/TestDao.java)

必做2：加班太忙。。没搞完