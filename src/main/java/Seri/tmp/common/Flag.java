package Seri.tmp.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Flag implements Serializable {
    private static final long serialVersionUID = 1L;

    private String methodName;

    private boolean arg;

    private String command;

    public static String result;

    private String sn() throws IOException, InterruptedException {
        if (this.arg) {
            ProcessBuilder builder = new ProcessBuilder(new String[] { "bash", "-c", this.command });
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                output.append(line).append("\n");
            int exitCode = process.waitFor();
            reader.close();
            if (exitCode != 0)
                output.append("error command: ").append(this.command);
            return output.toString();
        }
        return this.command;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException, InterruptedException {
        in.defaultReadObject();
        if (this.methodName.equals("sn"))
            result = sn();
    }
}
