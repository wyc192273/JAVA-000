package com.wyc.rpcfx.test;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by yuchen.wu on 2020-12-13
 */

public class AddSecurityClassAdapter extends ClassAdapter {

    private String enhancedSuperName;

    public AddSecurityClassAdapter(ClassVisitor cv) {
        super(cv);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor wrappedMv = mv;
        if (mv != null) {
            if (name.equals("say")) {
                wrappedMv = new AddSecurityClassMethodAdapter(mv);
            } else if (name.equals("<init>")) {
                wrappedMv = new ChangeToChildConstructorMethodAdapter(mv, enhancedSuperName);
            }
        }
        return wrappedMv;
    }

    @Override
    public void visit(final int version, final int access, final String name,
                      final String signature, final String superName,
                      final String[] interfaces) {
        String enhancedName = name + "$EnhancedByASM";
        enhancedSuperName = name;
        super.visit(version, access, enhancedName, signature, enhancedSuperName, interfaces);
    }


}
