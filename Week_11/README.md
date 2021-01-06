学习笔记

需要配置本地redis：端口号：6379

#### 简单实用string相关命令：

[spring-boot和jedis集成](spring-jedis-test/src/test/java/com/wyc/redis/JedisTest.java)

[spring-boot和lettuce集成](spring-lettuce-test/src/test/java/com/wyc/redis/RedisTest.java)

[spring-boot和redisson集成](spring-redisson-test/src/test/java/com/wyc/redisson/RedisTest.java)

#### [jedis简单实现分布式锁：](spring-jedis-test/src/main/java/com/wyc/redis/controller/TestController.java)

* 锁命令：http://localhost:8081/lock

#### [jedis简单实现分布式锁：](spring-lettuce-test/src/main/java/com/wyc/redis/controller/TestController.java)

* 锁命令：http://localhost:8081/lock

#### [redisson简单实现全局分布式锁：](spring-redisson-test/src/main/java/com/wyc/redisson/controller/TestController.java)

分别启动三种集成方式的项目：

* 有http请求的三个方法：
* 锁命令，带超时时间，阻塞：http://localhost:8081/lock
* 尝试锁，尝试在超时时间内，阻塞： http://localhost:8081/try_lock
* 释放锁：http://localhost:8081/release_lock

#### [jedis简单实现减库存](spring-jedis-test/src/main/java/com/wyc/redis/controller/TestController.java)

* 初始化库存： http://localhost:8081/init_inventory
* 扣减库存： http://localhost:8081/cost_inventory?count=10

#### [redisson简单实现减库存：](spring-redisson-test/src/main/java/com/wyc/redisson/controller/TestController.java)

* 初始化库存： http://localhost:8081/init_inventory
* 扣减库存： http://localhost:8081/cost_inventory?count=10

#### jedis简单实现pub/sub
* [pub: http://localhost:8081/pub?message=test](spring-jedis-test/src/main/java/com/wyc/redis/controller/TestController.java)
* [sub: 看日志输出](spring-jedis-test/src/main/java/com/wyc/redis/listener/OrderListener.java)