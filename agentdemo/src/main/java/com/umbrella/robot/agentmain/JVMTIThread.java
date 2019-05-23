package com.umbrella.robot.agentmain;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class JVMTIThread {
    public static void main(String[] args)
            throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            System.out.println(vmd.displayName());
            if (vmd.displayName().endsWith("com.rong.kim.AgenttestApplication")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                try {
                    virtualMachine.loadAgent("D:/aaaaagggg/javaagentparent/agentmain/target/my-agent.jar", "cxs");
                    System.out.println("ok");
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    virtualMachine.detach();
                }
            }
        }
    }
}
