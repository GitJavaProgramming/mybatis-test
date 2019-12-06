package org.pp.mybatis.foundationsupportlayer.reflector.proxy.jdk;

import org.pp.mybatis.foundationsupportlayer.reflector.proxy.interceptor.Interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JdkDynamicProxyHandler implements InvocationHandler {

    /**
     * 被代理对象
     */
    private Object proxied;

    /**
     * 拦截器
     */
    private List<Interceptor> interceptorList = new ArrayList<Interceptor>();

    private JdkDynamicProxyHandler() {
    }

    public List<Interceptor> getInterceptorList() {
        return interceptorList;
    }

    public void setInterceptorList(List<Interceptor> interceptorList) {
        this.interceptorList = interceptorList;
    }

    public Object getProxied() {
        return proxied;
    }

    public void setProxied(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy/* 对象的代理 */, Method method, Object[] args) throws Throwable {
        System.out.println("method: " + method.getDeclaringClass());
        invokeBefore();
        Object result = method.invoke(proxied, args);
        invokeAfter();
        return result;
    }

    private void invokeAfter() {
        System.out.println("invokeAfter");
        for (Interceptor interceptor : interceptorList) {
            interceptor.postAction();
        }
    }

    private void invokeBefore() {
        System.out.println("invokeBefore");
        for (Interceptor interceptor : interceptorList) {
            interceptor.postAction();
        }
    }

    private static class Holder {
        private static /* final 指定为常量立即初始化？ */ JdkDynamicProxyHandler handler = new JdkDynamicProxyHandler();
    }

    public static JdkDynamicProxyHandler getInstance() {
        return Holder.handler;
    }
}
