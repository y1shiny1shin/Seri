package Seri.DynamiceProxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client2 {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        System.out.println(userServiceImpl);

        ClassLoader classLoader = userServiceImpl.getClass().getClassLoader();
        System.out.println(classLoader);

        Class[] interfaces = userServiceImpl.getClass().getInterfaces();
        for (Class i : interfaces){
            System.out.println(i);
        }

        InvocationHandler handler = new LogHandler(userServiceImpl);
        UserService proxy = (UserService) Proxy.newProxyInstance(classLoader, interfaces, handler);

        proxy.update();
    }
}
