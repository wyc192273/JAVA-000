# 周四作业
[第1题](work1.md)

[第4题](work4.md)
#周六作业
[第一题](work01.md)

[第二题](Work2.java)
# 学习笔记
### JVM核心技术（三）：调优分析与面试经验
1.直接运行 java -XX:+PrintGCDetails GCLogAnalysis
-XX:+PrintGCDetails ： 打印GC的详细信息
由于使用的是jdk8版本，默认使用的是并行GC
![avatar](img/JVM01.jpg)
日志解析：
 * [GC 和 [Full GC  代表GC的停顿类型
 * Allocation Failure 说明分配空间失败导致
 * Ergonomics 这种一般出现在关注吞吐量的收集器中，在某个generation被过度使用之前，GC ergonomics就会启动一次GC
 * [PSYoungGen 、[ParOldGen 、[Metaspace 代表GC发生的区域。这些区域名称与使用的GC收集器相关
 * DefNew代表Serial收集器，ParNew代表Parallel New Generation，PSYoungGen代表用的是Parallel Scavenge，ParOldGen使用的是Parallel Old，Tenured使用的是Serial Old
 * 方框中的65518K->10751K(76288K) 含义是GC前该内存区域已使用容量->GC后该内存区域已使用容量(该内存区域总容量)
 * 方括号之外 65518K->20429K(251392K) 表示GC前Java堆的已使用容量->GC后Java堆的已使用容量(Java堆总容量)
 * [Times: user=0.02 sys=0.04, real=0.01 secs] GC事件的持续时间，按不同类别进行度量，
 * user 在此收集期间，垃圾收集器线程所消耗的总CPU时间 ，sys 花费在操作系统调用或等待系统事件上的时间， real 应用程序被停止的时钟时间。对于并行GC，这个数字应该接近于(用户时间+系统时间)除以垃圾收集器使用的线程数。注意，由于某些活动不能并行，它总是会超过一定数量的比率。

分析第一行收集日志
 * [GC (Allocation Failure) 代表新生代内存分配失败
 * [PSYoungGen: 65518K->10751K(76288K)] 发生了一次youngGC，内存空间从 65518K清理到 10751K，当前新生代总容量是76288K
 * 65518K->20429K(251392K) java堆的清理情况，这里有一个细节可以注意到，刚开始 新生代和java堆的已使用容量是相同的，说明刚开始我们的老年代是没有内存占用的
 * 同时还注意到，新生代清理完之后内存使用量是10727K，但是java堆清理完之后使用量是24418K，这说明我们有13691K进入了老年代，这个可以从第二行也验证这一点
 * 第二行的 [PSYoungGen: 75913K->10751K(141824K)] 89603K->50960K(316928K) 年轻代使用内存是 75913K，而Java堆使用内存是89603K，89603 - 75913 = 13691，正好是当前老年代使用的内存容量
 *  [Times: user=0.01 sys=0.05, real=0.01 secs] 由于我当前使用的系统核心线程数是4，用的并行收集器，并行收集器默认线程数分配是，如果大于8个则取5/8,如果小于8个，则直接使用核心线程数。而时间real = (user + sys) / 4 大概约等于0.01

 
 