package Seri.ProxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        IUser user = new UserImpl();
//        IUser proxy = new UserProxy(user);
//        proxy.show();

        // 动态代理
        IUser proxy2 =  (IUser) new UserInvocationHandler(user).getProxyInstance();
        proxy2.show();
    }
}
