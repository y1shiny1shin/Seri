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

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
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

import static sun.security.x509.CertificateAlgorithmId.ALGORITHM;

public class Test {
    private static final String KEY = "oszXCfTeXHpIkMS3";
    private static final String ALGORITHM = "AES";
    public static void main(String[] args) throws Exception {
        System.out.println(decrypt("SpL+x7y+I+ZMyWEXeJ0NZMa2lXxVJJQX8qje+iiGLCag8tPabyOaifukFtoSxu6RFUpZaKyNuMCXHzK3RDjcTFL73lNoG0uHqJJBjObbdTPDfU7cYGyAY5cXrNzHkdq6IyH1gpeYpyzuLqXSSjhkwp1pKh6Uh6asrqyZuM97bG3Vakj6n1FXkVyDfSWHkDhIns9VKOKE5dgs4ZwhBUxRI67sQmSA2+47jDr/nLzxsa1DIv9K67DBh8hrgV2XrBsGbbCsaiZo45Qxy62KbNjXeNpMjnAF/05JhkOqFkaqh97yhOgERj/cx+y5sYC3GTSBK5UW7o8ozXn3OjZt1b6OFnX13uDI8Pnvajsw1EaY/KaDNCUS6ATLbajdQsza7Rmt1MODH7j5EW49s+1dKHdrWGv27YLnRMqQptBCn56yoiLZ21aJ/CggSZnRqdee7pChC23JnN+E3t2tKQG8QQnxT6XXsTmii0kRCEoXFiEVrmJpg+7wo1Mqpj9T32i6wlmP+bdJWcOxjizJOoSkd0RAVf8dU399C2TkViAaOhLAj2XCW+qF8z6Dbs/G1HIs51QGig0ujdQFkZ+j6wo2F5B0OBh0oMLT4q2A9DuU5XEC00M="));
    }

    public static String decrypt(String strToDecrypt) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedValue = Base64.getDecoder().decode(strToDecrypt);
        byte[] decryptedByteValue = cipher.doFinal(decodedValue);
        return Base64.getEncoder().encodeToString(decryptedByteValue);
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
