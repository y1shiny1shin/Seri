package Seri.JVMAgentInjetction;

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
        while (true){
            System.out.println("调用了agentmain-Agent!");
            sleep(3000);
        }
    }
}