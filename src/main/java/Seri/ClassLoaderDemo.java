package Seri;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ClassLoaderDemo {
    /**
     * Class.forName(): 将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块；
     *
     * ClassLoader.loadClass(): 只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,
     * 只有在newInstance才会去执行static块。
     *
     * Class.forName(name, initialize, loader)带参函数也可控制是否加载static块。
     * 并且只有调用了newInstance()方法采用调用构造函数，创建类的对象 。
     *
     */
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/y1shin/IdeaProjects/Seri/target/classes/Seri/ShellClass.class"));
        System.out.println(Base64.getEncoder().encodeToString(bytes));

        Method defineClass =
                ClassLoader.class.getDeclaredMethod("defineClass", String.class,
                        byte[].class, int.class, int.class);
        defineClass.setAccessible(true);
        String src = "yv66vgAAADMANAoACAAkCgAlACYIACcKACUAKAcAKQoABQAqBwArBwAsAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBABFMU2VyaS9TaGVsbENsYXNzOwEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACGRvY3VtZW50AQAtTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007AQAIaGFuZGxlcnMBAEJbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjsBAApFeGNlcHRpb25zBwAtAQCmKExjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO0xjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRlcmF0b3I7TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACGl0ZXJhdG9yAQA1TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvZHRtL0RUTUF4aXNJdGVyYXRvcjsBAAdoYW5kbGVyAQBBTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjsBAAg8Y2xpbml0PgEAAWUBABVMamF2YS9sYW5nL0V4Y2VwdGlvbjsBAA1TdGFja01hcFRhYmxlBwApAQAKU291cmNlRmlsZQEAD1NoZWxsQ2xhc3MuamF2YQwACQAKBwAuDAAvADABAARjYWxjDAAxADIBABNqYXZhL2xhbmcvRXhjZXB0aW9uDAAzAAoBAA9TZXJpL1NoZWxsQ2xhc3MBAEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFjdFRyYW5zbGV0AQA5Y29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL1RyYW5zbGV0RXhjZXB0aW9uAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwEAD3ByaW50U3RhY2tUcmFjZQAhAAcACAAAAAAABAABAAkACgABAAsAAAAvAAEAAQAAAAUqtwABsQAAAAIADAAAAAYAAQAAABYADQAAAAwAAQAAAAUADgAPAAAAAQAQABEAAgALAAAAPwAAAAMAAAABsQAAAAIADAAAAAYAAQAAACIADQAAACAAAwAAAAEADgAPAAAAAAABABIAEwABAAAAAQAUABUAAgAWAAAABAABABcAAQAQABgAAgALAAAASQAAAAQAAAABsQAAAAIADAAAAAYAAQAAACcADQAAACoABAAAAAEADgAPAAAAAAABABIAEwABAAAAAQAZABoAAgAAAAEAGwAcAAMAFgAAAAQAAQAXAAgAHQAKAAEACwAAAGEAAgABAAAAErgAAhIDtgAEV6cACEsqtgAGsQABAAAACQAMAAUAAwAMAAAAFgAFAAAAGQAJABwADAAaAA0AGwARAB0ADQAAAAwAAQANAAQAHgAfAAAAIAAAAAcAAkwHACEEAAEAIgAAAAIAIw==";
        byte[] code = Base64.getDecoder().decode(src);
        Class hello = (Class)defineClass.invoke(
                ClassLoader.getSystemClassLoader(),
                null,
                code,
                0,
                code.length
        );
//        System.out.println(src);
        // ShellClass 类中定义了 static 块，所以需要 newInstance() 去执行 static 块中的内容
        hello.newInstance();

    }
}
