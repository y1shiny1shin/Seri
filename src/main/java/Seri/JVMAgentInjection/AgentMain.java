package Seri.JVMAgentInjection;

import java.lang.instrument.Instrumentation;

import static java.lang.Thread.sleep;


public class AgentMain {
    /**
     * 方法必须命名为agentmain，
     * private void loadClassAndCallPremain(String var1, String var2) throws Throwable {
     *         this.loadClassAndStartAgent(var1, "premain", var2);
     *     }
     *
     * private void loadClassAndCallAgentmain(String var1, String var2) throws Throwable {
     *     this.loadClassAndStartAgent(var1, "agentmain", var2);
     * }
     * 通过名字和参数来查找agent类
     */
    public static void agentmain(String agentArgs, Instrumentation inst) throws InterruptedException {
        Class[] clss = inst.getAllLoadedClasses();

        for (Class cls : clss) {
            System.out.println("--------------------------");
            System.out.println("加载类: " + cls.getName());
            System.out.println("是否可被修改: " + inst.isModifiableClass(cls));
        }
    }
}