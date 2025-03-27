package Seri.JVMAgentChangeBytecode;

import static java.lang.Thread.sleep;

public class Say_Hello {
    public static void main(String[] args) {
        for (;;){
            sayHello();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void sayHello() {
        System.out.println("hello");
    }
}
