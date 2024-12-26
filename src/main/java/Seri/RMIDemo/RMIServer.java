package Seri.RMIDemo;

import sun.rmi.registry.RegistryImpl_Skel;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    public interface IRemoteHelloworld extends Remote {
        public String hello() throws RemoteException;
    }


    public class RemoteHelloworld extends UnicastRemoteObject implements IRemoteHelloworld {
        protected RemoteHelloworld() throws RemoteException {
        }

        @Override
        public String hello() throws RemoteException {
            System.out.println("[+] 调用Remote hello方法");
            return "Hello world";
        }
    }

    private void start() throws Exception {
        RemoteHelloworld remoteHelloworld = new RemoteHelloworld();
        LocateRegistry.createRegistry(1099);
        Naming.bind("rmi://localhost:1099/Hello", remoteHelloworld);
        Naming.bind("rmi://localhost:1099/Hello2", remoteHelloworld);
        System.out.println("[+] Rmi 启动成功");
    }

    public static void main(String[] args) throws Exception {
        new RMIServer().start();
    }
}
