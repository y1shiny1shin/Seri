package Seri.ReflectDemo;

import sun.reflect.ReflectionFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionFactoryDemo {
    public static void main(String[] args)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //获取ReflectionFactory对象，它本身是单例的
        ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
        //获取Object类的构造器
        Constructor<Object> constructor = Object.class.getDeclaredConstructor();
        //根据Object构造器创建一个User类的构造器
        Constructor<?> constructorForSerialization = reflectionFactory
                .newConstructorForSerialization(User.class, constructor);
        constructorForSerialization.setAccessible(true);
        //实例化对象
        System.out.println(constructorForSerialization.newInstance());
    }

    public static class User {
        private String name = "lisi";
        public User() {
            System.out.println("User created");
        }
        @Override
        public String toString() {
            return "user=" + name;
        }
    }
}