package com.wyc.rpcfx.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * Created by yuchen.wu on 2020-12-13
 */

public class Generator {

    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader("com/wyc/rpcfx/test/App");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassAdapter classAdapter = new AddSecurityClassAdapter(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] data = cw.toByteArray();
        File file = new File(Generator.class.getResource("/").getPath() + "com/wyc/rpcfx/test/App$EnhancedByASM.class");
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(data);
        outputStream.close();
    }
}
