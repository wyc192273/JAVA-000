package work2;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yuchen.wu on 2020-10-19
 */

public class Work2 {

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException,
            InvocationTargetException {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class clazz = helloClassLoader.findClass("Hello");
        Object obj = clazz.newInstance();
        Method method = obj.getClass().getMethod("hello");
        method.invoke(obj);
    }

    static class HelloClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] bytes = getData();
            if (bytes == null) {
                return super.findClass(name);
            } else {
                byte[] newBytes = new byte[bytes.length];
                for (int i = 0; i < bytes.length; i++) {
                    newBytes[i] = (byte) (255 - bytes[i]);
                }
                return defineClass(name, newBytes, 0, newBytes.length);
            }
        }

        private byte[] getData() {
            File file = new File("/Users/kuaikan/work/JAVA-000/Week_01/work2/Hello.xlass");
            try (InputStream inputStream = new FileInputStream(file)) {
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                return bytes;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
