package org.pp.mybatis.foundationsupportlayer.reflector.proxy.jdk;

import java.lang.reflect.Proxy;

public class JdkProxy {

//    private Object proxied; // 被代理对象

    private JdkProxy() {
    }

    public static Object getProxy(Object proxied/* 被代理对象 */) {
        JdkDynamicProxyHandler handler = JdkDynamicProxyHandler.getInstance();
        handler.setProxied(proxied); // 注入Handler invoke方法需要用到

        //取得代理对象
        return Proxy.newProxyInstance(proxied.getClass().getClassLoader(), proxied.getClass().getInterfaces(), handler);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
    }
}
