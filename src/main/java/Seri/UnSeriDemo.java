package Seri;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class UnSeriDemo {
    public static void main(String[] args) throws Exception {
        unserialize("cc1.bin");
    }

    public static Object unserialize(String Filename) throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
        System.out.println(ois.getClass());
        System.out.println(ois.toString());
        Object obj = ois.readObject();
        System.out.println(obj.getClass());
        System.out.println(obj.toString());
        return obj;
    }
}
