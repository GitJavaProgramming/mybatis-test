package org.pp.mybatis.foundationsupportlayer.reflector.proxy;

import org.pp.mybatis.foundationsupportlayer.reflector.proxy.jdk.JdkProxy;
import org.pp.mybatis.foundationsupportlayer.reflector.proxy.jdk.JdkSubject;
import org.pp.mybatis.foundationsupportlayer.reflector.proxy.jdk.Subject;

public class Client {
    public static void main(String[] args) {
        // jdk proxy
        Subject subject = new JdkSubject();

        Subject proxy = (Subject) JdkProxy.getProxy(subject);
        proxy.doSomething();
        proxy.getStr();

        System.out.println("======================================================");
    }
}
