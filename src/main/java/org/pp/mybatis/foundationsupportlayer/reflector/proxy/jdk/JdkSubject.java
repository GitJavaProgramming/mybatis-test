package org.pp.mybatis.foundationsupportlayer.reflector.proxy.jdk;

public class JdkSubject implements Subject {

    public void doSomething() {
        System.out.println("jdk DynamicProxy invoke method. do something.");
    }

    public void getStr() {
        System.out.println("jdk DynamicProxy invoke method. getStr method.");
    }
}
