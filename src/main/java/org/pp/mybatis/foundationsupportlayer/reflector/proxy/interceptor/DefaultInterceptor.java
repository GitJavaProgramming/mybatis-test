package org.pp.mybatis.foundationsupportlayer.reflector.proxy.interceptor;

public class DefaultInterceptor implements Interceptor {

    public void preAction() {
        System.out.println("======intercept before method invoke======");
    }

    public void postAction() {
        System.out.println("======intercept after method invoke======");
    }
}
