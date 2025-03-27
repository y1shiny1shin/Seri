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

            CtClass ctClass = classPool.getCtClass("Seri.JVMAgentChangeBytecode.Say_Hello");
            CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello");

            String body = "{System.out.println(\"Hacker!\");}";
            ctMethod.setBody(body);

            byte[] byteCode = ctClass.toBytecode();
            return byteCode;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
