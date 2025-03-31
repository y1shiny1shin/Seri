package Seri.SnakeYaml;

import org.yaml.snakeyaml.Yaml;

public class BasicDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setName("xiaobai");
        user.setAge(20);
        Yaml yaml = new Yaml();
        String dump = yaml.dump(user);
        System.out.println(dump);
        System.out.println("=====================");
        Yaml yaml0 = new Yaml();
        yaml0.load(dump);
    }

    public static class User {
        String name;
        int age;

        public User() {
            System.out.println("User构造函数");
        }

        public String getName() {
            System.out.println("User.getName");
            return name;
        }

        public void setName(String name) {
            System.out.println("User.setName");
            this.name = name;
        }

        public int getAge() {
            System.out.println("User.getAge");
            return age;
        }

        public void setAge(int age) {
            System.out.println("User.setAge");
            this.age = age;
        }
    }

}
