package Seri.JavaAssistDemo;

import javassist.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Demo1 {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("HelloWorld");

        // 添加 字段
        CtField ctFieldName = new CtField(pool.get("java.lang.String") ,"username",ctClass);
        ctFieldName.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ctFieldName ,CtField.Initializer.constant("y1shin"));
        // 添加 字段2
        CtField ctField = CtField.make("private static String username1=\"hello\";" ,ctClass);
        ctClass.addField(ctField);

        // 添加 getter setter
        ctClass.addMethod(CtNewMethod.getter("getUsername", ctFieldName));
        ctClass.addMethod(CtNewMethod.setter("setUsername", ctFieldName));

        // 新建 方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType ,"printUsername",new CtClass[]{CtClass.charType},ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody("{System.out.println($1+username);}");
        ctClass.addMethod(ctMethod);
        // 新建 方法
        CtMethod ctMethod1 = CtMethod.make("public static void main(String[] args){System.out.println(\"mian\");}",ctClass);
        ctClass.addMethod(ctMethod1);


        // 获取构造方法
        CtConstructor[] ctConstructors = ctClass.getDeclaredConstructors();
        for (CtConstructor ctConstructor : ctConstructors) {
            System.out.println(ctConstructor.getMethodInfo());
        }
        System.out.println("============================================================");
        CtField[] ctFields = ctClass.getDeclaredFields();
        for (CtField c: ctFields){
            System.out.println(c.getName());
        }
        System.out.println("============================================================");
        CtMethod[] ctMethods = ctClass.getMethods();
        for (CtMethod ctMethod2 : ctMethods){
            System.out.println(ctMethod2.getName());
        }


        ctClass.writeFile("/Users/y1shin/IdeaProjects/Seri/target/classes/Seri/JavaAssistDemo/");



    }
}
