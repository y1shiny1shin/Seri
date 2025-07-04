package Seri;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2 {
    private static final Logger logger = LogManager.getLogger(Log4j2.class);
    public static void main(String[] args) {

        Package pkg = org.apache.logging.log4j.LogManager.class.getPackage();
        System.out.println(pkg.getImplementationVersion());

        String user = "admin";
        logger.error("Java version :{}","${java:version}");

    }
}
