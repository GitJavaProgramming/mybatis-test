package org.pp.mybatis.foundationsupportlayer.reflector.proxy.jdk;

public interface Subject {

    @Select("select name from user")
    void doSomething();

    void getStr();
}
