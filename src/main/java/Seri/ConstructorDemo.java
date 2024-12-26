package Seri;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = null;
        clazz = Class.forName("Seri.User");

        User user = (User) clazz.newInstance();
        user.setAge(11);
        user.setName("y1shin");
        System.out.println(user);

        User user2 = (User) clazz.getConstructor(String.class).newInstance("y2shin");
        user2.setAge(22);
        System.out.println(user2);

        Constructor<?> constructor = clazz.getDeclaredConstructor(int.class, String.class);
        constructor.setAccessible(true);
        User user3 = (User) constructor.newInstance(33, "y3shin");
        System.out.println(user3);

        Constructor<?> constructor1[] = clazz.getDeclaredConstructors();
        for (int i=0;i < constructor1.length ;i++){
            Class<?> parameterTypes[] = constructor1[i].getParameterTypes();
            System.out.println("构造函数名称：" + constructor1[i].toString());
        }

        Constructor constructor2 = clazz.getDeclaredConstructor(int.class, String.class);
        Class uclazz = constructor2.getDeclaringClass();
        System.out.println("构造函数所在类：" + uclazz.getName());
    }
}
class User {
    private int age;
    private String name;
    public User() {
        super();
    }
    public User(String name) {
        super();
        this.name = name;
    }

    /**
     * 私有构造
     * @param age
     * @param name
     */
    private User(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
