学习笔记

读写分离-动态切换数据源版本1.0:
在项目muliti-datasource-test.
使用方式： 
* 先执行下resources的init.sql初始化数据库
* 修改jdbc.properties的username和password为本地数据库的用户名和密码
* 配置application.yaml的routerStrategy,调整多个分库的路由策略，暂时只支持两种方式 polling 轮询 random 随机
* 启动项目
* 调用暂时提供的两种方式，验证下是否主从隔离 
* 查询： http://localhost:8080/test/get_name_by_id?id=1
* 更新： http://localhost:8080/test/update_name_by_id?id=1&name=test1
* 通过ReadOnly注解来 从而判断是使用从库还是主库

两个从库故意设置成相同的id，name不同从而看出是否分配不同的从库
如果更新主库后，在调用查询发现name没变化，说明已经可以切换


读写分离-数据库框架版本2.0
在项目ss-jdbc-datasource-test.
使用方式： 
* 先执行下resources的init.sql初始化数据库
* 修改jdbc.properties的username和password为本地数据库的用户名和密码
* 启动项目
* 调用暂时提供的两种方式，验证下是否主从隔离 
* 查询： http://localhost:8080/test/get_name_by_id?id=1
* 更新： http://localhost:8080/test/update_name_by_id?id=1&name=test1
* 更新后查询： http://localhost:8080/test/test_update_query?id=1&name=test5