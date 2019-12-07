package org.pp.mybatis.foundationsupportlayer.reflector.proxy.jdk;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkInterfaceDynamicProxyHandler implements InvocationHandler {

    static {
        System.out.println("JdkInterfaceDynamicProxyHandler Class init.");
    }

    private JdkInterfaceDynamicProxyHandler() {
    }

    public Object invoke(Object proxy/* 对象的代理 */, Method method, Object[] args) throws Throwable {
        System.out.println("method.getDeclaringClass(): " + method.getDeclaringClass());
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        // wrap,getAnnotation() ops
        System.out.println(method.getName() + " invoke.");
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("annotation annotationType: " + annotation.annotationType());
            if (annotation.annotationType() == NAnnotation.class) {
                NAnnotation nAnnotation = method.getAnnotation(NAnnotation.class);
                System.out.println("annotation value: " + nAnnotation.value());
            }
        }
        // 接口不提供实现，需要自己实现方法处理逻辑
        // MapperMethod--methodCache--sqlCommand,MethodSignature,Executor......
        return null;
    }

    private static class Holder {
        static {
            System.out.println("Holder Class init.");
        }

        private static /*final*/ /* final 指定为常量立即初始化？ */ JdkInterfaceDynamicProxyHandler handler = new JdkInterfaceDynamicProxyHandler();
    }

    public static JdkInterfaceDynamicProxyHandler getInstance() {
        return Holder.handler;
    }
}
