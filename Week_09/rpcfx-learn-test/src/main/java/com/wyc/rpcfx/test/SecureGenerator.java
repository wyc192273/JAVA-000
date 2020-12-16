package com.wyc.rpcfx.test;

import java.io.IOException;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * Created by yuchen.wu on 2020-12-13
 */

public class SecureGenerator {

    private static AccountGeneratorClassLoader classLoader =
            new AccountGeneratorClassLoader();

    private static Class secureClass;

    public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException {
        SecureGenerator secureGenerator = new SecureGenerator();
        App app = secureGenerator.generateSecure();
        app.say();
    }

    public App generateSecure() throws ClassFormatError, InstantiationException, IllegalAccessException, IOException {
        if (null == secureClass) {
            ClassReader cr = new ClassReader("com/wyc/rpcfx/test/App");
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter classAdapter = new AddSecurityClassAdapter(cw);
            cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
            byte[] data = cw.toByteArray();
            secureClass = classLoader.defineClassFromClassFile(
                    "com.wyc.rpcfx.test.App$EnhancedByASM",data);
        }
        return (App) secureClass.newInstance();
    }

    private static class AccountGeneratorClassLoader extends ClassLoader {


        public Class defineClassFromClassFile(String className,
                                              byte[] classFile) throws ClassFormatError {
            return defineClass(className, classFile, 0, classFile.length);
        }
    }
}
