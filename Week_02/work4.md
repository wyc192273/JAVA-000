* 对于较小堆内存的情况下，SerialGC 性能反而是更好
* 对于与用户交互的web服务来说，使用CMS更好，防止某次请求的请求时间过长，从而给用户带来不好的体验
* 对于系统更关注系统吞吐量的服务，使用并行GC可以达到不错的效果
* G1回收器对于大部分场景表现都不错，尤其对于大内存的情况下，回收效果明显好于其他的垃圾回收器
* 我们要根据实际应用场景，通过搭配不同的参数配置来对系统进行调优。通过多次尝试才能知道哪种配置是更适合当期服务的，没有完美的配置。