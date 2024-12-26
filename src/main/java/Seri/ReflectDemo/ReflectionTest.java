package Seri.ReflectDemo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Person person1 = new Person("y1shin", 10);
        System.out.println(person1);

        Field f = person1.getClass().getDeclaredField("name");
        f.setAccessible(true);
        f.set(person1, "y2shin");
        System.out.println(person1);


    }
}
