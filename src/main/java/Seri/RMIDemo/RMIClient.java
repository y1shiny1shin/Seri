package Seri.RMIDemo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        RMIServer.IRemoteHelloworld hello = (RMIServer.IRemoteHelloworld)
                Naming.lookup("rmi://localhost:1099/Hello2");
        String[] s = Naming.list("rmi://localhost:1099/");
        for (String sx: s){
            System.out.println(sx);
        }
//        String s = hello.hello();
        System.out.println(s);

    }
}
