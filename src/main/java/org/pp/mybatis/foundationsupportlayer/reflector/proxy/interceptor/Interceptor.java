package org.pp.mybatis.foundationsupportlayer.reflector.proxy.interceptor;

/**
 * 代理方法拦截器
 */
public interface Interceptor {
    /**
     * 执行方法之前
     */
    void preAction();

    /**
     * 执行方法之后
     */
    void postAction();
}
