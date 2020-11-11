import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03 {
    
    public static void main(String[] args) throws ExecutionException, InterruptedException, BrokenBarrierException {
        solution1();
        solution2();
        solution3();
        solution4();
        solution5();
        solution6();
        solution7();
        solution8();
        solution9();
        solution10();
        solution11();
        solution12();
    }

    /**
     * FutureTask
     */
    private static void solution1() throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        FutureTask<Integer> futureTask = new FutureTask<>(Homework03::sum);
        new Thread(futureTask).start();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+futureTask.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    /**
     * CompletableFuture 可以使用默认线程池和指定线程池
     */
    private static void solution2() throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Homework03::sum);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Homework03::sum, executorService);
        int result = future.get();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        executorService.shutdown();
    }

    /**
     * join等待线程执行完
     */
    private static void solution3() throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        Thread thread = new Thread(() -> result.set(sum()));
        thread.start();
        thread.join();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    /**
     * 信号量实现
     */
    private static void solution4() throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        Semaphore semaphore = new Semaphore(0);
        AtomicInteger result = new AtomicInteger();
        Thread thread = new Thread(() -> {
            result.set(sum());
            semaphore.release(1);
        });
        thread.start();
        semaphore.acquire(1);
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    /**
     * CountDownLatch实现
     */
    private static void solution5() throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicInteger result = new AtomicInteger();
        Thread thread = new Thread(() -> {
            result.set(sum());
            countDownLatch.countDown();
        });
        thread.start();
        countDownLatch.await();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    /**
     * CyclicBarrier实现
     */
    private static void solution6() throws ExecutionException, InterruptedException, BrokenBarrierException {
        long start=System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread thread = new Thread(() -> {
            try {
                result.set(sum());
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        cyclicBarrier.await();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    /**
     * wait notify 实现
     */
    private static void solution7() throws ExecutionException, InterruptedException, BrokenBarrierException {
        long start=System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        Thread thread = new Thread(() -> {
            synchronized (Thread.class) {
                result.set(sum());
                Thread.class.notifyAll();
            }
        });
        thread.start();
        synchronized (Thread.class) {
            Thread.class.wait();
        }
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    /**
     * condition await signal 实现
     */
    private static void solution8() throws ExecutionException, InterruptedException, BrokenBarrierException {
        long start=System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        MyThread8 myThread8 = new MyThread8(result, lock, condition);
        myThread8.start();
        lock.lock();
        condition.await();
        lock.unlock();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    static class MyThread8 extends Thread{
        AtomicInteger result;
        ReentrantLock lock;
        Condition condition;

        public MyThread8(AtomicInteger result, ReentrantLock lock, Condition condition) {
            this.result = result;
            this.lock = lock;
            this.condition = condition;
        }

        @Override
        public void run() {
            lock.lock();
            result.set(sum());
            condition.signalAll();
            lock.unlock();
        }
    }


    /**
     * LockSupport park/unpark 实现
     */
    private static void solution9() throws ExecutionException, InterruptedException, BrokenBarrierException {
        long start=System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        MyThread9 myThread9 = new MyThread9(Thread.currentThread(), result);
        myThread9.start();
        LockSupport.park();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    static class MyThread9 extends Thread{
        Thread thread;
        AtomicInteger result;

        public MyThread9(Thread thread, AtomicInteger result) {
            this.thread = thread;
            this.result = result;
        }

        @Override
        public void run() {
            result.set(sum());
            LockSupport.unpark(thread);
        }
    }

    /**
     * SynchronousQueue 实现
     */
    private static void solution10() throws ExecutionException, InterruptedException, BrokenBarrierException {
        long start=System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        Thread thread = new Thread(() -> {
            result.set(sum());
            try {
                synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        synchronousQueue.put(result);
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    /**
     * LinkedBlockingQueue 实现
     */
    private static void solution11() throws ExecutionException, InterruptedException, BrokenBarrierException {
        long start=System.currentTimeMillis();
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
        Thread thread = new Thread(() -> {
            try {
                blockingQueue.put(sum());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        System.out.println("异步计算结果为："+blockingQueue.take());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    /**
     * 自旋 实现
     */
    private static void solution12() throws ExecutionException, InterruptedException, BrokenBarrierException {
        long start=System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        Thread thread = new Thread(() -> result.set(sum()));
        thread.start();
        while (result.get() == 0) {
            Thread.yield();
        }
        System.out.println("异步计算结果为："+result.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
    
    private static int sum() {
        return fiboReformat(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    private static int fiboReformat(int a) {
        if (a == 0) {
            return 0;
        }
        int a1 = 1;
        int a2 = 1;
        for (int i = 2; i <= a; i++) {
            int tempa2 = a2;
            a2 = a2 + a1;
            a1 = tempa2;
        }
        return a2;
    }
}
