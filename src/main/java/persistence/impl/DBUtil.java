package persistence.impl;
import javax.xml.stream.events.Comment;
import java.sql.*;
public class DBUtil {
    private static String driverString = "com.mysql.cj.jdbc.Driver";
    private static String connectionString = "jdbc:mysql://localhost:3306/petstore?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "123456";

    public static Connection getConnection() throws Exception {
        Connection connection = null;
        try{
            Class.forName(driverString);
            connection = DriverManager.getConnection(connectionString, username, password);
        }catch (Exception e) {
            throw e;
        }
        return connection;
    }

    public static void closeStatement(Statement statement) throws Exception {
        statement.close();
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws Exception {
        preparedStatement.close();
    }

    public static void closeResultSet(ResultSet resultSet) throws Exception {
        resultSet.close();
    }

    public static void closeConnection(Connection connection) throws Exception {
        connection.close();
    }
}
