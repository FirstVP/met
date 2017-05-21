package grp1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import  java.sql.Statement;
import  java.sql.ResultSet;
import  java.sql.Driver;
import java.util.ArrayList;
import  grp1.model.News;


/**
 * Created by admin on 19.02.2017.
 */
@SuppressWarnings("ALL")
public class DataBaseConnectionTester implements AutoCloseable {
    private final String HOST = "jdbc:mysql://localhost:3306/weatherdb";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final String TESTQUERY = "SELECT * FROM news";

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    public DataBaseConnectionTester() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<News> getNews() throws SQLException {
        ArrayList<News> news = new ArrayList<News>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(TESTQUERY);
        while (resultSet.next()) {
            News item = new News();
            item.setNewsId(resultSet.getInt(1));
            item.setTitle(resultSet.getString(2));
            item.setBrief(resultSet.getString(3));
            item.setContent(resultSet.getString(4));
            news.add(item);
        }
        return  news;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

}
