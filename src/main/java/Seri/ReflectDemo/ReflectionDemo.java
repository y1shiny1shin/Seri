package Seri.ReflectDemo;//package com.sec.seri;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException, IOException {
//        Runtime.getRuntime().exec("code");

        Person person = new Person("张三", 20);
        Class c = person.getClass();
        // Class c = Person.class;

        // 创建一个构造器，也就是Person类中的构造方法
        Person personDefalut = (Person) c.newInstance();// 默认的无参构造
        System.out.println(personDefalut); // Person{name='default', age=-1}

        // 创建构造器，构造的是有参构造方法
        Constructor PersonConstructor = c.getConstructor(String.class, Integer.class);
        Person personNew = (Person) PersonConstructor.newInstance("李四", 30);
        System.out.println(personNew); // Person{name='李四', age=30}

        // 获取类中的属性，getFields() 只能获取public
        Field[] personFileds = c.getFields();
        for (Field f : personFileds) { System.out.println(f); }

        // 获取类中的属性，getDeclaredFields() 都可以获取到
        Field[] personAllFileds = c.getDeclaredFields();
        for (Field f : personAllFileds){ System.out.println(f); }

        // [+] 修改public属性
        Field nameField = c.getField("age");
        nameField.set(personNew ,99); // 修改 personNew 中的 age
        System.out.println(personNew); // Person{name='李四', age=99}

        // [+] 修改private属性
        Field namePrivate = c.getDeclaredField("name");
        namePrivate.setAccessible(true);
        namePrivate.set(personNew ,"王五");
        System.out.println(personNew); // Person{name='王五', age=99}

        /*// 获取类中的方法，getMethods() 只能获取public
        Method[] personMethods = c.getMethods();
        for (Method m : personMethods) { System.out.println(m); }

        // 获取类中的方法，getDeclaredMethods() 都可以获取到
        Method[] personAllMethods = c.getDeclaredMethods();
        for (Method m : personAllMethods){ System.out.println(m); }*/

        // 执行方法 invoke
        Method test2 = c.getDeclaredMethod("test2");
        test2.setAccessible(true);
        test2.invoke(personNew ,null);
        Method test = c.getMethod("test" ,java.lang.String.class);
        test.invoke(personNew ,"hello");

    }
}
