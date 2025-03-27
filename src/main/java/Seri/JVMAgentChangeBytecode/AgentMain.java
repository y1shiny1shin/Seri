package Seri.JVMAgentChangeBytecode;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

import Seri.JVMAgentChangeBytecode.Hello_Transform;

public class AgentMain {
    public static void agentmain(String agentArgs, Instrumentation inst) throws InterruptedException, UnmodifiableClassException {
        Class[] clss = inst.getAllLoadedClasses();
        Hello_Transform h = new Hello_Transform();

        for (Class cls : clss) {
            if (cls.getName().endsWith("JVMAgentChangeBytecode.Say_Hello")) {
                System.out.println("匹配成功");
                inst.addTransformer(h , true);
                inst.retransformClasses(cls);
            }
        }
    }
}
