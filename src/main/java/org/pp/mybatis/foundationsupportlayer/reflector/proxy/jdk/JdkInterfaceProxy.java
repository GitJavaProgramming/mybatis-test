package org.pp.mybatis.foundationsupportlayer.reflector.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JdkInterfaceProxy {

    private JdkInterfaceProxy() {
    }

    public static <T> T newInstance(Class<T> proxied/* 被代理接口 */, InvocationHandler handler) {
        //取得代理对象
        return (T) Proxy.newProxyInstance(proxied.getClassLoader(), new Class<?>[]{proxied}, handler);
    }

    public static <T> T newInstance(Class<T> proxied) {
        InvocationHandler handler = JdkInterfaceDynamicProxyHandler.getInstance();
        return (T) Proxy.newProxyInstance(proxied.getClassLoader(), new Class<?>[]{proxied}, handler);
    }
}
