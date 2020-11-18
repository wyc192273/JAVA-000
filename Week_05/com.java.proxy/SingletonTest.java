import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;

/**
 * Created by yuchen.wu on 2020-11-17
 * 1.饿汉式，静态块方式 ：
 *      如果这个类一直不被使用，会导致内存的浪费
 * 2.懒汉式:
 *      能够在需要时创建对象，但是会有多线程问题。多个线程可能获取不同的对象。可以简单添加Synchronized防止多线程, 但是性能又不是很好
 * 3.双重校验锁:
 *      双重校验锁可以做到，单例和线程安全，但是由于指令重排序问题导致。我们new Singleton()一行代码会分解成
 *      memory = allocate()  1.分配对象内存空间
 *      ctorInstance(memory) 2.初始化对象
 *      instance = memory    3.设置instance指向刚分配的地址
 *      上面3个步骤，2和3可能重排序， 导致A线程执行过程中，A 1,3 切换线程到B 执行判断发现不为null，直接访问instance引用的对象，这个时候该对象还未初始化从而出现问题
 *      在jdk1.5之后加了volatile指令从而解决了该问题
 * 4.静态内部类：
 *      即是线程安全的，又是懒汉模式。推荐
 * 5.枚举:
 *      线程安全，自动支持序列化
 *
 * 除了枚举方式：其它方式都可能通过私有的构造器生成多个实例, 可以通过在创建第二个实例的时候抛异常来间接解决
 */

public class SingletonTest {

    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException,
            ExecutionException, InterruptedException {
        Assert.assertEquals(Singleton1.getInstance(), Singleton1.getInstance());
        changeInstance(Singleton1.class);
        Assert.assertEquals(Singleton2.getInstance(), Singleton2.getInstance());
        changeInstance(Singleton2.class);
//       由于并发问题会导致抛异常
//        testThreadSafe(Singleton3.class);
        Assert.assertEquals(Singleton3.getInstance(), Singleton3.getInstance());
//       由于构造器添加异常处理导致抛异常
//        changeInstance(Singleton3.class);
        testThreadSafe(Singleton4.class);
        Assert.assertEquals(Singleton4.getInstance(), Singleton4.getInstance());
        changeInstance(Singleton4.class);
        Assert.assertEquals(Singleton5.getInstance(), Singleton5.getInstance());
        changeInstance(Singleton5.class);
        Assert.assertEquals(Singleton7.getInstance(), Singleton7.getInstance());
        changeInstance(Singleton7.class);
        Assert.assertEquals(Singleton8.INSTANCE.getInstance(), Singleton8.INSTANCE.getInstance());
    }

    /**
     *  通过反射机制可以获取到private的构造器，从而可以创建多个对象
     */
    private static void changeInstance(Class<?> clazz)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object singleton1 = constructor.newInstance();
        Object singleton2 = constructor.newInstance();
        Assert.assertNotEquals(singleton1, singleton2);
    }

    private static void testThreadSafe(Class<?> clazz)
            throws NoSuchMethodException, ExecutionException,
            InterruptedException {
        Method method = clazz.getMethod("getInstance");
        CompletableFuture<Object> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                return method.invoke(clazz);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
        CompletableFuture<Object> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                return method.invoke(clazz);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
        Object object1 = future1.get();
        Object object2 = future2.get();
        Assert.assertEquals(object1, object2);
    }

    private interface Singleton{
    }

    /**
     * 饿汉式
     */
    private static class Singleton1 implements Singleton{

        private static Singleton1 singleton = new Singleton1();

        private Singleton1() {
        }

        public static Singleton1 getInstance() {
            return singleton;
        }
    }

    /**
     * 静态块
     */
    private static class Singleton2 implements Singleton{

        private static Singleton2 singleton;

        static {
            singleton = new Singleton2();
        }

        private Singleton2(){}

        public static Singleton2 getInstance() {
            return singleton;
        }
    }

    /**
     * 懒汉式
     */
    private static class Singleton3 implements Singleton{
        private static Singleton3 instance;
        private Singleton3() {
            if (instance != null) {
                throw new RuntimeException("second invoke construct");
            }
        }

        public static Singleton3 getInstance() throws InterruptedException {

            if (null == instance) {
                Thread.sleep(100);
                instance = new Singleton3();
            }
            return instance;
        }
    }

    /**
     * 懒汉式 : 线程安全版本
     */
    private static class Singleton4 implements Singleton{
        private static Singleton4 instance;
        private Singleton4() { }

        public static synchronized Singleton4 getInstance() throws InterruptedException {
            if (null == instance) {
                Thread.sleep(100);
                instance = new Singleton4();
            }
            return instance;
        }
    }

    /**
     * 双重校验锁
     */
    private static class Singleton5 implements Singleton{
        private static Singleton5 instance;
        private Singleton5() { }

        public static Singleton5 getInstance() throws InterruptedException {
            if (null == instance) {
                synchronized (Singleton5.class) {
                    if (null == instance) {
                        instance = new Singleton5();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * 双重校验锁： volatile
     */
    private static class Singleton6 implements Singleton{
        private static volatile Singleton6 instance;
        private Singleton6() { }

        public static Singleton6 getInstance() throws InterruptedException {
            if (null == instance) {
                synchronized (Singleton6.class) {
                    if (null == instance) {
                        instance = new Singleton6();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * 静态内部类方式
     */
    private static class Singleton7 implements Singleton{
        private Singleton7() { }
        private static class SingletonHolder {
            public static Singleton7 instance = new Singleton7();
        }
        public static  Singleton7 getInstance() throws InterruptedException {
            return SingletonHolder.instance;
        }
    }

    /**
     * 枚举
     */
    private enum Singleton8{
        INSTANCE;
        private Resource instance;

        private Singleton8() {
            this.instance = new Resource();
        }

        public Resource getInstance() {
            return instance;
        }
    }

    private static class Resource{

    }
}
