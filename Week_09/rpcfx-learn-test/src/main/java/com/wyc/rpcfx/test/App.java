package com.wyc.rpcfx.test;

import java.io.IOException;

/**
 * Created by yuchen.wu on 2020-12-13
 */

public class App {

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.say();
    }

    public void say() {
        System.out.println("App say");
    }

}
