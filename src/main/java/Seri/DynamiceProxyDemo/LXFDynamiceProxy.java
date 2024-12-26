package Seri.DynamiceProxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LXFDynamiceProxy {
    public static void main(String[] args) {
        InvocationHandler handler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                if (method.getName().equals("helloworld")) {
                    System.out.println("Hello world, " + args[0]);
                }
                return null;
            }
        };
        // 实现两个接口调用
        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 传入ClassLoader
                new Class[] { Hello.class , HelloWorld.class}, // 传入要实现的接口
                handler); // 传入处理调用方法的InvocationHandler
        hello.morning("Bob");

        HelloWorld helloWorld = (HelloWorld) hello;
        helloWorld.helloworld("Bob");
    }
}
interface Hello{
    void morning(String name);
}
interface HelloWorld{
    void helloworld(String name);
}
