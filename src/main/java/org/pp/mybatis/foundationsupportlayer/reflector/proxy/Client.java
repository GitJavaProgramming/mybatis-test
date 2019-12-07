package org.pp.mybatis.foundationsupportlayer.reflector.proxy;

import org.pp.mybatis.foundationsupportlayer.reflector.proxy.jdk.*;

public class Client {
    public static void main(String[] args) {
        // jdk proxy
        Subject subject = new JdkSubject();

        Subject proxy = (Subject) JdkProxy.getProxy(subject);
        proxy.doSomething();
        proxy.getStr();

        System.out.println("======================================================");

        Subject proxy2 = JdkInterfaceProxy.newInstance(Subject.class);
        proxy2.doSomething();
        proxy2.getStr();
    }
}
