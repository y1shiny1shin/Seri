package Seri;

import com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor;
import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class JDBC {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Runtime.getRuntime().exec("cal").getInputStream();
        Scanner s = new Scanner(inputStream).useDelimiter("\\a");
        String output = s.hasNext() ? s.next() : "";
        System.out.println(output);

    }
}
