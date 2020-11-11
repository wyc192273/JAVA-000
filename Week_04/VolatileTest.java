/**
 * Created by yuchen.wu on 2020-11-10
 */

public class VolatileTest {

    int a;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        MyThread1 myThread1 = new MyThread1(volatileTest);
        MyThread2 myThread2 = new MyThread2(volatileTest);
        myThread1.start();

        myThread2.start();

        Thread.sleep(1000);
    }

    public static class MyThread1 extends Thread{
        VolatileTest volatileTest;

        public MyThread1(VolatileTest volatileTest) {
            this.volatileTest = volatileTest;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; ) {
                i++;
            }
            if (volatileTest.a == 0) {
                System.out.println("in");
            }
        }
    }

    public static class MyThread2 extends Thread{
        VolatileTest volatileTest;

        public MyThread2(VolatileTest volatileTest) {
            this.volatileTest = volatileTest;
        }

        @Override
        public void run() {
            volatileTest.a = 10;
        }
    }
}
