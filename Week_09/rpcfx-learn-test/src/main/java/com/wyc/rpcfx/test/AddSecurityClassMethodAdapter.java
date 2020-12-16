package com.wyc.rpcfx.test;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by yuchen.wu on 2020-12-13
 */

public class AddSecurityClassMethodAdapter extends MethodAdapter {

    public AddSecurityClassMethodAdapter(MethodVisitor mv) {
        super(mv);
    }

    @Override
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/wyc/rpcfx/test/SecurityChecker", "checkSecurity", "()V");
    }
}
