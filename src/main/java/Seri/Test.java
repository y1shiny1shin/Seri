package Seri;

import com.caucho.hessian.io.AnnotationInvocationHandler;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.tools.attach.VirtualMachine;
import javassist.ClassPool;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import com.sun.tools.attach.VirtualMachineDescriptor;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.LazyMap;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.util.ByteSource;

import javax.management.BadAttributeValueExpException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
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
import org.springframework.security.core.parameters.P;

public class Test {
    public static void main(String[] args) throws Exception {
        List<VirtualMachineDescriptor> lists = VirtualMachine.list();
        for (VirtualMachineDescriptor virtualMachineDescriptor : lists) {
            System.out.println(virtualMachineDescriptor.displayName());
            System.out.println(virtualMachineDescriptor.id());
        }
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
