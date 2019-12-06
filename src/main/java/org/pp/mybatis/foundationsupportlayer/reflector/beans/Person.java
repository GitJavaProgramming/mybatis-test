package org.pp.mybatis.foundationsupportlayer.reflector.beans;

import java.lang.reflect.Constructor;

/**
 *
 */
public class Person {
    private String name;
    private static volatile boolean flag = false;

    private Person() {
        synchronized (Person.class) {
            if (!flag) {
                flag = true;
            } else {
                throw new RuntimeException("单例模式被侵犯！");
            }
        }
    }

    public void say() {
        System.out.println("Person say: Hello World!");
    }

    /**
     * 类初始化，在多线程环境下能被正确的加锁和同步
     */
    private static class Holder {
        private static final Person person = new Person();
    }

    public static final Person getInstance() {
        return Holder.person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Class<Person> clazz1 = Person.class;
        Class<?> clazz2 = null;
        try {
            clazz2 = Class.forName("org.pp.mybatis.foundationsupportlayer.reflector.beans.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("clazz1 == clazz2 " + (clazz1 == clazz2));

        Person person1 = Person.getInstance();

        Constructor<?> constructor = null;
        try {
            constructor = clazz2.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        constructor.setAccessible(true);
        Person person = null;
        try {
            person = (Person) constructor.newInstance();
        } catch (Throwable throwable) {
            throwable.printStackTrace();

            String strTemp = throwable.getStackTrace().toString();
            System.out.println(strTemp);
            StackTraceElement stackTraceElement = throwable.getStackTrace()[0];// 得到异常栈的首个元素

            System.out.println("File=" + stackTraceElement.getFileName());// 打印文件名
            System.out.println("Line=" + stackTraceElement.getLineNumber());// 打印出错行号
            System.out.println("Method=" + stackTraceElement.getMethodName());// 打印出错方法
        }
        person1.say();
    }
}
