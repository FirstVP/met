package grp1.dao;

import grp1.DataBaseConnectionTester;
import grp1.model.Accident;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 22.05.2017.
 */
public class AccidentDao {
    private Connection connection;

    public AccidentDao() {
        DataBaseConnectionTester tester = new DataBaseConnectionTester();
        this.connection = tester.getConnection();
    }

    public List getAllAccidents() throws SQLException {
        ArrayList<Accident> list = new ArrayList<Accident>();
        PreparedStatement ps= connection.prepareStatement(
                "select * from accidents");
        try
        {
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer accident_id = rs.getInt("accident_id");
                Integer dis_id = rs.getInt("dis_id");
                Integer city_id= rs.getInt("city_id");
                Integer accident_level= rs.getInt("accident_level");
                String accident_content= rs.getNString("accident_content");
                Date date= rs.getDate("accident_date");
                list.add(new Accident(accident_id, dis_id, city_id, accident_level, accident_content, date));
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

    public Accident getAccident(Integer id)
    {
        return null;
    }

    public int update(Accident accident) throws SQLException {
        int result = 0;
        PreparedStatement ps= connection.prepareStatement(
                "update accidents set dis_id=?, city_id=?, accident_level=?, accident_content=?, accident_date=? where accident_id=?");
        try
        {
            ps.setInt( 1, accident.getDisasterId() );
            ps.setInt( 2, accident.getCityId() );
            ps.setInt( 3, accident.getLevel() );
            ps.setString( 4, accident.getContent() );
            ps.setDate(5, new java.sql.Date(accident.getDate().getTime()));
            ps.setInt( 6, accident.getAccidentId() );
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

    public int insert(Accident accident) throws SQLException {
        int result = 0;
        PreparedStatement ps= connection.prepareStatement(
                "insert into accidents (dis_id, city_id, accident_level, accident_content, accident_date) values (?,?,?,?,?)");
        try
        {
            ps.setInt( 1, accident.getDisasterId() );
            ps.setInt( 2, accident.getCityId() );
            ps.setInt( 3, accident.getLevel() );
            ps.setString( 4, accident.getContent() );
            ps.setDate(5, new java.sql.Date(accident.getDate().getTime()));
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
                "delete from accidents where accident_id=?");
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

    public Accident getAccidentById(Integer id) throws SQLException {
        Accident accident = null;
        PreparedStatement ps= connection.prepareStatement(
                "select * from accidents where accident_id=?");
        try
        {
            ps.setInt( 1, id );
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer accident_id = rs.getInt("accident_id");
                Integer dis_id = rs.getInt("dis_id");
                Integer city_id= rs.getInt("city_id");
                Integer accident_level= rs.getInt("accident_level");
                String accident_content= rs.getNString("accident_content");
                Date date= rs.getDate("accident_date");
                accident = new Accident(accident_id, dis_id, city_id, accident_level, accident_content, date);

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
        return accident;
    }
}

