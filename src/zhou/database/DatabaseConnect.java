package zhou.database;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Collection;

/**
 * @author zhouh
 * 2020-12-7
 * 原代码中，每次对数据库进行操作都需要调用一次下面函数进行连接，然后关闭
 * 此处修改为了单例模式，并在退出程序时再进行关闭操作，使得程序只进行一次连接与关闭
 */
public class DatabaseConnect {
    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null) {
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
    }
}
