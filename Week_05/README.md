### 学习笔记

##### spring01 选做1

[简单的代码写死的静态代理方式](com.java.proxy/CodeStaticProxyTest.java)

[使用一个方法增强器实现的静态代理方式](com.java.proxy/StaticProxyTest.java)

静态代理的问题：
* 需要事先知道要代理的类的方法，并且需要将被代理类作为内部属性。
* 如果事先不知道真实的类的实现已经要代理的方法，则无法处理

使用动态代理的方式解决上述痛点：

动态代理有三种实现方式：
* JDKProxy
* cglib
* javassist

[JDK动态代理实现](com.java.proxy/JdkProxyTest.java)

JDKProxy : 代理对象基于接口实现， 所以无法同时调用两种方法, 并且无法代理没有接口的方法以及实现类自己的方法

[Cglib动态代理实现](com.java.proxy/CglibDynamicProxyTest.java)

cglib : 是通过动态使用默认构造器方式创建 被代理类的子类， 在调用 父类的方法实现， 如果被代理类没有默认构造器，将会导致无法初始化子类从而报错

[Javassist动态代理实现](com.java.proxy/JavassistProxyTest.java)

##### spring01 必做题2

写代码实现Spring Bean的装配，方式越多越好(XML、Annotation都可以),提 交到Github。

[代码](com.java.proxy/SpringBeanInjectTest.java)

##### spring01 选做题3

[代码](com.java.proxy/com/test2/Main.java)


##### spring02 选做题1 

[代码](com.java.proxy/SingletonTest.java)

##### spring02 选做题2

spring profile配置：
[代码](com.java.proxy/ProfileTest.java)

##### spring02 必做题3

[代码](https://github.com/wyc192273/JAVA-000/tree/main/school-springboot-test)

##### spring02 必做题6

[代码1，包括批量](com.java.proxy/com/test3/DataSourceDemo.java)
这里自己实现了一个线程池，但是发现有多线程问题，导致线程池关闭不掉，还未定位到问题

[代码3](com.java.proxy/com/test3/HikariTest.java)
