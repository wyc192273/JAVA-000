package test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;

/**
 * Created by yuchen.wu on 2020-10-18
 */

public class TestArrayLoader {

    public static void test() {
        System.out.println("hi");
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        TestArrayLoader[] a1 = new TestArrayLoader[10];
        TestArrayLoader[] a2 = new TestArrayLoader[10];
        System.out.println(a1.getClass() == a2.getClass());
        MyClassLoader myClassLoader = new MyClassLoader();
        Class clazz = myClassLoader.findClass("test.TestArrayLoader");
//        TestArrayLoader[] a3 = (TestArrayLoader[]) Array.newInstance(clazz, 10);  会报异常
        //返回false ， 由此可以知道，数组会关联一个引导类加载器
        System.out.println(Array.newInstance(clazz, 10).getClass() == a1.getClass());
    }

}

class MyClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class log = null;
        // 获取该class文件字节码数组
        byte[] classData = getData();

        if (classData != null) {
            // 将class的字节码数组转换成Class类的实例
            log = defineClass(name, classData, 0, classData.length);
        }
        return log;
    }

    private byte[] getData() {

        File file = new File("/Users/kuaikan/work/JAVA-000/Week_01/test/TestArrayLoader.class");
        if (file.exists()){
            FileInputStream in = null;
            ByteArrayOutputStream out = null;
            try {
                in = new FileInputStream(file);
                out = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = in.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
            return out.toByteArray();
        }else{
            return null;
        }


    }


}
