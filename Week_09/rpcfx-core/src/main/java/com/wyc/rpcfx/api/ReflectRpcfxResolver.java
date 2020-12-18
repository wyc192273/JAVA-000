package com.wyc.rpcfx.api;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yuchen.wu on 2020-12-14
 */

public class ReflectRpcfxResolver implements RpcfxResolver {

    public static final Map<Class<?>, Object> SERVICE_MAPPING = new ConcurrentHashMap<>();

    @Override
    public <T> T resolve(Class<T> serviceClass, String basePackageScan) throws RpcfxException {
        if (SERVICE_MAPPING.containsKey(serviceClass)) {
            return (T) SERVICE_MAPPING.get(serviceClass);
        }
        try {
            List<Class<T>> classes = getAllClassByInterface(serviceClass, basePackageScan);
            return classes.get(0).newInstance();
        } catch (Exception e) {
            throw new RpcfxException(e);
        }
    }

    public static <T> List<Class<T>> getAllClassByInterface(Class<T> clazz, String basePageName) {
        List<Class<T>> list = new ArrayList<>();
        if (clazz.isInterface()) {
            try {
                List<Class> allClass = getAllClass(basePageName);
                /**
                 * 循环判断路径下的所有类是否实现了指定的接口 并且排除接口类自己
                 */
                for (int i = 0; i < allClass.size(); i++) {
                    /**
                     * 判断是不是同一个接口
                     */
                    // isAssignableFrom:判定此 Class 对象所表示的类或接口与指定的 Class
                    // 参数所表示的类或接口是否相同，或是否是其超类或超接口
                    if (clazz.isAssignableFrom(allClass.get(i))) {
                        if (!clazz.equals(allClass.get(i))) {
                            // 自身并不加进去
                            list.add(allClass.get(i));
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("出现异常"+e.getMessage());
            }
        }
        return list;
    }

    private static List<Class> getAllClass(String packageName) {
        List<String> classNameList =  getClassName(packageName);
        List<Class> list = new ArrayList<>();

        for(String className : classNameList){
            try {
                list.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("load class from name failed:"+className+e.getMessage());
            }
        }
        return list;
    }

    public static List<String> getClassName(String packageName) {
        List<String> fileNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", File.separator);
        URL url = loader.getResource(packagePath);
        if (url != null) {
            String type = url.getProtocol();
            if (type.equals("file")) {
                String fileSearchPath = url.getPath();
                fileNames = getClassNameByFile(fileSearchPath);
            }else{
                throw new RuntimeException("file system not support! cannot load！");
            }
        }
        return fileNames;
    }

    private static List<String> getClassNameByFile(String filePath) {
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                myClassName.addAll(getClassNameByFile(childFile.getPath()));
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    childFilePath = childFilePath.substring(childFilePath.indexOf("classes") + 8, childFilePath.lastIndexOf("."));
                    childFilePath = childFilePath.replace(File.separator, ".");
                    myClassName.add(childFilePath);
                }
            }
        }
        return myClassName;
    }

}
