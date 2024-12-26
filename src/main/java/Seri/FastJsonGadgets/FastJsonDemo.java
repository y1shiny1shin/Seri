package Seri.FastJsonGadgets;


import Seri.People;
import Seri.U;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class FastJsonDemo {
    public static void main(String[] args) throws Exception {
        People people = new People();
        // 会出发 getter setter
//        String json1 = JSON.toJSONString(people , SerializerFeature.WriteClassName);
        String json1 = JSON.toJSONString(people ,SerializerFeature.WriteClassName);
        System.out.println(json1);
        System.out.println("============================================");

        // @type给你指定调用哪个类下的 setter 方法和 getter 方法
        Object parse = JSON.parse(json1);
//        Object parse = JSON.parse("{\"@type\":\"Seri.People\",\"age\":20,\"name\":\"y1shin2\"}");
        System.out.println(parse.toString());
        System.out.println("============================================");

        Object parse1 = JSON.parseObject(json1);
        System.out.println(parse1);
        System.out.println(parse1.getClass().getName());
        System.out.println("============================================");

        Object parse2 = JSON.parseObject(json1);
//        Object parse2 = JSON.parseObject("{\"@type\":\"Seri.People\",\"age\":20,\"name\":\"y1shin2\"}" ,Object.class);
        System.out.println(parse2);
        System.out.println(parse2.getClass().getName());

        System.out.println("============================================");

    }
}
/**
 * 调用了 getAge
 * 调用了 getName
 * {"age":20,"name":"y1shin"}
 * ============================================
 * {"name":"y1shin","age":20}
 * ============================================
 * com.alibaba.fastjson.JSONObject
 * ============================================
 * {"name":"y1shin","age":20}
 * ============================================
 */