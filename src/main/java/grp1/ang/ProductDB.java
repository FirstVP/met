package grp1.ang;

import grp1.DataBaseConnectionTester;
import grp1.dao.NewsDao;
import grp1.model.News;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import grp1.model.News;
/**
 * Created by admin on 29.05.2017.
 */
public class ProductDB {
    public static News fileToString(String filename)
    {

        News p = new News();
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(filename);
            JSONObject json = (JSONObject) obj;
            String newsId = (String) json.get("newsId");
            String title = (String) json.get("title");
            String brief = (String) json.get("brief");
            String content = (String) json.get("content");
            //Date date = (Date) json.get("date");
            System.out.println("date: "+json.get("date"));
            Date date = new Date();
           // date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'z'").parse((String)json.get("date"));
            ZonedDateTime zdt = ZonedDateTime.parse((String)json.get("date"));
            Calendar calendar = Calendar.getInstance();
            calendar.set(zdt.getYear(), zdt.getMonthValue() - 1, zdt.getDayOfMonth(), 0, 0);
            date = calendar.getTime();
            ProductDB.insert(title, brief, content, date);
            System.out.println("ANGULARINSER");
        }
        catch (Exception ex) { ex.printStackTrace(); }
        return p;
    }

    public static News jsonDataForUpdate(String filename)
    {
        System.out.println("inside filetostring "+filename);
        News p = new News();
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(filename);
            System.out.println("object "+obj);
            JSONObject json = (JSONObject) obj;

            int id = Integer.parseInt(String.valueOf(json.get("newsId")));
            String title= (String)json.get("title");
            String brief= (String)json.get("brief");
            String content= (String)json.get("content");
            Date date= new Date();
            ZonedDateTime zdt = ZonedDateTime.parse((String)json.get("date"));
            Calendar calendar = Calendar.getInstance();
            calendar.set(zdt.getYear(), zdt.getMonthValue() - 1, zdt.getDayOfMonth(), 0, 0);
            date = calendar.getTime();
            p.setNewsId(id);
            p.setTitle(title);
            p.setBrief(brief);
            p.setContent(content);
            p.setDate(date);
            new NewsDao().update(p);
            System.out.println("id "+id);


        }
        catch (Exception ex) {  }
        return p;

    }


    public static void insert(String title, String brief, String content, Date date) {
        //System.out.println("in insert "+name+ description);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        System.out.println("11111111111111111111111");
        try {
            connection = new DataBaseConnectionTester().getConnection();
            preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO news (news_title, news_brief, news_content, news_date)" +
                    "VALUES (?, ?, ?, ?)");
            System.out.println("2222222222222222");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, brief);
            preparedStatement.setString(3, content);
            preparedStatement.setDate(4, new java.sql.Date(date.getTime()));
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static News jsonDataForDelete(String filename) {
        System.out.println("inside filetostring "+filename);
        News p = new News();
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(filename);
            System.out.println("object "+obj);
            JSONObject json = (JSONObject) obj;

            int id = Integer.parseInt(String.valueOf(json.get("newsId")));
            new NewsDao().delete(id);


        }
        catch (Exception ex) {  }
        return p;
    }
}

