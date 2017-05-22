package grp1.dao;

import grp1.DataBaseConnectionTester;
import grp1.model.Disaster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 22.05.2017.
 */
public class DisasterDao {
    private Connection connection;

    public DisasterDao() {
        DataBaseConnectionTester tester = new DataBaseConnectionTester();
        this.connection = tester.getConnection();
    }

    public List getAllDisasters() throws SQLException {
        ArrayList<Disaster> list = new ArrayList<Disaster>();
        PreparedStatement ps= connection.prepareStatement(
                "select * from disasters");
        try
        {
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer dis_id = rs.getInt("dis_id");
                String dis_name= rs.getString("dis_name");
                Boolean dis_is_global= rs.getBoolean("dis_is_global");
                list.add(new Disaster(dis_id, dis_name, dis_is_global));
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

    public Disaster getDisaster(Integer id)
    {
        return null;
    }

    public int update(Disaster dis) throws SQLException {
        int result = 0;
        PreparedStatement ps= connection.prepareStatement(
                "update disasters set dis_name=?, dis_is_global=? where dis_id=?");
        try
        {
            ps.setString( 1, dis.getName() );
            ps.setBoolean( 2, dis.getGlobal() );
            ps.setInt( 3, dis.getDisasterId() );
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

    public int insert(Disaster dis) throws SQLException {
        int result = 0;
        PreparedStatement ps= connection.prepareStatement(
                "insert into disasters (dis_name, dis_is_global) values (?,?)");
        try
        {
            ps.setString( 1, dis.getName() );
            ps.setBoolean( 2, dis.getGlobal() );
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
                "delete from disasters where dis_id=?");
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

    public Disaster getDisasterById(Integer id) throws SQLException {
        Disaster dis = null;
        PreparedStatement ps= connection.prepareStatement(
                "select * from disasters where dis_id=?");
        try
        {
            ps.setInt( 1, id );
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Integer dis_id = rs.getInt("dis_id");
                String dis_name= rs.getString("dis_name");
                Boolean dis_is_global= rs.getBoolean("dis_is_global");
                dis = new Disaster(dis_id, dis_name, dis_is_global);
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
        return dis;
    }
}

