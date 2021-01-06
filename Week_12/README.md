学习笔记

### redis主从复制
#### docker部署步骤
* [主redis配置](redis6379.conf)
* [从redis1配置](redis6380.conf)
* [从redis2配置](redis6381.conf)
* 创建docker 主实例：docker run -p 6379:6379 --name redis01 -v redis.conf:/etc/redis/redis.conf -v /etc/redis/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes
* 创建docker 从实例1：docker run -p 6380:6380 --name redis02 -v redis.conf:/etc/redis/redis.conf -v /etc/redis/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes
* 创建docker 从实例2：docker run -p 6381:6381 --name redis03 -v redis.conf:/etc/redis/redis.conf -v /etc/redis/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes

* 这里要注意，conf的 bind需要设置为 bind 0.0.0.0 ::1 ,否则没法容器之间无法互通
* docker exec -it redis01 /bin/bash 和 docker exec -it redis02 /bin/bash 分别启动主从docker
* 在从docker上执行 slaveof 当前主机ip 6379 便可以设置好主从模式
* 还可以直接在conf文件中设置好master的ip和port