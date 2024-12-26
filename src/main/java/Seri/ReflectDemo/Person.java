package Seri.ReflectDemo;

import java.io.Serializable;

public class Person implements Serializable {
    private String name="default";
    public Integer age=-1;


    public Person() {
    }
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void test(String ceshi){
        System.out.println("[+] ceshi "+ this.name);
    }

    private void test2(){
        System.out.println("[+] ceshi private");
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

}
