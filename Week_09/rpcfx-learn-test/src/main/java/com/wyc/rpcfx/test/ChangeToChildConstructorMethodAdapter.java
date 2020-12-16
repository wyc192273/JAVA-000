package com.wyc.rpcfx.test;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by yuchen.wu on 2020-12-13
 */

public class ChangeToChildConstructorMethodAdapter extends MethodAdapter {

    private String superClassName;

    public ChangeToChildConstructorMethodAdapter(MethodVisitor methodVisitor, String superClassName) {
        super(methodVisitor);
        this.superClassName = superClassName;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name,
                                String desc) {
        if (opcode == Opcodes.INVOKESPECIAL && name.equals("<init>")) {
            owner = superClassName;
        }
        super.visitMethodInsn(opcode, owner, name, desc);// 改写父类为 superClassName
    }
}
