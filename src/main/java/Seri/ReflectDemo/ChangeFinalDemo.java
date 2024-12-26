package Seri.ReflectDemo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ChangeFinalDemo {
    public static final String test1 = "True";
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ChangeFinalDemo changeFinalDemo = new ChangeFinalDemo();
        Field field = changeFinalDemo.getClass().getDeclaredField("test1");
        // 现在modifiers的值为 0b00011001 = 25
        Field modifiers = field.getClass().getDeclaredField("modifiers");
        // 需要将final对应的位由 1 变为 0 ,即变为 0b00001001 = 9
        modifiers.setAccessible(true);
        modifiers.set(field , field.getModifiers() & ~Modifier.FINAL);

        field.set(changeFinalDemo , "False");
        /**
         * JVM的内联优化了：
         *
         * 内联函数，编译器将指定的函数体插入并取代每一处调用该函数的地方（上下文），从而节省了每次调用函数带来的额外时间开支。
         * 简单的说，就是JVM在处理代码的时候会帮我们优化代码逻辑，比如上述的final变量，
         * 已知final修饰后不会被修改，所以获取这个变量的时候就直接帮你在编译阶段就给赋值了。
         *
         * 所以上述的getName方法经过JVM编译内联优化后会变成：
         *
         * public String getName() {
         *     return "sam";
         * }
         *
         * 所以必须要使用如下方法获取变量的值
         */
        System.out.println(field.get(changeFinalDemo));
    }
}
