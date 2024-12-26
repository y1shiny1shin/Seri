package Seri;

public class People {
    private String name="y1shin";
    private int age=20;

    public int getAge() {
        System.out.println("调用了 getAge");
        return age;
    }

    @Override
    public String toString() {
        System.out.println("调用了 toString");
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setAge(int age) {
        System.out.println("调用了 setAge");
        this.age = age;
    }

    public String getName() {
        System.out.println("调用了 getName");
        return name;
    }

    public void setName(String name) {
        System.out.println("调用了 setName");
        this.name = name;
    }
}
