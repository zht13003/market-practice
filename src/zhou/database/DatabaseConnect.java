package zhou.database;
import java.sql.DriverManager;
import java.sql.Connection;

public class DatabaseConnect {
    public static Connection getConnection() {
        Connection connection = null;

        String url = "jdbc:mysql://127.0.0.1:3306/market?serverTimezone=UTC";
        String userName = "root";
        String password = "26813003";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
