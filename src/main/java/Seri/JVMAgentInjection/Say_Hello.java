package Seri.JVMAgentInjection;

import static java.lang.Thread.sleep;

public class Say_Hello {
    public static void main(String[] args) throws InterruptedException {
        for (;;){
            System.out.println("hello");
            sleep(5000);
        }
    }
}
