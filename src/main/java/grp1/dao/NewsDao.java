package grp1.dao;

import grp1.DataBaseConnectionTester;
import grp1.model.News;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 09.05.2017.
 */
public class NewsDao {
    private Connection connection;

    public NewsDao() {
        DataBaseConnectionTester tester = new DataBaseConnectionTester();
        this.connection = tester.getConnection();
    }

    public List getAllNews() throws SQLException {
        ArrayList<News> list = new ArrayList<News>();
        PreparedStatement ps= connection.prepareStatement(
                "select * from news");
        try
        {
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer news_id = rs.getInt("news_id");
                String news_title = rs.getString("news_title");
                String news_brief= rs.getString("news_brief");
                String news_content= rs.getString("news_content");
                Date news_date = rs.getDate("news_date");
                list.add(new News(news_id, news_title, news_brief, news_content, news_date));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (ps != null)
                ps.close();
            if (connection != null)
                connection.close();

        }
        return list;
    }

    public News getNews(Integer id)
    {
        return null;
    }

    public int update(News news) throws SQLException {
        int result = 0;
        PreparedStatement ps= connection.prepareStatement(
                "update news set news_title=?, news_brief=?, news_content=?, news_date=? where news_id=?");
        try
        {
            ps.setString( 1, news.getTitle() );
            ps.setString( 2, news.getBrief() );
            ps.setString( 3, news.getContent() );
            ps.setInt( 5, news.getNewsId() );
            ps.setDate(4, new java.sql.Date(news.getDate().getTime()));
            result = ps.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (ps != null)
                ps.close();
            if (connection != null)
                connection.close();

        }
        return result;
    }

    public int insert(News news) throws SQLException {
        int result = 0;
        PreparedStatement ps= connection.prepareStatement(
                "insert into news (news_title, news_brief, news_content, news_date) values (?,?,?,?)");
        try
        {
            ps.setString( 1, news.getTitle() );
            ps.setString( 2, news.getBrief() );
            ps.setString( 3, news.getContent() );
            ps.setDate( 4, new java.sql.Date(news.getDate().getTime()) );
            result = ps.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (ps != null)
            ps.close();
            if (connection != null)
                connection.close();

        }
        return result;
    }

    public int delete(Integer id) throws SQLException {
        int result = 0;
        PreparedStatement ps = connection.prepareStatement(
                "delete from news where news_id=?");
        try
        {
            ps.setInt( 1, id );
            result = ps.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (ps != null)
                ps.close();
            if (connection != null)
                connection.close();

        }
        return result;
    }

    public News getNewsById(Integer id) throws SQLException {
        News news = null;
        PreparedStatement ps= connection.prepareStatement(
                "select * from news where news_id=?");
        try
        {
            ps.setInt( 1, id );
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer news_id = rs.getInt("news_id");
                String news_title = rs.getString("news_title");
                String news_brief= rs.getString("news_brief");
                String news_content= rs.getString("news_content");
                Date news_date = rs.getDate("news_date");
                news = new News(news_id, news_title, news_brief, news_content, news_date);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (ps != null)
                ps.close();
            if (connection != null)
                connection.close();

        }
        return news;
    }
}
