package Seri.JVMAgentInjetction;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class Inject_Agent {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> lists = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : lists) {
            if (vmd.displayName().equals("Seri.JVMAgentInjection.Say_Hello")){
                System.out.println("匹配成功");
                VirtualMachine vm = VirtualMachine.attach(vmd.id());
                vm.loadAgent("src/main/java/Seri/JVMAgentInjection/AgentMain.jar");
                vm.detach();
            }
        }
    }
}
