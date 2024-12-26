package Seri.ReflectDemo;

import java.lang.reflect.Field;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        Class stiClass = StuInfo.class;
        // 获取public字段"score":
        System.out.println(stiClass.getField("age"));
        // 获取继承的public字段"name":
        System.out.println(stiClass.getField("name"));
        // 获取private字段"grade":
        System.out.println(stiClass.getDeclaredField("money"));
        // 获得值,name.get里面参数需要该类对象，而不是.class
        Field name = stiClass.getField("name");
        System.out.println(name.get(stiClass.newInstance()));
        // 设置值
        StuInfo stuInfo = new StuInfo();
        Field money = stiClass.getDeclaredField("money");
        money.setAccessible(true);
        money.set(stuInfo,2333333);
        System.out.println(stuInfo);

        Class.forName("java.lang.Runtime").
                getMethod("exec", String.class).
                invoke(
                Class.forName("java.lang.Runtime").getMethod("getRuntime").invoke(Class.forName("java.lang.Runtime")),
            "calc");


    }
}

class StuInfo extends PersonInfo{
    public int age;
    private int money;

    @Override
    public String toString() {
        return "StuInfo{" +
                "name=" + name +
                ", money=" + money +
                '}';
    }
}

class PersonInfo{
    public String name = "Y4tacker";
}
