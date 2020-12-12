package zhou.database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author zhouh
 * 2020-12-12
 * 使用Mybatis代替了JDBC
 */
public class DatabaseConnect {
    private static SqlSession session = null;

    public static SqlSession getSession() {
        if(session == null) {
            String resource = "zhou/Mybatis-config.xml";
            InputStream inputStream;

            {
                try {
                    inputStream = Resources.getResourceAsStream(resource);
                    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                    session = sqlSessionFactory.openSession();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return session;
        /*
        if(connection == null) {
            System.out.println("连接数据库...");
            String url = "jdbc:mysql://127.0.0.1:3306/market?serverTimezone=UTC";
            String userName = "root";
            String password = "26813003";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, userName, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
        */
    }
}
