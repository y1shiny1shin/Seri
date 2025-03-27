package Seri.JVMAgentChangeBytecode;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class Inject_Agent {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> lists = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : lists) {
            if (vmd.displayName().endsWith("Say_Hello")){
                System.out.println("匹配成功");
                VirtualMachine vm = VirtualMachine.attach(vmd.id());
                vm.loadAgent("/Users/y1shin/IdeaProjects/Seri/src/main/java/Seri/JVMAgentChangeBytecode/AgentMain.jar");
                vm.detach();
            }
        }
    }
}
