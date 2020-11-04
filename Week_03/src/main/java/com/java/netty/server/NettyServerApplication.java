package com.java.netty.server;

/**
 * Created by yuchen.wu on 2020-11-03
 */

public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
        String proxyServer = System.getProperty("proxyServer","127.0.0.1");
        String port = System.getProperty("port", "8888");
        String proxyPort = System.getProperty("proxyPort", "8088");

        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        NettyProxyServer server = new NettyProxyServer(Integer.parseInt(port), Integer.parseInt(proxyPort), proxyServer);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + proxyServer);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
