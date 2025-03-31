package Seri.JVMAgentChangeBytecode;

import javassist.*;

import java.io.IOException;

public class LookInjectedBytecode {
    public static void main(String[] args) throws ClassNotFoundException, NotFoundException, IOException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(Class.forName("org.apache.catalina.core.ApplicationFilterChain")));

        // 获取 CtClass
        CtClass ctClass = pool.get("org.apache.catalina.core.ApplicationFilterChain");

        // 获取 doFilter 方法
        CtMethod method = ctClass.getDeclaredMethod("internalDoFilter");

        // 打印方法源码（字节码转换）
        System.out.println("Method Source Code:");
        System.out.println(method.getLongName());
        ctClass.writeFile();
    }
}
